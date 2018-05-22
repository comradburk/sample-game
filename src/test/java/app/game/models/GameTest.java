package app.game.models;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldRequireValidBoardSize() {
        assertThrows(IllegalArgumentException.class, () -> new Game(-10, 5));
    }

    @Test
    void shouldRequireValidStartingStones() {
        assertThrows(IllegalArgumentException.class, () -> new Game(7, -5));
    }

    @Test
    void shouldMoveStones() {
    }

    @Test
    void shouldCaptureStones() {
        var lastPlacedPitIndex = 1;
        var oppositePitIndex = 11;
        var game = new Game(7, 6);
        // This pit will act as the one a stone was place in
        game.getPits().set(lastPlacedPitIndex, 1);


        game.captureStones(lastPlacedPitIndex);

        // Assert stones were captured
        assertEquals(0, (int)game.getPits().get(lastPlacedPitIndex));
        assertEquals(0, (int)game.getPits().get(oppositePitIndex));
        assertEquals(7, (int)game.getPits().get(6));

        // Assert all other pits are the same
        assertEquals(6, (int)game.getPits().get(0));
        assertEquals(6, (int)game.getPits().get(2));
        assertEquals(6, (int)game.getPits().get(3));
        assertEquals(6, (int)game.getPits().get(4));
        assertEquals(6, (int)game.getPits().get(5));
        
        assertEquals(6, (int)game.getPits().get(7));
        assertEquals(6, (int)game.getPits().get(8));
        assertEquals(6, (int)game.getPits().get(9));
        assertEquals(6, (int)game.getPits().get(10));
        assertEquals(6, (int)game.getPits().get(12));
        assertEquals(0, (int)game.getPits().get(13));
    }

    @Test
    void shouldGetNextPitIndex() {
        var game = new Game(7, 6);

        assertEquals(1, game.nextPitIndex(0));
        assertEquals(0, game.nextPitIndex(13));

        assertThrows(NoSuchElementException.class, () -> game.nextPitIndex(15));
    }

    @Test
    void shouldGetOppositePitIndex() {
        var game = new Game(7, 6);

        assertEquals(12, game.oppositePitIndex(0));
        assertEquals(7, game.oppositePitIndex(5));
        assertEquals(5, game.oppositePitIndex(7));
        assertEquals(0, game.oppositePitIndex(12));

        assertThrows(NoSuchElementException.class, () -> game.oppositePitIndex(15));
    }

    @Test
    void shouldGetIsScorePit() {
        var game = new Game(7, 6);

        assertFalse(game.isScorePit(0));
        assertFalse(game.isScorePit(11));
        assertTrue(game.isScorePit(6));
        assertTrue(game.isScorePit(13));

        assertThrows(NoSuchElementException.class, () -> game.isScorePit(15));
    }

    @Test
    void shouldBeGameOverWhenPitsEmpty() {
        var game = new Game(7, 6);

        assertSame(game.getGameState(), GameState.ACTIVE);

        for (var i = 0; i < 7; i++) {
            game.getPits().set(i, 0);
        }

        assertSame(game.getGameState(), GameState.GAME_OVER);
    }
}