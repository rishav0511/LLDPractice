package org.example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

enum PlayerState { PLAYING, PAUSED, STOPPED }

interface PlayerObserver {
    void onSongChanged(Song song);
    void onStateChanged(PlayerState state);
}


public class MusicPlayer {
    private Playlist currentPlaylist;
    private PlayMode playMode;
    private volatile PlayerState state = PlayerState.STOPPED;
    private volatile Song currentSong;
    private final PlayHistory history = new PlayHistory(100);
    private boolean shuffleEnabled = false;
    private final ReentrantLock lock = new ReentrantLock();
    private final List<PlayerObserver> observers = new CopyOnWriteArrayList<>();

    public void loadPlaylist(Playlist playlist) {
        lock.lock();
        try {
            this.currentPlaylist = playlist;
            this.playMode = new SequentialMode();
            this.playMode.reset(playlist.getSongs());
            this.state = PlayerState.STOPPED;
            this.currentSong = null;
        } finally { lock.unlock(); }
    }

    public void play() {
        lock.lock();
        try {
            if (state == PlayerState.PAUSED) {
                state = PlayerState.PLAYING;
                notifyState();
            } else if (state == PlayerState.STOPPED) {
                Song song = playMode.next();
                if (song != null) {
                    currentSong = song;
                    state = PlayerState.PLAYING;
                    history.add(song);
                    notifySong(song);
                    notifyState();
                }
            }
        } finally { lock.unlock(); }
    }

    public void pause() {
        lock.lock();
        try {
            if (state == PlayerState.PLAYING) {
                state = PlayerState.PAUSED;
                notifyState();
            }
        } finally { lock.unlock(); }
    }

    public Song next() {
        lock.lock();
        try {
            Song song = playMode.next();
            if (song != null) {
                currentSong = song;
                state = PlayerState.PLAYING;
                history.add(song);
                notifySong(song);
                notifyState();
            }
            return song;
        } finally { lock.unlock(); }
    }

    public Song previous() {
        lock.lock();
        try {
            Song song = playMode.previous();
            if (song != null) {
                currentSong = song;
                state = PlayerState.PLAYING;
                history.add(song);
                notifySong(song);
                notifyState();
            }
            return song;
        } finally { lock.unlock(); }
    }

    public void enableShuffle() {
        lock.lock();
        try {
            shuffleEnabled = true;
            playMode = new ShuffleMode();
            playMode.reset(currentPlaylist.getSongs());
            System.out.println("Shuffle: ON");
        } finally { lock.unlock(); }
    }

    public void disableShuffle() {
        lock.lock();
        try {
            shuffleEnabled = false;
            playMode = new SequentialMode();
            playMode.reset(currentPlaylist.getSongs());
            System.out.println("Shuffle: OFF");
        } finally { lock.unlock(); }
    }

    public boolean isShuffleEnabled() { return shuffleEnabled; }
    public Song getCurrentSong() { return currentSong; }
    public PlayerState getState() { return state; }
    public List<Song> getHistory() { return history.getHistory(); }

    public void addObserver(PlayerObserver o) { observers.add(o); }
    public void removeObserver(PlayerObserver o) { observers.remove(o); }

    private void notifySong(Song s) { for (var o : observers) o.onSongChanged(s); }
    private void notifyState() { for (var o : observers) o.onStateChanged(state); }
}
