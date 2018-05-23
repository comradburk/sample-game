package app;

import app.util.Routing;
import com.google.inject.Inject;
import io.javalin.Javalin;

import javax.inject.Singleton;
import javax.naming.OperationNotSupportedException;
import java.util.Collections;
import java.util.Set;

@Singleton
class WebEntryPoint implements AppEntryPoint {
    private Javalin app;

    @Inject(optional = true)
    private Set<Routing> routes = Collections.emptySet();

    @Inject
    public WebEntryPoint(Javalin app) {
        this.app = app;
    }

    @Override
    public void boot(String[] args) {
        bindRoutes();
        app.port(7000);
        app.enableCorsForOrigin("http://localhost:8080");
        app.exception(Exception.class, (ex, context) -> {
            context.status(500);
            context.json(ex.getMessage());
        });
        app.exception(OperationNotSupportedException.class, (ex, context) -> {
            context.status(400);
            context.json(ex.getMessage());
        });
        app.start();
    }

    private void bindRoutes() {
        routes.forEach(Routing::bindRoutes);
    }
}