package app.game.models;

import javax.naming.OperationNotSupportedException;

public class ScorePit extends StonePit {	
    public ScorePit(GamePlayer pitOwner) {
		super(0, null, pitOwner);
    }

    public void setNextPit(StonePit firstPitNextPlayer) {
        nextPit = firstPitNextPlayer;
    }

    public void addCapturedStones(int stones) {
        this.stones += stones;
    }
	
	public StonePit distributeStones(int stones, GamePlayer player) {
        if (player == pitOwner) {
			return super.distributeStones(stones, player);
		}
		
		return nextPit.distributeStones(stones, player);
    }

    public int captureStones() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Cannot capture stones from score pit!");
    }

    public StonePit playPit(GamePlayer player) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Cannot make a move from score pit!");
    }
}
