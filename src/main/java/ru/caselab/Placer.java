package ru.caselab;

import ru.caselab.state.CellState;
import ru.caselab.state.ShipPosition;

import java.util.List;

public class Placer {

    public void manualPlace(List<Ship> ships, Field field) {
        for (Ship ship : ships) {
            Coordinates[] shipCoordinates = new Coordinates[ship.getSize()];

            if (ship.getShipPosition() == ShipPosition.VERTICAL) {
                for (int i = 0; i < ship.getSize(); i++) {
                    shipCoordinates[i] = new Coordinates(ship.getStartCoordinates().getX() + i,
                            ship.getStartCoordinates().getY());
                }
            }

            if (ship.getShipPosition() == ShipPosition.HORIZONTAL) {
                for (int i = 0; i < ship.getSize(); i++) {
                    shipCoordinates[i] = new Coordinates(ship.getStartCoordinates().getX(),
                            ship.getStartCoordinates().getY() + i);
                }
            }

            ship.setCoordinates(shipCoordinates);

            for (Coordinates coordinates : shipCoordinates) {
                field.getCells()[coordinates.getX()][coordinates.getY()].setCellState(CellState.SHIP);
                field.getCells()[coordinates.getX()][coordinates.getY()].setShip(ship);
            }
        }
        field.setShipsLeft(ships.size());
    }
}
