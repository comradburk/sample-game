package app.game.models;

import javax.naming.OperationNotSupportedException;

public abstract class StonePit {
    int stones;
    StonePit nextPit;
	GamePlayer pitOwner;

    protected StonePit(int startingStones, StonePit nextPit, GamePlayer pitOwner) {
        stones = startingStones;
        this.nextPit = nextPit;
        this.pitOwner = pitOwner;
    }
	
	public GamePlayer getPitOwner() { 
		return pitOwner;
	}

    public int getStoneCount() {
        return stones;
    }

    public void setStoneCount(int stones) {
        this.stones = stones;
    }

    public abstract int captureStones() throws OperationNotSupportedException;

    public abstract StonePit playPit(GamePlayer player) throws OperationNotSupportedException;

    public StonePit distributeStones(int stones, GamePlayer player) {
        this.stones++;
        stones--;

        return stones == 0 ? this : nextPit.distributeStones(stones, player);
    }

    public StonePit getNextPit() {
        return nextPit;
    }
}
