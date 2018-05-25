package app.game.models;

import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScorePitTest {
    @Test
    void ShouldAddCapturedStonesToTotal() {
        var scorePit = new ScorePit();


        scorePit.AddCapturedStones(10);


        assertEquals(10, scorePit.getStoneCount());
    }

    @Test
    void ShouldNotAllowCapturingStones() {
        var scorePit = new ScorePit();


        assertThrows(OperationNotSupportedException.class, () -> scorePit.captureStones());
    }

    @Test
    void ShouldNotAllowPlayingPit() {
        var scorePit = new ScorePit();


        assertThrows(OperationNotSupportedException.class, () -> scorePit.playPit());
    }
}
