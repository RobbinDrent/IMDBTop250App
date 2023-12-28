package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ImdbController {

    private List<Film> films;
    private Javalin app;
    private Imdb250Service service;

    ImdbController(final Javalin app, final List<Film> films){
        this.app = app;
        this.service = new Imdb250Service();
        this.films = films;

        app.get(Config.IMDB_TOP_250, ctx -> {
            this.showTop250(ctx);
        });
    }

    private void showTop250(final Context ctx) {
        try {
            ctx.json(films);
        } catch (Exception e) {
            ctx.result("Er is iets mis gegaan");
        }
    }
}
