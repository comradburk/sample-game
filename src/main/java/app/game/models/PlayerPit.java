package app.game.models;

import javax.naming.OperationNotSupportedException;

public class PlayerPit extends StonePit {
    public PlayerPit(int startingStones, StonePit nextPit, GamePlayer pitOwner) {
        super(startingStones, nextPit, pitOwner);
    }

    public StonePit playPit(GamePlayer player) throws OperationNotSupportedException {
        if (stones == 0) {
            throw new OperationNotSupportedException("Cannot play an empty pit!");
        }
		if (pitOwner != player) {
			throw new OperationNotSupportedException("Cannot play another players pit!");
		}

        var stonesToMove = this.stones;
        this.stones = 0;

        return nextPit.distributeStones(stonesToMove, player);
    }

    public int captureStones() {
        var stonesToCapture = stones;
        stones = 0;

        return stonesToCapture;
    }
}
