package ru.caselab.player;

import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.Placer;

public abstract class Player {

    protected String name;
    protected Field field;
    protected Placer placer;

    public Player(String name) {
        this.name = name;
        field = new Field();
        placer = new Placer();
    }

    public abstract void placeShips();

    public abstract Coordinates makeMove();

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }
}
