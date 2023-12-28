package org.example;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

@Slf4j
public class Imdb250Service {
    private static final String INVALID_TIME_NOTATION = "Ongeldige tijdnotatie: ";

    @Getter
    private Map<Integer, Film> filmInTop250;
    @Getter
    private List<Film> films;

    public Imdb250Service() {
        filmInTop250 = new HashMap<>();
        films = new ArrayList<>();
        loadImdbTop250();
    }

    /**
     * Laadt de IMDB top 250 en sla de films op een een List
     */
    private void loadImdbTop250() {
        filmInTop250.clear();

        try {
            var document = Jsoup.connect(Config.IMDB_TOP_250_URL).get();
            var elementsWithClass = document.select(Config.SUMMARY_ITEM_TAG);

            for (Element element : elementsWithClass) {
                films.add(generateFilm(element));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Haal de titel, release jaar en duur van een film op uit een html element
     * @param element Element
     * @return Film
     */
    private Film generateFilm(final Element element) {
        var title = element.select(Config.TITLE_TAG).text();
        var releaseYear = Integer.parseInt(element.select(Config.METADATA_TAG).get(0).text());
        var duration = element.select(Config.METADATA_TAG).get(1).text();

        return new Film(
                generateFilmTitle(title),
                releaseYear,
                generateDurationInMinutes(duration),
                extractUrlFromHtml(element)
        );
    }

    /**
     * Laat alleen titel over.
     * @param title String
     * @return String bewerkte titel
     */
    private String generateFilmTitle(final String title) {
        int index = title.indexOf(". ");

        return title.substring(index + 2);
    }

    /**
     * Verander de duur van een film naar het aantal minuten
     * @param duration String
     * @return String durationInMinutes
     */
    private String generateDurationInMinutes(final String duration) {
        int durationInMinutes = 0;

        if (duration.matches("\\d+h \\d+m")) {
            String[] parts = duration.split("[hm]");
            var hours = Integer.parseInt(parts[0].trim());
            var minutes = Integer.parseInt(parts[1].trim());

            durationInMinutes = hours * 60 + minutes;
        } else if (duration.matches("\\d+h")) {
            var hours = Integer.parseInt(duration.replace("h","").trim() );
            durationInMinutes = hours * 60;
        } else {
            log.error(INVALID_TIME_NOTATION + duration);
        }

        return durationInMinutes + "m";
    }

    /**
     * Haal de url van de film op
     * @param element element
     * @return String url
     */
    private String extractUrlFromHtml(final Element element) {
        var selectedElement = element.select(Config.URL_WRAPPER_TAG);
        String url = selectedElement.attr("href");

        return Config.IMDB_BASE_URL + url;
    }
}
