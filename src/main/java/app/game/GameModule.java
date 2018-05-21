package app.game;

import app.game.repositories.IGameRepository;
import app.game.repositories.InMemoryGameRepository;
import app.game.services.IGameService;
import app.game.services.GameService;
import app.util.Routing;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GameController.class);
        bind(IGameService.class).to(GameService.class);
        bind(IGameRepository.class).to(InMemoryGameRepository.class);
        Multibinder.newSetBinder(binder(), Routing.class).addBinding().to(GameRouting.class);
    }
}
