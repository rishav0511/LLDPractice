package org.example;

public class Song {
    private final String id,title, artist;
    private final int durationSec;

    public Song(String id, String title, String artist, int durationSec) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationSec = durationSec;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDurationSec() { return durationSec; }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + durationSec + "s)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        return id.equals(((Song) o).id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}
