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

    private final String IMDB_TOP_250 = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
    private Javalin app;
    private Imdb250Service service;
    private Map<Integer, String> top250;

    ImdbController(Javalin app){
        this.app = app;
        this.service = new Imdb250Service();
//        this.top250 = loadTop250();

        app.get("/", ctx -> {
            this.showTop250(ctx);
        });
    }

    private void showTop250(final Context ctx) {
        try {
            ctx.json(service.getFilms());
        } catch (Exception e) {
            ctx.result("pantoffels");
        }
    }
//
//    private void loadTop250() throws IOException {
//        URL url = new URL(IMDB_TOP_250);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        connection.setRequestMethod("GET");
//
//        int responseCode = connection.getResponseCode();
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            int position = 1;
//            Map<Integer, String> lines = new HashMap<>();
//
//            while ((line = reader.readLine()) != null) {
//                if (line.contains("titleText")) {
//                    lines.put(position, )
//                }
//            }
//        }
//
//    }


}
