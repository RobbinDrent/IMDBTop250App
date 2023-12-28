package org.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.stop();
        }));

        new ImdbController(app);
    }
}