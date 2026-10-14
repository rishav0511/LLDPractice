package org.example;

import java.util.Random;

public class Dice {
    private Integer low, high;

    public Dice(Integer low, Integer high) {
        this.low = low;
        this.high = high;
    }

    public Integer roll() {
        Random random = new Random();
        return random.nextInt(high - low + 1) + low;
    }
}
