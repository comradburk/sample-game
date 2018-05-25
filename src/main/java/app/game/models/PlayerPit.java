package app.game.models;

import javax.naming.OperationNotSupportedException;

public class PlayerPit extends StonePit {
    public PlayerPit(int startingStones, StonePit nextPit) {
        super(startingStones, nextPit);
    }

    public StonePit playPit() throws OperationNotSupportedException {
        if (stones == 0) {
            throw new OperationNotSupportedException("Pit is empty");
        }

        var stonesToMove = stones;
        stones = 0;

        return nextPit.distributeStones(stonesToMove);
    }

    public int captureStones() {
        var stonesToCapture = stones;
        stones = 0;

        return stonesToCapture;
    }
}
