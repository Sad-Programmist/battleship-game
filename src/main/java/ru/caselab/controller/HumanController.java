package ru.caselab.controller;

import ru.caselab.Coordinates;
import ru.caselab.Ship;

public interface HumanController {

    boolean requestAutoPlacing();
    Ship requestPlacingShip(int size);
    Coordinates requestMoveCoordinates();
    void showError(String text);
}
