package app.game.models;

import javax.naming.OperationNotSupportedException;

public abstract class StonePit {
    int stones;
    StonePit nextPit;
	GamePlayer _pitOwner;

    protected StonePit(int startingStones, StonePit nextPit, GamePlayer pitOwner) {
        stones = startingStones;
        this.nextPit = nextPit;
    }
	
	public GamePlayer getPitOwner() { 
		return _pitOwner;
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
        this.stones = this.stones + 1;

        var stonesLeft = stones - 1;
        return stonesLeft == 0 ? this : nextPit.distributeStones(stones - 1);
    }

    public StonePit getNextPit() {
        return nextPit;
    }
}
