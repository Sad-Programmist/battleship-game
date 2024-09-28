package ru.caselab;

import ru.caselab.state.CellState;

public class Cell {

    private CellState cellState;
    private Ship ship;

    public Cell(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }

    public Ship getShip() {
        return ship;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
