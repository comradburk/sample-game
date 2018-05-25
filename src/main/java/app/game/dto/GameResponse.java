package app.game.dto;

import app.game.models.GamePlayer;
import app.game.models.GameState;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GameResponse {
    public UUID id;
    public Map<GamePlayer, List<Integer>> pits;
    public GamePlayer currentPlayer;
    public GameState gameState;
}

