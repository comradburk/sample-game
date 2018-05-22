package app.game.dto;

import app.game.models.GamePlayer;
import app.game.models.GameState;

import java.util.UUID;

public class GameResponse {
    public UUID id;
    public int[] pits;
    public GamePlayer currentPlayer;
    public GameState gameState;
}

