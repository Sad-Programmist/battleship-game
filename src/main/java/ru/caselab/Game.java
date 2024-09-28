package ru.caselab;

import ru.caselab.player.Player;
import ru.caselab.state.CellState;
import ru.caselab.state.GameState;
import ru.caselab.view.View;

public class Game {

    private GameState gameState;
    private Player player1;
    private Player player2;
    private Player winner;
    private View view;

    public Game(Player player1, Player player2, View view) {
        gameState = GameState.IN_PROCESS;
        this.player1 = player1;
        this.player2 = player2;
        this.view = view;
    }

    public void play() {
        player1.prepare();
        view.renderPlayer(player1);

        player2.prepare();
        view.renderPlayer(player2);

        start();
    }

    private void start() {
        player1.placeShips();
        player2.placeShips();

        inProcess();
    }

    private void inProcess() {
        Player currentPlayer = player1;
        Player enemy = player2;

        while (gameState != GameState.END) {
            view.renderMove(currentPlayer, enemy);

            Coordinates moveCoordinates = currentPlayer.makeMove();
            boolean isHit = processMove(enemy.getField(), moveCoordinates);

            view.renderMoveResult(currentPlayer, enemy, moveCoordinates);

            if (isHit && enemy.getField().getShipsLeft() == 0) {
                winner = currentPlayer;
                gameState = GameState.END;
            }

            if (!isHit) {
                currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
                enemy = enemy.equals(player1) ? player2 : player1;
            }
        }
        end();
    }

    private boolean processMove(Field field, Coordinates moveCoordinates) {
        Cell cell = field.getCells()[moveCoordinates.getX()][moveCoordinates.getY()];

        if (cell.getCellState() == CellState.SHIP) {
            Ship ship = cell.getShip();
            ship.setDeckLeft(ship.getDeckLeft() - 1);

            if (ship.getDeckLeft() > 0) {
                cell.setCellState(CellState.WOUNDED);

            } else {
                Coordinates[] shipCoordinates = ship.getCoordinates();
                for (Coordinates coordinates : shipCoordinates) {
                    field.getCells()[coordinates.getX()][coordinates.getY()].setCellState(CellState.DEAD);
                }
                field.setShipsLeft(field.getShipsLeft() - 1);
            }
            return true;
        }

        if (cell.getCellState() == CellState.EMPTY) {
            cell.setCellState(CellState.MISS);
            return false;
        }

        return false;
    }

    private void end() {
        view.renderWinner(winner);
    }
}

