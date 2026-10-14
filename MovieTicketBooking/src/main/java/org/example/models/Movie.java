package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {
    private String id;
    private String name;

    // other Metadata like genre, duration, etc.
}
