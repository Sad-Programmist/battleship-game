package ru.caselab.player;

import ru.caselab.Coordinates;
import ru.caselab.Ship;
import ru.caselab.controller.HumanController;

public class Human extends Player {

    private final HumanController humanController;

    public Human(String name, HumanController humanController) {
        super(name);
        this.humanController = humanController;
    }

    @Override
    public void placeShips() {
        if (humanController.requestAutoPlacing()) {
            placer.autoPlaceShips(field);
            return;
        }

        int[] shipSizes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int shipSize : shipSizes) {
                Ship ship = humanController.requestPlacingShip(shipSize);
                while (!placer.manualPlace(ship, field)) {
                    humanController.showError("Ship cannot go outside the field, overlap, or touch another ship");
                    ship = humanController.requestPlacingShip(shipSize);
                }

        }
    }

    @Override
    public Coordinates makeMove() {
        return humanController.requestMoveCoordinates();
    }
}
