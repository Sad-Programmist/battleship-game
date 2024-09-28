package ru.caselab;

import ru.caselab.state.CellState;

public class Field {

    public static final int SIZE = 10;
    private final Cell[][] cells = new Cell[SIZE][SIZE];

    private int shipsLeft;

    public Field() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(CellState.EMPTY);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;
    }
}
