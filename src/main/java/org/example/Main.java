package org.example;

import io.javalin.Javalin;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Imdb250Service imdb250Service = new Imdb250Service();
//        imdb250Service.loadImdbTop250();

        List<Film> films = imdb250Service.loadImdbTop250();

        Javalin app = Javalin.create().start(Config.PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.stop();
        }));


        new ImdbController(app, films);

    }
}