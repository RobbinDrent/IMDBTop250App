package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import org.eclipse.jetty.server.HttpConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
