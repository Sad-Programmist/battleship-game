package ru.caselab;

import ru.caselab.controller.HumanConsoleController;
import ru.caselab.player.Computer;
import ru.caselab.player.Human;
import ru.caselab.player.Player;
import ru.caselab.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Computer("Computer 1");
        //Player player2 = new Computer("Computer 2");
        Player player2 = new Human(new HumanConsoleController());

        Game game = new Game(player1, player2, new ConsoleView());
        game.play();
    }
}