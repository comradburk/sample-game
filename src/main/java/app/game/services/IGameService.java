package app.game.services;

import app.game.models.Game;
import app.game.models.GamePlayer;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IGameService {
    Game createGame();

    void cancelGame(UUID id);

    Optional<Game> getGameById(UUID id);

    Collection<Game> getGames();

    Game performMove(UUID gameId, GamePlayer player, int playerPitIndex) throws OperationNotSupportedException;
}
