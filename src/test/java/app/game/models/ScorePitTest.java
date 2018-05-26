package app.game.models;

import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScorePitTest {
    @Test
    void shouldAddCapturedStonesToTotal() {
        var scorePit = new ScorePit(GamePlayer.PLAYER_ONE);


        scorePit.addCapturedStones(10);


        assertEquals(10, scorePit.getStoneCount());
    }
	
	@Test
	void shouldDistributeStoneFromOwnerPlayerIntoPit() {
		var scorePit = new ScorePit(GamePlayer.PLAYER_ONE);
		
		
		scorePit.distributeStones(1, GamePlayer.PLAYER_ONE);
		
		
		assertEquals(1, scorePit.getStoneCount());
	}
	
	@Test
	void shouldNotDistributeStoneFromAnotherPlayerIntoPit() {
		var scorePit = new ScorePit(GamePlayer.PLAYER_TWO);
		var testNextPit = new PlayerPit(0, null, GamePlayer.PLAYER_ONE);
		scorePit.setNextPit(testNextPit);
		
		
		scorePit.distributeStones(1, GamePlayer.PLAYER_ONE);
		
		
		assertEquals(0, scorePit.getStoneCount());
	}

    @Test
    void shouldNotAllowCapturingStones() {
        var scorePit = new ScorePit(GamePlayer.PLAYER_ONE);


        assertThrows(OperationNotSupportedException.class, () -> scorePit.captureStones());
    }

    @Test
    void shouldNotAllowPlayingPit() {
        var scorePit = new ScorePit(GamePlayer.PLAYER_ONE);


        assertThrows(OperationNotSupportedException.class, () -> scorePit.playPit(GamePlayer.PLAYER_ONE));
    }
}
