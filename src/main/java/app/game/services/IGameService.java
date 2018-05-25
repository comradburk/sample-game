package app.game.services;

import app.game.models.GameBoard;
import app.game.models.GamePlayer;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IGameService {
    GameBoard createGame();

    void cancelGame(UUID id);

    Optional<GameBoard> getGameById(UUID id);

    Collection<GameBoard> getGames();

    GameBoard performMove(UUID gameId, GamePlayer player, int playerPitIndex) throws OperationNotSupportedException;
}
