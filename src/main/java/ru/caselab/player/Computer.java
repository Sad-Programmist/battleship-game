package ru.caselab.player;

import ru.caselab.*;

import java.util.Random;

public class Computer extends Player{


    public Computer(String name) {
        super(name);
    }

    @Override
    public void placeShips() {
        placer.autoPlaceShips(field);
    }

    @Override
    public Coordinates makeMove() {
        Random random = new Random();
        return new Coordinates(random.nextInt(Field.SIZE), random.nextInt(Field.SIZE));
    }
}
