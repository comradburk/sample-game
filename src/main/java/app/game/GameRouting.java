package app.game;

import app.util.Routing;
import io.javalin.Javalin;

import javax.inject.Inject;
import javax.inject.Singleton;

import static io.javalin.ApiBuilder.*;

@Singleton
public class GameRouting extends Routing<GameController> {
    private Javalin javalin;
    @Inject
    public GameRouting(Javalin javalin) {
        this.javalin = javalin;
    }

    @Override
    public void bindRoutes() {
        javalin.routes(() -> {
            path("/game", () -> post(ctx -> getController().createGame(ctx)));
            path("/game/" + GameController.ROUTE_PARAM_GAME_ID, () -> get(ctx -> getController().getGame(ctx)));
            path("/game/" + GameController.ROUTE_PARAM_GAME_ID, () -> delete(ctx -> getController().cancelGame(ctx)));
        });
    }
}
