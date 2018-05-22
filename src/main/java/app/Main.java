package app;

import com.google.inject.Guice;

public class Main {
    public static void main(String[] args) {
        var injector = Guice.createInjector(WebModule.create());
        injector.getInstance(AppEntryPoint.class).boot(args);
    }
}
