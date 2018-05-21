package app.game;

import app.game.services.IGameService;
import io.javalin.Context;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class GameController {
    static final String ROUTE_PARAM_GAME_ID = ":gameId";
    private IGameService gameService;

    @Inject
    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    void getGame(Context context) {
        var game = gameService.getGameById(UUID.fromString(context.param(ROUTE_PARAM_GAME_ID)));

        if (!game.isPresent()) {
            context.status(404);
            context.json("Game not found");
            return;
        }

        context.status(201);
        context.json(game.get());
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
}

