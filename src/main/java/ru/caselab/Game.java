package ru.caselab;

import ru.caselab.player.Human;
import ru.caselab.player.Player;
import ru.caselab.enumeration.CellState;
import ru.caselab.enumeration.GameState;
import ru.caselab.view.View;

public class Game {

    private GameState gameState;
    private final Player player1;
    private final Player player2;
    private Player winner;
    private final View view;

    public Game(Player player1, Player player2, View view) {
        gameState = GameState.IN_PROCESS;
        this.player1 = player1;
        this.player2 = player2;
        this.view = view;
    }

    public void play() {
        view.renderPlayer(player1);
        view.renderPlayer(player2);
        start();
    }

    private void start() {
        placeShipsAndRender(player1);
        placeShipsAndRender(player2);
        inProcess();
    }

    private void placeShipsAndRender(Player player) {
        player.placeShips();
        if (player instanceof Human) {
            view.renderPlayerField(player.getField());
        }
    }

    private void inProcess() {
        Player currentPlayer = player1;
        Player enemy = player2;

        while (gameState != GameState.END) {
            view.renderMove(currentPlayer, enemy);

            Coordinates moveCoordinates = requestValidMove(currentPlayer, enemy);
            boolean isHit = processMove(enemy.getField(), moveCoordinates);
            view.renderMoveResult(currentPlayer, enemy, moveCoordinates);

            if (isHit && enemy.getField().getShipsLeft() == 0) {
                winner = currentPlayer;
                gameState = GameState.END;
            }

            if (!isHit) {
                Player temp = currentPlayer;
                currentPlayer = enemy;
                enemy = temp;
            }
        }
        end();
    }

    private Coordinates requestValidMove(Player currentPlayer, Player enemy) {
        Coordinates moveCoordinates = currentPlayer.makeMove();

        while (!checkMove(enemy.getField(), moveCoordinates)) {
            view.renderWrongMove();
            moveCoordinates = currentPlayer.makeMove();
        }

        return moveCoordinates;
    }

    private boolean checkMove(Field field, Coordinates moveCoordinates) {
        CellState cellState = field.getCells()[moveCoordinates.x()][moveCoordinates.y()].getCellState();
        return cellState == CellState.SHIP || cellState == CellState.EMPTY;
    }

    private boolean processMove(Field field, Coordinates moveCoordinates) {
        Cell cell = field.getCells()[moveCoordinates.x()][moveCoordinates.y()];

        return switch (cell.getCellState()) {
            case SHIP -> handleHit(cell, field);
            case EMPTY -> {
                cell.setCellState(CellState.MISS);
                yield false;
            }
            default -> false;
        };
    }

    private boolean handleHit(Cell cell, Field field) {
        Ship ship = cell.getShip();
        ship.setDeckLeft(ship.getDeckLeft() - 1);

        if (ship.getDeckLeft() > 0) {
            cell.setCellState(CellState.WOUNDED);
        } else {
            sinkShip(ship, field);
        }
        return true;
    }

    private void sinkShip(Ship ship, Field field) {
        for (Cell shipCell : ship.getCells()) {
            shipCell.setCellState(CellState.DEAD);
        }
        field.setShipsLeft(field.getShipsLeft() - 1);
    }

    private void end() {
        view.renderWinner(winner);
    }
}

