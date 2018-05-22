package app.game.repositories;

import app.game.models.Game;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IGameRepository {
    Optional<Game> getGameById(UUID id);

    Collection<Game> getGames();

    Game saveGame(Game game);

    void deleteGame(UUID id);
}

