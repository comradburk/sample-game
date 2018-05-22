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
            path("game", () -> {
                get(ctx -> getController().getGames(ctx));
                post(ctx -> getController().createGame(ctx));

                path(GameController.ROUTE_PARAM_GAME_ID, () -> {
                    get(ctx -> getController().getGame(ctx));
                    delete(ctx -> getController().cancelGame(ctx));

                    path("move", () -> post(ctx -> getController().performMove(ctx)));
                });
            });
        });
    }
}
