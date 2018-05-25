package app.game.services;

import app.game.models.GameBoard;
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
    private final int PLAYER_PIT_COUNT = 6;
    private final int STARTING_STONE_COUNT = 6;
    private IGameRepository gameRepository;

    @Inject
    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameBoard createGame() {
        var game = new GameBoard(PLAYER_PIT_COUNT, STARTING_STONE_COUNT);

        game.setId(UUID.randomUUID());

        return gameRepository.saveGame(game);
    }

    @Override
    public void cancelGame(UUID id) {
        gameRepository.deleteGame(id);
    }

    @Override
    public Optional<GameBoard> getGameById(UUID id) {
        return gameRepository.getGameById(id);
    }

    @Override
    public Collection<GameBoard> getGames() {
        return gameRepository.getGames();
    }

    @Override
    public GameBoard performMove(UUID gameId, GamePlayer player, int playerPitIndex) throws OperationNotSupportedException {
        var game = gameRepository.getGameById(gameId).get();

        if (game.getGameState() != GameState.ACTIVE) {
            throw new OperationNotSupportedException("Game is over");
        }

        if (game.getCurrentPlayer() != player) {
            throw new OperationNotSupportedException("It is not the players turn");
        }

        game.playPit(player, playerPitIndex);

        return gameRepository.saveGame(game);
    }
}
