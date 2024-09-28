package ru.caselab;

import ru.caselab.enumeration.ShipPosition;

import java.util.List;

public class Ship {

    private final int size;
    private final Coordinates startCoordinates;
    private final ShipPosition shipPosition;
    private int deckLeft;
    private List<Cell> cells;


    public Ship(int size, Coordinates startCoordinates, ShipPosition shipPosition) {
        this.size = size;
        deckLeft = size;
        this.startCoordinates = startCoordinates;
        this.shipPosition = shipPosition;
    }

    public void setDeckLeft(int deckLeft) {
        this.deckLeft = deckLeft;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public int getDeckLeft() {
        return deckLeft;
    }

    public Coordinates getStartCoordinates() {
        return startCoordinates;
    }

    public ShipPosition getShipPosition() {
        return shipPosition;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
