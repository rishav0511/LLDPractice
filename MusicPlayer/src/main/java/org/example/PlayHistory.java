package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PlayHistory {
    private final Deque<Song> history;
    private final int maxSize;

    public PlayHistory(int maxSize) {
        this.maxSize = maxSize;
        this.history = new ArrayDeque<>(maxSize);
    }

    public void add(Song song) {
        if (song == null)
            return;
        if (history.size() >= maxSize)
            history.removeLast();
        history.addFirst(song);
    }

    public Song getLastPlayed() {
        return history.peekFirst();
    }

    public List<Song> getHistory() {
        return new ArrayList<>(history);
    }

    public int size() {
        return history.size();
    }

    public void clear() {
        history.clear();
    }
}
