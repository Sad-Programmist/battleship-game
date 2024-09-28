package ru.caselab;

import ru.caselab.state.ShipPosition;

public class Ship {

    private final int size;
    private final Coordinates startCoordinates;
    private final ShipPosition shipPosition;
    private int deckLeft;
    private Coordinates[] coordinates;


    public Ship(int size, Coordinates startCoordinates, ShipPosition shipPosition) {
        this.size = size;
        deckLeft = size;
        this.startCoordinates = startCoordinates;
        this.shipPosition = shipPosition;
    }

    public void setDeckLeft(int deckLeft) {
        this.deckLeft = deckLeft;
    }

    public void setCoordinates(Coordinates[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getSize() {
        return size;
    }

    public int getDeckLeft() {
        return deckLeft;
    }

    public Coordinates[] getCoordinates() {
        return coordinates;
    }

    public Coordinates getStartCoordinates() {
        return startCoordinates;
    }

    public ShipPosition getShipPosition() {
        return shipPosition;
    }
}
