package app.game.repositories;

import app.game.models.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryGameRepositoryTest {
    private InMemoryGameRepository repository;

    @BeforeEach
    void setup(){
        repository = new InMemoryGameRepository();
    }

    @Test
    void shouldSaveGame() {
        var game = new GameBoard(6, 6);
        var savedGame = repository.saveGame(game);

        assertEquals(game, savedGame);
    }

    @Test
    void shouldGetGameById() {
        var game = new GameBoard(6, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        var retrievedGame = repository.getGameById(gameId);

        assertTrue(retrievedGame.isPresent());
        assertEquals(gameId, retrievedGame.get().getId());
    }

    @Test
    void shouldGetAllGames() {
        Stream.generate(() -> {
            var newGame = new GameBoard(6, 6);
            newGame.setId(UUID.randomUUID());
            return newGame;
        })
                .limit(4)
                .forEach(repository::saveGame);


        var retrievedGames = repository.getGames();


        assertEquals(4, retrievedGames.size());
    }

    @Test
    void shouldGetEmptyGameIfNotExists(){
        var game = new GameBoard(6, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        var retrievedGame = repository.getGameById(UUID.randomUUID());

        assertFalse(retrievedGame.isPresent());
    }

    @Test
    void shouldDeleteGame() {
        var game = new GameBoard(6, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        repository.deleteGame(gameId);

        var retrievedGame = repository.getGameById(gameId);
        assertFalse(retrievedGame.isPresent());
    }

    @Test
    void shouldThrowWhenDeletingNonExisting() {
        var game = new GameBoard(6, 6);
        var gameId = UUID.randomUUID();
        game.setId(gameId);
        repository.saveGame(game);

        assertThrows(NoSuchElementException.class, () -> repository.deleteGame(UUID.randomUUID()));
    }
}