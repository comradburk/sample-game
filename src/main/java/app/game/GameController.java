package app.game;

import app.game.dto.MoveRequest;
import app.game.services.IGameService;
import app.game.translators.GameResponseTranslator;
import io.javalin.Context;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.OperationNotSupportedException;
import java.util.UUID;

@Singleton
public class GameController {
    static final String ROUTE_PARAM_GAME_ID = ":gameId";
    private IGameService gameService;
    private GameResponseTranslator gameResponseTranslator;

    @Inject
    public GameController(IGameService gameService, GameResponseTranslator gameResponseTranslator) {
        this.gameService = gameService;
        this.gameResponseTranslator = gameResponseTranslator;
    }

    void getGame(Context context) {
        var game = gameService.getGameById(UUID.fromString(context.param(ROUTE_PARAM_GAME_ID)));

        if (!game.isPresent()) {
            context.status(404);
            context.json("Game not found");
            return;
        }

        context.status(200);
        context.json(gameResponseTranslator.Translate(game.get()));
    }

    public void getGames(Context context) {
        var games = gameService.getGames().stream()
                .map(gameResponseTranslator::Translate)
                .toArray();

        context.status(200);
        context.json(games);
    }

    void createGame(Context context) {
        var gameId = gameService.createGame().getId();

        context.status(201);
        context.json(gameId);
    }

    void cancelGame(Context context) {
        var gameId = UUID.fromString(context.param(ROUTE_PARAM_GAME_ID));

        if(!gameService.getGameById(gameId).isPresent()) {
            context.status(404);
            context.json("Game not found");
            return;
        }

        gameService.cancelGame(gameId);
        context.status(200);
    }

    public void performMove(Context context) throws OperationNotSupportedException {
        var gameId = UUID.fromString(context.param(ROUTE_PARAM_GAME_ID));

        if (!gameService.getGameById(gameId).isPresent()) {
            context.status(404);
            context.json("Game not found");
            return;
        }

        var moveRequest = context.bodyAsClass(MoveRequest.class);
        var game = gameService.performMove(gameId, moveRequest.gamePlayer, moveRequest.playerPitIndex);

        context.status(200);
        context.json(gameResponseTranslator.Translate(game));
    }
}

