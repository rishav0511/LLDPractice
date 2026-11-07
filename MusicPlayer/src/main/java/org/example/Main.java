package org.example;

/**
 * <a href="https://systemcraft.in/lld/MusicPlayer/">Description</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("══════ MUSIC PLAYER DEMO ══════\n");

        MusicPlayer player = new MusicPlayer();
        player.addObserver(new PlayerObserver() {
            public void onSongChanged(Song s) { System.out.println("  ♪ Now playing: " + s); }
            public void onStateChanged(PlayerState st) { System.out.println("  ⏸ State: " + st); }
        });

        Playlist pl = new Playlist("Favourites");
        pl.addSong(new Song("1", "Blinding Lights", "The Weeknd", 200));
        pl.addSong(new Song("2", "Bohemian Rhapsody", "Queen", 354));
        pl.addSong(new Song("3", "Shape of You", "Ed Sheeran", 233));
        pl.addSong(new Song("4", "Starboy", "The Weeknd", 230));
        pl.addSong(new Song("5", "Someone Like You", "Adele", 285));

        player.loadPlaylist(pl);

        System.out.println("--- Sequential ---");
        player.play();
        player.next();
        player.next();
        player.pause();
        player.play(); // resume
        player.previous();

        System.out.println("\n--- Shuffle (no repeats) ---");
        player.enableShuffle();
        for (int i = 0; i < 5; i++) player.next();
        System.out.println("All 5 played without repeat!");

        System.out.println("\n--- History ---");
        var hist = player.getHistory();
        for (int i = 0; i < Math.min(5, hist.size()); i++)
            System.out.println("  " + (i+1) + ". " + hist.get(i));

        System.out.println("\n══════ DONE ══════");
    }
}