package app.game.translators;

import app.game.dto.GameResponse;
import app.game.models.GameBoard;
import app.game.models.GamePlayer;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Singleton
public class GameResponseTranslator {
    public GameResponse Translate(GameBoard game) {
        var gameResponse = new GameResponse();

        gameResponse.id = game.getId();
        gameResponse.pits = new HashMap<GamePlayer, List<Integer>>();
        gameResponse.currentPlayer = game.getCurrentPlayer();
        gameResponse.gameState = game.getGameState();

        for (var player : game.getPlayerPits().keySet()) {
            var playerPits = new ArrayList<Integer>();

            var currentPit = game.getPlayerPits().get(player);
            for (var i = 0; i < game.getPlayerPitCount() + 1; i++) {
                playerPits.add(currentPit.getStoneCount());
                currentPit = currentPit.getNextPit();
            }

            gameResponse.pits.put(player, playerPits);
        }

        return gameResponse;
    }
}
