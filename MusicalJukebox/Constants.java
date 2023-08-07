package MusicalJukebox;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Constants {

    private Constants() {

    }

    public static final Set<Integer> ACCEPTED_DENOMINATIONS = new HashSet<>(List.of(1, 5, 10));

    public static final Map<Integer, Song> SONG_MAP = Map.of(
            1, new Song(1, "SongOne"),
            2, new Song(2, "SongTwo"),
            3, new Song(3, "SongThree"),
            4, new Song(4, "SongFour"),
            5, new Song(5, "SongFive"),
            6, new Song(6, "SongSix")
    );
}
