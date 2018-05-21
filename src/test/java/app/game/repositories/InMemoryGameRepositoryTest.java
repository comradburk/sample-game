package app.game.repositories;

import app.game.models.Game;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryGameRepositoryTest {
    @Test
    void shouldSaveGame() {
        var repository = new InMemoryGameRepository();

        var game = new Game(7, 6);
        var savedGame = repository.saveGame(game);

        assertEquals(game, savedGame);
    }

    @Test
    void shouldGetGameById() {
        var repository = new InMemoryGameRepository();

        var game = new Game(7, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        var retrievedGame = repository.getGameById(gameId);

        assertTrue(retrievedGame.isPresent());
        assertEquals(gameId, retrievedGame.get().getId());
    }

    @Test
    void shouldGetEmptyGameIfNotExists(){
        var repository = new InMemoryGameRepository();

        var game = new Game(7, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        var retrievedGame = repository.getGameById(UUID.randomUUID());

        assertFalse(retrievedGame.isPresent());
    }

    @Test
    void shouldDeleteGame() {
        var repository = new InMemoryGameRepository();

        var game = new Game(7, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        repository.deleteGame(gameId);

        var retrievedGame = repository.getGameById(gameId);
        assertFalse(retrievedGame.isPresent());
    }

    @Test
    void shouldThrowWhenDeletingNonExisting() {
        var repository = new InMemoryGameRepository();

        var game = new Game(7, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        assertThrows(NoSuchElementException.class, () -> repository.deleteGame(UUID.randomUUID()));
    }
}