package app.game.services;

import app.game.models.GameBoard;
import app.game.models.GamePlayer;
import app.game.models.GameState;
import app.game.repositories.IGameRepository;
import app.game.util.GameUtilities;
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
        when(gameRepository.saveGame(any(GameBoard.class))).thenAnswer(invocation -> invocation.getArgument(0));


        var createdGame = gameService.createGame();

        assertEquals(GamePlayer.PLAYER_ONE, createdGame.getCurrentPlayer());

        // count created pits
        var pitCount = 1;
        var currentPit = createdGame.getPlayerPits().get(GamePlayer.PLAYER_ONE);
        var nextPit = currentPit.getNextPit();
        while (nextPit != currentPit) {
            pitCount++;
            nextPit = nextPit.getNextPit();
        }
        assertEquals(totalPits, pitCount);

        assertEquals(startingStones, createdGame.getPlayerPits().get(GamePlayer.PLAYER_ONE).getStoneCount());
        assertEquals(0, GameUtilities.GetPlayerScorePit(createdGame.getPlayerPits().get(GamePlayer.PLAYER_ONE)).getStoneCount());
    }

    @Test
    void shouldPreventIncorrectPlayerMove() {
        var gameId = UUID.randomUUID();
        var game = new GameBoard(6, 6);
        assertEquals(GamePlayer.PLAYER_ONE, game.getCurrentPlayer());
        when(gameRepository.getGameById(gameId)).thenReturn(Optional.of(game));

        assertThrows(OperationNotSupportedException.class,
                () -> gameService.performMove(gameId, GamePlayer.PLAYER_TWO, 0));
    }

    @Test
    void shouldPreventMovesInFinishedGame() {
        var gameId = UUID.randomUUID();
        var game = new GameBoard(6, 6);
        when(gameRepository.getGameById(gameId)).thenReturn(Optional.of(game));

        // Clear player pits to finish game
        var currentPit = game.getPlayerPits().get(GamePlayer.PLAYER_ONE);
        for (var i = 0; i < 6; i++) {
            currentPit.setStoneCount(0);
            currentPit = currentPit.getNextPit();
        }
        assertSame(GameState.GAME_OVER, game.getGameState());


        assertThrows(OperationNotSupportedException.class,
                () -> gameService.performMove(gameId, GamePlayer.PLAYER_ONE, 0));
    }

    @Test
    void shouldPreventMovesFromEmptyPit() {
        var gameId = UUID.randomUUID();
        var game = new GameBoard(6, 6);
        game.getPlayerPits().get(GamePlayer.PLAYER_ONE).setStoneCount(0);
        when(gameRepository.getGameById(gameId)).thenReturn(Optional.of(game));


        assertThrows(OperationNotSupportedException.class,
                () -> gameService.performMove(gameId, GamePlayer.PLAYER_ONE, 0));
    }

    @Test
    void shouldPerformValidMove() {

    }
}