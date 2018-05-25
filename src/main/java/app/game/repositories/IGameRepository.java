package app.game.repositories;

import app.game.models.GameBoard;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IGameRepository {
    Optional<GameBoard> getGameById(UUID id);

    Collection<GameBoard> getGames();

    GameBoard saveGame(GameBoard game);

    void deleteGame(UUID id);
}

