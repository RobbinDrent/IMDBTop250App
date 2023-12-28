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
import java.util.Map;

public class ImdbController {

    private Javalin app;
    private Imdb250Service service;

    ImdbController(Javalin app){
        this.app = app;
        this.service = new Imdb250Service();

        app.get(Config.IMDB_TOP_250, this::showTop250);
    }

    private void showTop250(final Context ctx) {
        try {
            ctx.json(service.getFilms());
        } catch (Exception e) {
            ctx.result("Er is iets mis gegaan");
        }
    }
}
