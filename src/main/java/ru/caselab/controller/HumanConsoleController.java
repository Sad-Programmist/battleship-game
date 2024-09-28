package ru.caselab.controller;

import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.Ship;
import ru.caselab.enumeration.ShipPosition;

import java.util.Scanner;

public class HumanConsoleController implements HumanController {

    private final Scanner scanner;

    public HumanConsoleController() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean requestAutoPlacing() {
        System.out.print("Do you want auto place ships? (y/n) ");
        String input = scanner.next().toLowerCase();

        while (input.length() != 1 || (input.charAt(0) != 'y' && input.charAt(0) != 'n')) {
            System.out.println("Answer must be a single letter 'y' or 'n'");
            System.out.print("Do you want auto place ships? (y/n) ");
            input = scanner.next().toLowerCase();
        }
        System.out.println();

        return input.charAt(0) == 'y';
    }

    @Override
    public Ship requestPlacingShip(int size) {
        System.out.println("Place " + size + "-deck ship");

        Coordinates startCoordinates = requestCoordinates();
        ShipPosition shipPosition = ShipPosition.HORIZONTAL;

        if (size > 1) {
            shipPosition = requestPosition();
        }
        System.out.println();

        return new Ship(size, startCoordinates, shipPosition);
    }

    @Override
    public Coordinates requestMoveCoordinates() {
        return requestCoordinates();
    }

    @Override
    public void showError(String text) {
        System.out.println(text + "\n");
    }

    private Coordinates requestCoordinates() {
        char x = requestXCoordinate();
        int y = requestYCoordinate();

        return new Coordinates(x - 'A', y - 1);
    }

    private char requestXCoordinate() {
        System.out.print("Enter X coordinate (A-J): ");
        String input = scanner.next().toUpperCase();

        while (input.length() != 1 || input.charAt(0) < 'A' || input.charAt(0) > 'J') {
            System.out.println("X must be a single letter between A and J");
            System.out.print("Enter X coordinate (A-J): ");
            input = scanner.next().toUpperCase();
        }

        return input.charAt(0);
    }

    private int requestYCoordinate() {
        System.out.print("Enter Y coordinate (1-10): ");

        while (!scanner.hasNextInt()) {
            System.out.println("Y must be a number between 1 and 10");
            System.out.print("Enter Y coordinate (1-10): ");
            scanner.next();
        }

        int y = scanner.nextInt();

        while (y < 1 || y > Field.SIZE) {
            System.out.println("Y must be between 1 and " + Field.SIZE);
            System.out.print("Enter Y coordinate (1-10): ");
            y = scanner.nextInt();
        }

        return y;
    }

    private ShipPosition requestPosition() {
        System.out.print("Enter ship position (h/v): ");
        String input = scanner.next().toLowerCase();

        while (input.length() != 1 || (input.charAt(0) != 'h' && input.charAt(0) != 'v')) {
            System.out.println("Position must be a single letter 'h' or 'v'");
            System.out.print("Enter ship position (h/v): ");
            input = scanner.next().toLowerCase();
        }

        return input.charAt(0) == 'h' ? ShipPosition.HORIZONTAL : ShipPosition.VERTICAL;
    }
}
