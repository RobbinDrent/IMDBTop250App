package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Imdb250Service {
    @Getter
    private Map<Integer, Film> filmInTop250;

    @Getter
    private List<Film> films;

    public Imdb250Service() {
        filmInTop250 = new HashMap<>();
        films = new ArrayList<>();
        loadImdbTop250();
    }


    private void loadImdbTop250() {

        filmInTop250.clear();


        String url = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";

        try {
            // HTML van de URL ophalen
            Document document = Jsoup.connect(url).get();

            Elements elementsWithClass = document.select(".ipc-metadata-list-summary-item__c");
            System.out.println("konijnen");

//            Film film = new Film("bananen");

            Integer i = 1;
            System.out.println(elementsWithClass.size());
            for (Element element : elementsWithClass) {

                                films.add(generateFilm(element));

        }} catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Film generateFilm(Element element) {

        String title = element.select(".ipc-title__text").text();;

//        Element metadata = element.select()

        int releaseYear = Integer.parseInt(element.select(".sc-43986a27-7.dBkaPT.cli-title-metadata > span").get(0).text());
        String duration = element.select(".sc-43986a27-7.dBkaPT.cli-title-metadata > span").get(1).text();

        return new Film(title, releaseYear, duration);
    }
}
