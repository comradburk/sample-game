package app.game.models;

import app.game.util.GameUtilities;

import javax.naming.OperationNotSupportedException;
import java.util.Map;
import java.util.UUID;

public class GameBoard {
    private UUID _id;
    private int _playerPitCount;
    private GamePlayer _currentPlayer;
    private Map<GamePlayer, StonePit> _playerPits;

    public GameBoard(int playerPitCount, int startingStones) {
        if (playerPitCount <= 0)
            throw new IllegalArgumentException("Number of pits must be greater than 0 (you want to actually play right?)");
        if (startingStones <= 0) throw new IllegalArgumentException("Starting stones must be greater than 0");

        _playerPitCount = playerPitCount;
        _currentPlayer = GamePlayer.PLAYER_ONE;
        _playerPits = new StonePitFactory().buildPlayerPits(playerPitCount, startingStones);
    }

    public UUID getId() {
        return _id;
    }

    public void setId(UUID _id) {
        this._id = _id;
    }

    public int getPlayerPitCount() {
        return _playerPitCount;
    }

    public GamePlayer getCurrentPlayer() {
        return _currentPlayer;
    }

    public Map<GamePlayer, StonePit> getPlayerPits() {
        return _playerPits;
    }

    public void playPit(GamePlayer player, int pitIndex) throws OperationNotSupportedException {
        var currentPit = _playerPits.get(player);
        for (var i = 0; i < pitIndex; i++) {
            currentPit = currentPit.getNextPit();
        }

        var lastPit = currentPit.playPit();

        if (lastPit.getStoneCount() == 1) {
            captureStones(player, lastPit);
        }

        if (!(lastPit instanceof ScorePit)) {
            _currentPlayer = GameUtilities.GetNextPlayer(_currentPlayer);
        }
    }

    void captureStones(GamePlayer player, StonePit lastScoredPit) throws OperationNotSupportedException {
        var playerScorePit = GameUtilities.GetPlayerScorePit(lastScoredPit);
        var oppositePit = getOppositePit(player, lastScoredPit);

        playerScorePit.AddCapturedStones(lastScoredPit.captureStones() + oppositePit.captureStones());
    }

    StonePit getOppositePit(GamePlayer player, StonePit givenPit) {
        var distanceFromScorePit = 0;
        while (!(givenPit instanceof ScorePit)) {
            givenPit = givenPit.getNextPit();
            distanceFromScorePit++;
        }

        var oppositePit = _playerPits.get(GameUtilities.GetNextPlayer(player));
        for (var i = 0; i < distanceFromScorePit - 1; i++) {
            oppositePit = oppositePit.getNextPit();
        }

        return oppositePit;
    }

    public int getScore(GamePlayer player) {
        var startingPit = _playerPits.get(player);
        var currentPit = startingPit;

        for (var i = 0; i < _playerPitCount; i++) {
            currentPit = currentPit.getNextPit();
        }

        return currentPit.getStoneCount();
    }

    public GameState getGameState() {
        var anyPitsEmpty = _playerPits.values().stream()
                .anyMatch(stonePit -> {
                    var currentPit = stonePit;
                    for (var i = 0; i < _playerPitCount; i++) {
                        if (currentPit.getStoneCount() != 0) return false;
                        currentPit = currentPit.getNextPit();
                    }

                    return true;
                });

        return anyPitsEmpty ? GameState.GAME_OVER : GameState.ACTIVE;
    }

}

