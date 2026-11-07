package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleMode implements PlayMode {

    private List<Song> shuffled = new ArrayList<>();
    private int currentIndex = -1;
    private final Random random = new Random();

    @Override
    public void reset(List<Song> songs) {
        this.shuffled = new ArrayList<>(songs);
        fisherYatesShuffle();
        this.currentIndex = -1;
    }

    private void fisherYatesShuffle() {
        for (int i = shuffled.size() - 1; i > 0; i--)
        {
            int j = random.nextInt(i + 1);
            Collections.swap(shuffled, i, j);
        }
    }

    @Override
    public Song next() {
        if (shuffled.isEmpty())
            return null;
        currentIndex++;
        if (currentIndex >= shuffled.size()) {
            fisherYatesShuffle(); // all played once, reshuffle
            currentIndex = 0;
        }
        return shuffled.get(currentIndex);
    }

    @Override
    public Song previous() {
        if (shuffled.isEmpty())
            return null;
        if (currentIndex > 0)
            currentIndex--;
        return shuffled.get(currentIndex);
    }

    @Override
    public boolean hasNext() { return !shuffled.isEmpty(); }

    @Override
    public Song current() {
        if (currentIndex < 0 || currentIndex >= shuffled.size())
            return null;
        return shuffled.get(currentIndex);
    }
}
