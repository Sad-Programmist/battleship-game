package ru.caselab.view;

import ru.caselab.Coordinates;
import ru.caselab.Field;
import ru.caselab.player.Player;

public interface View {

    void renderPlayerField(Field field);

    void renderEnemyField(Field field);

    void renderPlayer(Player player);

    void renderMove(Player currentPlayer, Player enemy);

    void renderMoveResult(Player currentPlayer, Player enemy, Coordinates moveCoordinates);

    void renderWinner(Player player);
}

