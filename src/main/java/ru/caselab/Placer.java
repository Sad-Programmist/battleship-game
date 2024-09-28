package ru.caselab;

import ru.caselab.enumeration.CellState;
import ru.caselab.enumeration.ShipPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Placer {

    public boolean manualPlace(Ship ship, Field field) {
        List<Cell> shipCells = checkPlace(ship, field);
        if (shipCells.isEmpty()) {
            return false;
        }

        ship.setCells(shipCells);

        for (Cell cell : shipCells) {
            cell.setCellState(CellState.SHIP);
            cell.setShip(ship);
        }

        field.setShipsLeft(field.getShipsLeft() + 1);
        return true;
    }

    public void autoPlaceShips(Field field) {
        Random random = new Random();

        int[] shipSizes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int size : shipSizes) {
            boolean placed = false;

            while (!placed) {
                int x = random.nextInt(Field.SIZE);
                int y = random.nextInt(Field.SIZE);

                ShipPosition position = random.nextBoolean() ? ShipPosition.HORIZONTAL : ShipPosition.VERTICAL;
                Ship ship = new Ship(size, new Coordinates(x, y), position);

                placed = manualPlace(ship, field);
            }
        }
    }


    private List<Cell> checkPlace(Ship ship, Field field) {
        List<Cell> shipCells = new ArrayList<>();
        Coordinates startCoordinates = ship.getStartCoordinates();
        int shipSize = ship.getSize();

        int deltaX = ship.getShipPosition() == ShipPosition.VERTICAL ? 1 : 0;
        int deltaY = ship.getShipPosition() == ShipPosition.HORIZONTAL ? 1 : 0;

        if (startCoordinates.x() + deltaX * shipSize > Field.SIZE ||
                startCoordinates.y() + deltaY * shipSize > Field.SIZE) {
            return Collections.emptyList();
        }

        for (int deck = 0; deck < shipSize; deck++) {
            int currentX = startCoordinates.x() + deck * deltaX;
            int currentY = startCoordinates.y() + deck * deltaY;

            if (!checkSurroundingCells(currentX, currentY, field)) {
                return Collections.emptyList();
            }

            shipCells.add(field.getCells()[currentX][currentY]);
        }

        return shipCells;
    }

    private boolean checkSurroundingCells(int x, int y, Field field) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int checkX = x + dx;
                int checkY = y + dy;

                if (checkX >= 0 && checkX < Field.SIZE && checkY >= 0 && checkY < Field.SIZE) {
                    if (field.getCells()[checkX][checkY].getCellState() == CellState.SHIP) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
