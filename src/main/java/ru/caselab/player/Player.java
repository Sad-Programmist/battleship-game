package ru.caselab.player;

import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.Placer;
import ru.caselab.Ship;
import ru.caselab.state.CellState;
import ru.caselab.state.ShipPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Player {

    protected String name;
    protected Field field;
    protected Placer placer;

    public abstract void placeShips();

    public abstract Coordinates makeMove();

    public abstract void prepare();

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }
}
