package app.game.models;

import javax.naming.OperationNotSupportedException;

public class ScorePit extends StonePit {
    public ScorePit() {
        super(0, null);
    }

    public void SetNextPit(StonePit firstPitNextPlayer) {
        nextPit = firstPitNextPlayer;
    }

    public void AddCapturedStones(int stones) {
        this.stones = this.stones + stones;
    }

    public int captureStones() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Cannot capture stones from score pit!");
    }

    public StonePit playPit() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Cannot make a move from score pit!");
    }
}
