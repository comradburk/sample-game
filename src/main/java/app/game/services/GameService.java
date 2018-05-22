package app.game.services;

import app.game.models.Game;
import app.game.models.GamePlayer;
import app.game.models.GameState;
import app.game.repositories.IGameRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class GameService implements IGameService {
    private final int BOARD_SIZE = 7;
    private final int STARTING_STONE_COUNT = 6;
    private IGameRepository gameRepository;

    @Inject
    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame() {
        var game = new Game(BOARD_SIZE, STARTING_STONE_COUNT);

        game.setId(UUID.randomUUID());
        game.setCurrentPlayer(GamePlayer.PLAYER_ONE);

        return gameRepository.saveGame(game);
    }

    @Override
    public void cancelGame(UUID id) {
        gameRepository.deleteGame(id);
    }

    @Override
    public Optional<Game> getGameById(UUID id) {
        return gameRepository.getGameById(id);
    }

    @Override
    public Collection<Game> getGames() {
        return gameRepository.getGames();
    }

    @Override
    public Game performMove(UUID gameId, GamePlayer player, int playerPitIndex) throws OperationNotSupportedException {
        var game = gameRepository.getGameById(gameId).get();

        if (game.getGameState() != GameState.ACTIVE) {
            throw new OperationNotSupportedException("Game is over");
        }

        if (game.getCurrentPlayer() != player) {
            throw new OperationNotSupportedException("It is not the players turn");
        }

        var pitIndex = (game.getCurrentPlayer() == GamePlayer.PLAYER_TWO) ?
                playerPitIndex + (game.getPits().size() + 1) : playerPitIndex;

        if (game.getPits().get(pitIndex) == 0) {
            throw new OperationNotSupportedException("Pit is empty");
        }

        var lastPlacedPitIndex = game.moveStones(pitIndex);

        // Check if player turn advances
        if (game.isScorePit(lastPlacedPitIndex)) {
            // Move stones from opponent side if it was placed in an empty spot
            if (game.getPits().get(lastPlacedPitIndex) == 1) {
                game.captureStones(lastPlacedPitIndex);
            }

            game.setCurrentPlayer(game.nextPlayer());
        }

        return gameRepository.saveGame(game);
    }
}
