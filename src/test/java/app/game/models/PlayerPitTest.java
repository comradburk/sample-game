package app.game.models;

import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPitTest {
    @Test
    void ShouldPlayPitByMovingStones() {
        var playerSidePit = new StonePitFactory().buildPlayerSide(6, 2);


        StonePit lastPitMoved = null;
        try {
            lastPitMoved = playerSidePit.playPit();
        } catch (OperationNotSupportedException e) {
            fail();
        }


        assertEquals(0, playerSidePit.getStoneCount());
        playerSidePit = playerSidePit.getNextPit();
        assertEquals(3, playerSidePit.getStoneCount());
        playerSidePit = playerSidePit.getNextPit();
        assertEquals(3, playerSidePit.getStoneCount());
        assertSame(playerSidePit, lastPitMoved);
        playerSidePit = playerSidePit.getNextPit();
        assertEquals(2, playerSidePit.getStoneCount());
        playerSidePit = playerSidePit.getNextPit();
        assertEquals(2, playerSidePit.getStoneCount());
        playerSidePit = playerSidePit.getNextPit();
        assertEquals(2, playerSidePit.getStoneCount());
        playerSidePit = playerSidePit.getNextPit();
        assertEquals(0, playerSidePit.getStoneCount());
    }
}
