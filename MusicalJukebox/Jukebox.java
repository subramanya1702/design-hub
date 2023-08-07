package MusicalJukebox;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static MusicalJukebox.Constants.ACCEPTED_DENOMINATIONS;
import static MusicalJukebox.Constants.SONG_MAP;

public class Jukebox implements SongCallback {

    private final Map<Integer, Integer> songCoinMap = new HashMap<>();

    private boolean isJukeBoxPlaying = false;

    public Jukebox() {
    }

    @Override
    public void onSongCompletion(Void unused) {
        isJukeBoxPlaying = false;
        System.out.println("Song completed. Jukebox available!!!");
    }

    public void insertCoin(int coin, int songNumber) {
        if (!validate(coin, songNumber)) {
            return;
        }

        isJukeBoxPlaying = true;
        songCoinMap.put(songNumber, coin);
        SONG_MAP.get(songNumber).playSong(this);
    }

    public Collection<Integer> getSongHistory() {
        return songCoinMap.values();
    }

    private boolean validate(int coin, int songNumber) {
        if (isJukeBoxPlaying) {
            System.out.println("Jukebox is currently playing. Try again after the song is done.");
            return false;
        }

        if (!ACCEPTED_DENOMINATIONS.contains(coin)) {
            System.out.println("Coin is not valid.");
            return false;
        }

        if (!SONG_MAP.containsKey(songNumber)) {
            System.out.println("Song number is invalid. Try again...");
            return false;
        }

        return true;
    }

}
