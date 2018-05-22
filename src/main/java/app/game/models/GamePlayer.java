package app.game.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GamePlayer {
    @JsonProperty("playerOne")
    PLAYER_ONE,
    @JsonProperty("playerTwo")
    PLAYER_TWO,
}
