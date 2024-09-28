package ru.caselab.player;

import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.Placer;
import ru.caselab.Ship;
import ru.caselab.controller.HumanController;

import java.util.List;

public class Human extends Player {

    private HumanController humanController;

    public Human(HumanController humanController) {
        this.humanController = humanController;
        placer = new Placer();
        field = new Field();
    }

    @Override
    public void prepare() {
        name = humanController.requestName();
    }

    @Override
    public void placeShips() {
        List<Ship> ships = humanController.requestPlacingShips();
        placer.manualPlace(ships, field);
    }

    @Override
    public Coordinates makeMove() {
        return humanController.requestMoveCoordinates();
    }
}
