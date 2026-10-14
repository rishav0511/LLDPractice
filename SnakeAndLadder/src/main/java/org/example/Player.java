package org.example;

import lombok.Getter;
import lombok.Setter;

public class Player {
    @Getter
    private String name;
    @Setter @Getter
    private int position;
    @Setter @Getter
    private boolean won;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.won = false;
    }

}
