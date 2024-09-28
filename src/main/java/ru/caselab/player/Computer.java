package ru.caselab.player;

import ru.caselab.Cell;
import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.Ship;
import ru.caselab.state.CellState;
import ru.caselab.state.ShipPosition;

import java.util.List;
import java.util.Random;

public class Computer extends Player{

    public Computer(String name) {
        this.name = name;
        field = new Field();
    }

    @Override
    public void prepare() {

    }

    @Override
    public void placeShips() {
        Cell[][] cells = field.getCells();
        // four-deck ship
        Coordinates coordinates1 = new Coordinates(0,0);
        Coordinates coordinates2 = new Coordinates(1,0);
        Coordinates coordinates3 = new Coordinates(2,0);
        Coordinates coordinates4 = new Coordinates(3,0);

        cells[coordinates1.getX()][coordinates1.getY()].setCellState(CellState.SHIP);
        cells[coordinates2.getX()][coordinates2.getY()].setCellState(CellState.SHIP);
        cells[coordinates3.getX()][coordinates3.getY()].setCellState(CellState.SHIP);
        cells[coordinates4.getX()][coordinates4.getY()].setCellState(CellState.SHIP);

        Ship ship = new Ship(4, coordinates1, ShipPosition.VERTICAL);
        Coordinates[] coordinates = new Coordinates[]{coordinates1, coordinates2, coordinates3, coordinates4};
        ship.setCoordinates(coordinates);

        cells[coordinates1.getX()][coordinates1.getY()].setShip(ship);
        cells[coordinates2.getX()][coordinates2.getY()].setShip(ship);
        cells[coordinates3.getX()][coordinates3.getY()].setShip(ship);
        cells[coordinates4.getX()][coordinates4.getY()].setShip(ship);

        field.setShipsLeft(1);
    }

    @Override
    public Coordinates makeMove() {
        Random random = new Random();
        return new Coordinates(random.nextInt(Field.SIZE), random.nextInt(Field.SIZE));
    }
}
