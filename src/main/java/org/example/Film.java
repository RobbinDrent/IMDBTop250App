package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Film {

    private String title;
    private int releaseYear;
    private String duration;
    private String imdbURL;
    private String director;

    @Override
    public String toString() {
        return String.format("Title: %s\nRelease: %d\nDuration: %s\nUrl: %s\nDirector: %s\n",
                title, releaseYear, duration, imdbURL, director);
    }
}
