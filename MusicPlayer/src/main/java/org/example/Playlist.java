package org.example;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final String name;
    private final List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) { songs.add(song); }
    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }
    public int size() { return songs.size(); }
    public boolean isEmpty() { return songs.isEmpty(); }
}
