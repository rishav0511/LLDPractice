package org.example;

import java.util.ArrayList;
import java.util.List;

public class SequentialMode implements PlayMode {

    private List<Song> songs = new ArrayList<>();
    private int currentIndex = -1;

    @Override
    public Song next() {
        if (songs.isEmpty())
            return null;
        currentIndex++;
        if (currentIndex >= songs.size())
            currentIndex = 0;
        return songs.get(currentIndex);
    }

    @Override
    public Song previous() {
        if (songs.isEmpty())
            return null;
        currentIndex--;
        if (currentIndex < 0)
            currentIndex = songs.size()-1;
        return songs.get(currentIndex);
    }

    @Override
    public void reset(List<Song> songs) {
        this.songs=songs;
        this.currentIndex=-1;
    }

    @Override
    public boolean hasNext() {
        return !songs.isEmpty();
    }

    @Override
    public Song current() {
        if (currentIndex < 0 || currentIndex >= songs.size())
            return null;
        return songs.get(currentIndex);
    }
}
