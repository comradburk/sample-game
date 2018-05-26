package app.game.models;

import app.game.util.GameUtilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class StonePitFactory {
    public Map<GamePlayer, StonePit> buildPlayerPits(int playerPitCount, int startingStones) {
        StonePit[] playerPits = {
                buildPlayerSide(playerPitCount, startingStones, GamePlayer.PLAYER_ONE),
                buildPlayerSide(playerPitCount, startingStones, GamePlayer.PLAYER_TWO)
        };

        // Link the player score pits with the first pit of next player
        GameUtilities.GetPlayerScorePit(playerPits[0]).setNextPit(playerPits[1]);
        GameUtilities.GetPlayerScorePit(playerPits[1]).setNextPit(playerPits[0]);

        var completedPits = new HashMap<GamePlayer, StonePit>();
        completedPits.put(GamePlayer.PLAYER_ONE, playerPits[0]);
        completedPits.put(GamePlayer.PLAYER_TWO, playerPits[1]);

        return completedPits;
    }

    /**
     * Builds the score pit and player pits for a single side of the board (one of the players)
     *
     * @param playerPitCount Number of player pits (not counting the scoring pit
     * @param startingStones Initial quantity of stones in each player pit
     * @return The starting player pit linked to it's following pits for one game board side
     */
    public StonePit buildPlayerSide(int playerPitCount, int startingStones, GamePlayer pitOwner) {
        StonePit playerScorePit = new ScorePit(pitOwner);
        var initialPitBuilder = playerPitBuilder(startingStones, pitOwner);

        return Stream.generate(() -> initialPitBuilder)
                .limit(playerPitCount)
                .reduce(playerScorePit, (
                                StonePit previousPit, Function<StonePit, StonePit> currentPitBuilderFunc) -> currentPitBuilderFunc.apply(previousPit),
                        (stonePit, stonePit2) -> stonePit2);
    }

    /**
     * Factory that returns a builder function for a pit with the given number of stones
     *
     * @param startingStones Initial quantity of stones in the pit
     * @return A function that takes in a pit and builds
     */
    public Function<StonePit, StonePit> playerPitBuilder(int startingStones, GamePlayer pitOwner) {
        return (pit) -> new PlayerPit(startingStones, pit, pitOwner);
    }
}
