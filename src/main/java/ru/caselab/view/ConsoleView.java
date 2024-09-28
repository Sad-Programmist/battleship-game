package ru.caselab.view;

import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.player.Human;
import ru.caselab.player.Player;

public class ConsoleView implements View {

    @Override
    public void renderPlayerField(Field field) {
        System.out.println("Your field");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < Field.SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");

            for (int j = 0; j < Field.SIZE; j++) {
                switch (field.getCells()[i][j].getCellState()) {
                    case EMPTY -> System.out.print(".");
                    case SHIP -> System.out.print("#");
                    case MISS -> System.out.print("*");
                    case WOUNDED -> System.out.print("x");
                    case DEAD -> System.out.print("@");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Override
    public void renderEnemyField(Field field) {
        System.out.println("Enemy's field");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < Field.SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");

            for (int j = 0; j < Field.SIZE; j++) {
                switch (field.getCells()[i][j].getCellState()) {
                    case EMPTY, SHIP -> System.out.print(".");
                    case MISS -> System.out.print("*");
                    case WOUNDED -> System.out.print("x");
                    case DEAD -> System.out.print("@");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Override
    public void renderPlayer(Player player) {
        System.out.println("Player's name: " + player.getName());
    }

    @Override
    public void renderMove(Player currentPlayer, Player enemy) {
        System.out.println("Now " + currentPlayer.getName() + "'s move");

        if (currentPlayer instanceof Human) {
            renderPlayerField(currentPlayer.getField());
        }
        renderEnemyField(enemy.getField());
    }

    @Override
    public void renderMoveResult(Player currentPlayer, Player enemy, Coordinates moveCoordinates) {
        System.out.println("Player " + currentPlayer.getName() + " made move " + (char) ('A' +
                moveCoordinates.getX()) + (moveCoordinates.getY() + 1));
        System.out.println(enemy.getField().getCells()[moveCoordinates.getX()][moveCoordinates.getY()].getCellState().name());
        System.out.println("Move result");
        renderEnemyField(enemy.getField());
    }

    @Override
    public void renderWinner(Player player) {
        System.out.println("Winner is " + player.getName());
    }
}
