package app.game.models;

import app.game.util.GameUtilities;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    @Test
    void shouldRequireValidBoardSize() {
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(-10, 5));
    }

    @Test
    void shouldRequireValidStartingStones() {
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(7, -5));
    }

    @Test
    void shouldCreateDefaultBoard() {
        var board = new GameBoard(6, 6);

        assertEquals(GamePlayer.PLAYER_ONE, board.getCurrentPlayer());

        var pitToCheck = board.getPlayerPits().get(GamePlayer.PLAYER_ONE);
        // Loop through pits beginning with player one and check that all player
        // pits start with the initial value and all score pits start with 0
        for (var i = 0; i < 6; i++) {
            assertEquals(6, pitToCheck.getStoneCount());
            pitToCheck = pitToCheck.getNextPit();
        }
        assertEquals(0, pitToCheck.getStoneCount());
        pitToCheck = pitToCheck.getNextPit();
        for (var i = 0; i < 6; i++) {
            assertEquals(6, pitToCheck.getStoneCount());
            pitToCheck = pitToCheck.getNextPit();
        }
        assertEquals(0, pitToCheck.getStoneCount());
    }

    @Test
    void shouldBeGameOverWhenPitsEmpty() {
        var game = new GameBoard(6, 6);

        assertSame(game.getGameState(), GameState.ACTIVE);

        var currentPit = game.getPlayerPits().get(GamePlayer.PLAYER_ONE);
        for (var i = 0; i < 6; i++) {
            currentPit.setStoneCount(0);
            currentPit = currentPit.getNextPit();
        }

        assertSame(game.getGameState(), GameState.GAME_OVER);
    }

    @Test
    void shouldCaptureStonesFromLastPlayedAndOpposite() {
        var game = new GameBoard(6, 6);
        // This pit will act as the one a stone was place in
        var lastPlacedPit = game.getPlayerPits().get(GamePlayer.PLAYER_ONE);
        lastPlacedPit.setStoneCount(1);


        try {
            game.captureStones(GamePlayer.PLAYER_ONE, lastPlacedPit);
        } catch (OperationNotSupportedException e) {
            fail();
        }

        assertEquals(0, lastPlacedPit.getStoneCount());
        assertEquals(7, GameUtilities.GetPlayerScorePit(lastPlacedPit).getStoneCount());

        var currentOtherPlayerPit = game.getPlayerPits().get(GamePlayer.PLAYER_TWO);
        for (var i = 0; i < 5; i++) {
            currentOtherPlayerPit = currentOtherPlayerPit.getNextPit();
        }
        assertEquals(0, currentOtherPlayerPit.getStoneCount());
    }

    @Test
    void shouldGetOppositePit() {
        var game = new GameBoard(6, 6);


        var oppositePit = game.getOppositePit(GamePlayer.PLAYER_ONE, game.getPlayerPits().get(GamePlayer.PLAYER_ONE));

        var currentOtherPlayerPit = game.getPlayerPits().get(GamePlayer.PLAYER_TWO);
        for (var i = 0; i < 5; i++) {
            currentOtherPlayerPit = currentOtherPlayerPit.getNextPit();
        }
        assertSame(oppositePit, currentOtherPlayerPit);
    }

    @Test
    void shouldPlayFromPit() {

    }
}
