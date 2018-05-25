package app.game.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StonePitTest {
    @Test
    void shouldDropSingleStoneIntoPit() {
        var playerSidePit = new PlayerPit(0, new PlayerPit(0, null));


        playerSidePit.distributeStones(2);


        assertEquals(1, playerSidePit.getStoneCount());
        assertEquals(1, playerSidePit.getNextPit().getStoneCount());
    }
}
