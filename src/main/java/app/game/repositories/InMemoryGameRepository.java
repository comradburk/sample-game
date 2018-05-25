package app.game.repositories;

import app.game.models.GameBoard;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class InMemoryGameRepository implements IGameRepository {
    private Map<UUID, GameBoard> games;

    InMemoryGameRepository() {
        games = new HashMap<>();
    }

    @Override
    public Optional<GameBoard> getGameById(UUID id) {
        if (!games.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(games.get(id));
    }

    @Override
    public Collection<GameBoard> getGames() {
        return games.values();
    }

    @Override
    public GameBoard saveGame(GameBoard game) {
        games.put(game.getId(), game);

        return game;
    }

    @Override
    public void deleteGame(UUID id) {
        if (!games.containsKey(id)) {
            throw new NoSuchElementException("Game does not exist");
        }

        games.remove(id);
    }
}
