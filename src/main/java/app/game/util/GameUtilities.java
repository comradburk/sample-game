package app.game.util;

import app.game.models.GamePlayer;
import app.game.models.ScorePit;
import app.game.models.StonePit;

public class GameUtilities {
    public static GamePlayer GetNextPlayer(GamePlayer player) {
        return player == GamePlayer.PLAYER_ONE ? GamePlayer.PLAYER_TWO : GamePlayer.PLAYER_ONE;
    }

    public static ScorePit GetPlayerScorePit(StonePit currentPit) {
        while (!(currentPit instanceof ScorePit)) {
            currentPit = currentPit.getNextPit();
        }
        return (ScorePit) currentPit;
    }
}
