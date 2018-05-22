package app.game.services;

import app.game.models.Game;
import app.game.models.GamePlayer;
import app.game.models.GameState;
import app.game.repositories.IGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceTest {
    private GameService gameService;

    // Dependencies
    private IGameRepository gameRepository;

    @BeforeEach
    void setup(){
        gameRepository = mock(IGameRepository.class);
        gameService = new GameService(gameRepository);
    }

    @Test
    void shouldCreateDefaultGame() {
        var totalPits = 14;
        var startingStones = 6;
        when(gameRepository.saveGame(any(Game.class))).thenAnswer(invocation ->  invocation.getArgument(0));


        var createdGame = gameService.createGame();

        assertEquals(GamePlayer.PLAYER_ONE, createdGame.getCurrentPlayer());
        assertEquals(totalPits, createdGame.getPits().size());
        assertEquals(startingStones, (int)createdGame.getPits().get(0));
        assertEquals(0, (int)createdGame.getPits().get(totalPits/2 - 1));
    }

    @Test
    void shouldPreventIncorrectPlayerMove() {
        var gameId = UUID.randomUUID();
        var game = new Game(7, 6);
        game.setCurrentPlayer(GamePlayer.PLAYER_ONE);
        when(gameRepository.getGameById(gameId)).thenReturn(Optional.of(game));

        assertThrows(OperationNotSupportedException.class,
                () -> gameService.performMove(gameId, GamePlayer.PLAYER_TWO, 0));
    }

    @Test
    void shouldPreventMovesInFinishedGame() {
        var gameId = UUID.randomUUID();
        var game = new Game(7, 6);
        game.setCurrentPlayer(GamePlayer.PLAYER_ONE);
        when(gameRepository.getGameById(gameId)).thenReturn(Optional.of(game));

        // Clear player pits to finish game
        for (var i = 0; i < 7; i++) {
            game.getPits().set(i, 0);
        }
        assertSame(GameState.GAME_OVER, game.getGameState());


        assertThrows(OperationNotSupportedException.class,
                () -> gameService.performMove(gameId, GamePlayer.PLAYER_ONE, 0));
    }

    @Test
    void shouldPreventMovesFromEmptyPit() {
        var gameId = UUID.randomUUID();
        var game = new Game(7, 6);
        game.setCurrentPlayer(GamePlayer.PLAYER_ONE);
        game.getPits().set(0, 0);
        when(gameRepository.getGameById(gameId)).thenReturn(Optional.of(game));


        assertThrows(OperationNotSupportedException.class,
                () -> gameService.performMove(gameId, GamePlayer.PLAYER_ONE, 0));
    }

    @Test
    void shouldPerformValidMove() {

    }
}