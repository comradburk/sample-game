package app.game.repositories;

import app.game.models.Game;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class InMemoryGameRepository implements IGameRepository {
    private Map<UUID, Game> games;

    InMemoryGameRepository(){
        games = new HashMap<>();
    }

    @Override
    public Game saveGame(Game game) {
        games.put(game.getId(), game);

        return game;
    }

    @Override
    public Optional<Game> getGameById(UUID id) {
        if (!games.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(games.get(id));
    }

    @Override
    public void deleteGame(UUID id) {
        if (!games.containsKey(id)) {
            throw new NoSuchElementException("Game does not exist");
        }

        games.remove(id);
    }
}
