package ru.caselab.controller;

import ru.caselab.Coordinates;
import ru.caselab.Ship;

import java.util.List;

public interface HumanController {

    String requestName();
    List<Ship> requestPlacingShips();
    Coordinates requestMoveCoordinates();
}
