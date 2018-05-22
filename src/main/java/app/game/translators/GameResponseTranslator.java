package app.game.translators;

import app.game.dto.GameResponse;
import app.game.models.Game;

import javax.inject.Singleton;

@Singleton
public class GameResponseTranslator {
    public GameResponse Translate(Game game) {
        var gameResponse = new GameResponse();

        gameResponse.id = game.getId();
        gameResponse.pits = game.getPits().stream().mapToInt(Integer::intValue).toArray();
        gameResponse.currentPlayer = game.getCurrentPlayer();
        gameResponse.gameState = game.getGameState();

        return gameResponse;
    }
}
