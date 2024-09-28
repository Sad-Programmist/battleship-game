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
        System.out.println();
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
        System.out.println();
    }

    @Override
    public void renderPlayer(Player player) {
        System.out.printf("Player's name: %s%n%n", player.getName());
    }

    @Override
    public void renderMove(Player currentPlayer, Player enemy) {
        System.out.printf("Now %s's move", currentPlayer.getName());

        if (currentPlayer instanceof Human) {
            renderPlayerField(currentPlayer.getField());
        }
        renderEnemyField(enemy.getField());
    }

    @Override
    public void renderWrongMove() {
        System.out.println("Choose another coordinates\n");
    }

    @Override
    public void renderMoveResult(Player currentPlayer, Player enemy, Coordinates moveCoordinates) {
        String movePosition = getMovePosition(moveCoordinates);
        String cellState = enemy.getField().getCells()[moveCoordinates.x()][moveCoordinates.y()].getCellState().name();

        System.out.printf("Player %s made move %s%n", currentPlayer.getName(), movePosition);
        System.out.println(cellState + "\n");
        System.out.println("Move result");
        renderEnemyField(enemy.getField());
    }

    private String getMovePosition(Coordinates moveCoordinates) {
        char column = (char) ('A' + moveCoordinates.x());
        int row = moveCoordinates.y() + 1;
        return String.valueOf(column) + row;
    }

    @Override
    public void renderWinner(Player player) {
        System.out.printf("Winner is %s%n", player.getName());
    }
}
