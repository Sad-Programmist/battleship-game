package ru.caselab.controller;

import ru.caselab.Coordinates;
import ru.caselab.Ship;
import ru.caselab.state.ShipPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanConsoleController implements HumanController {

    private final Scanner scanner;

    public HumanConsoleController() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String requestName() {
        System.out.print("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public List<Ship> requestPlacingShips() {
        List<Ship> ships = new ArrayList<>();

        for (int size = 4; size > 0; size--) {
            for (int shipNumber = 4 - size + 1; shipNumber > 0; shipNumber--) {
                System.out.println("Number of the " + size + "-deck ship to place: " + (shipNumber));
                Coordinates startCoordinates = requestCoordinates();
                ShipPosition shipPosition = ShipPosition.HORIZONTAL;
                if (size > 1) {
                    shipPosition = requestPosition();
                }
                ships.add(new Ship(size, startCoordinates, shipPosition));
            }
        }
        System.out.println("Ship placing is done");
        System.out.println();

        return ships;
    }

    @Override
    public Coordinates requestMoveCoordinates() {
        return requestCoordinates();
    }

    private Coordinates requestCoordinates() {
        System.out.print("Enter X coordinate: ");
        char x = scanner.next().charAt(0);

        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();

        return new Coordinates(x - 'A', y - 1);
    }

    private ShipPosition requestPosition() {
        System.out.print("Enter ship position (h/v): ");
        char shipPosition = scanner.next().charAt(0);
        if (shipPosition == 'h') {
            return ShipPosition.HORIZONTAL;
        } else {
            return ShipPosition.VERTICAL;
        }
    }
}
