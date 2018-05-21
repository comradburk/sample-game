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
    void moveStones() {
    }

    @Test
    void shouldCaptureStones() {

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

        assertSame(game.getGameState(), GameState.Active);

        for (var i = 0; i < 7; i++) {
            game.getPits().set(i, 0);
        }

        assertSame(game.getGameState(), GameState.GameOver);
    }
}