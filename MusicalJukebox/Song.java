package MusicalJukebox;

import java.util.concurrent.CompletableFuture;

public class Song {
    private final int id;
    private final String name;

    public Song(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void playSong(SongCallback songCallback) {
        // Starts playing the song
        System.out.println("Playing song: " + name);

        // Sleeping for 10 seconds to mimic the song
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenAccept(songCallback::onSongCompletion);

        // After the song is done, notify jukebox to change its status
    }
}
