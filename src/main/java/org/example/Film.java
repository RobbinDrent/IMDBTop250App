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
}
