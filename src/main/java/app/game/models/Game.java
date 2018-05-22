package app.game.models;

import java.util.*;

public class Game {
    private UUID Id;
    private List<Integer> pits;
    private GamePlayer currentPlayer;

    public UUID getId() {
        return Id;
    }
    public void setId(UUID value) { Id = value; }

    public Game(int boardSize, int startingStones) {
        if (boardSize <= 0) throw new IllegalArgumentException("Board size must be greater than 0");
        if (startingStones <= 0) throw new IllegalArgumentException("Starting stones must be greater than 0");

        // Pits start with starting quantity of stones
        pits = new ArrayList<>(Collections.nCopies(boardSize * 2, startingStones));

        // Score pits start empty
        pits.set(boardSize - 1, 0);
        pits.set((boardSize * 2) - 1, 0);
    }

    public GamePlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public GamePlayer nextPlayer() {
        return currentPlayer == GamePlayer.PLAYER_ONE ? GamePlayer.PLAYER_TWO : GamePlayer.PLAYER_ONE;
    }

    public void setCurrentPlayer(GamePlayer gamePlayer) {
        currentPlayer = gamePlayer;
    }

    public GameState getGameState() {
        var playerPitCount = (pits.size() / 2) - 1;

        var playerOnePitsEmpty = pits.stream()
                .limit(playerPitCount)
                .allMatch((stones) -> stones == 0);
        var playerTwoPitsEmpty = pits.stream()
                .skip(playerPitCount + 1)
                .limit(playerPitCount)
                .allMatch((stones) -> stones == 0);

        if (playerOnePitsEmpty || playerTwoPitsEmpty) {
            return GameState.GAME_OVER;
        }

        return GameState.ACTIVE;
    }

    public List<Integer> getPits() {
        return pits;
    }

    public int moveStones(int pitIndex) {
        if (pitIndex >= pits.size()) throw new NoSuchElementException();

        var stones = pits.get(pitIndex);
        pits.set(pitIndex, 0);

        var currentPitIndex = pitIndex;
        for(var stoneMoves = 0; stoneMoves < stones; stoneMoves++) {
            currentPitIndex = nextPitIndex(currentPitIndex);
            var pitValue = getPits().get(currentPitIndex);
            pits.set(currentPitIndex, pitValue + 1);
        }

        return currentPitIndex;
    }

    public void captureStones(int lastPlacedPitIndex) {
        var oppositePitIndex = oppositePitIndex(lastPlacedPitIndex);
        var capturedStones = pits.get(oppositePitIndex);

        pits.set(lastPlacedPitIndex, 0);
        pits.set(oppositePitIndex, 0);

        // Move captured stones to score pit
        var scorePitIndex = lastPlacedPitIndex + 1;
        while (!isScorePit(scorePitIndex)) {
            scorePitIndex = nextPitIndex(scorePitIndex);
        }
        pits.set(scorePitIndex, capturedStones + 1);
    }

    public int nextPitIndex(int pitIndex) {
        if (pitIndex >= pits.size()) throw new NoSuchElementException();

        var nextPitIndex = pitIndex + 1;
        return (nextPitIndex >= pits.size()) ? 0 : nextPitIndex;
    }

    public int oppositePitIndex(int pitIndex) {
        if (pitIndex >= pits.size()) throw new NoSuchElementException();

        return (pits.size()) - (pitIndex + 2);
    }

    public boolean isScorePit(int pitIndex) {
        if (pitIndex >= pits.size()) throw new NoSuchElementException();

        return pitIndex + 1 == pits.size() / 2 || pitIndex + 1 == pits.size();
    }
}

