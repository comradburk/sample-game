package app;

import app.game.GameModule;
import com.google.inject.AbstractModule;
import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;

public class WebModule extends AbstractModule {
    private Javalin app;

    private WebModule(Javalin app) {
        this.app = app;
    }

    @NotNull
    public static WebModule create() {
        return new WebModule(Javalin.create());
    }

    @Override
    protected void configure() {
        bind(Javalin.class).toInstance(app);
        bind(AppEntryPoint.class).to(WebEntryPoint.class);

        install(new GameModule());
    }
}
