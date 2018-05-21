package app.game.repositories;

import app.game.models.Game;

import java.util.Optional;
import java.util.UUID;

public interface IGameRepository {
    Game saveGame(Game game);

    Optional<Game> getGameById(UUID id);

    void deleteGame(UUID id);
}

