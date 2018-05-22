package app.game.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GameState {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("gameOver")
    GAME_OVER,
}

