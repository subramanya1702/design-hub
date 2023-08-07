package MusicalJukebox;

public class TestJukebox {

    public static void main(String[] args) throws InterruptedException {
        Jukebox jukebox = new Jukebox();

        // Invalid plays
        jukebox.insertCoin(0, 1);
        jukebox.insertCoin(1, 8);

        // Valid play
        jukebox.insertCoin(1, 1);

        // Try to play another song when one is already in progress
        jukebox.insertCoin(1, 3);

        // Wait till the song is completed
        Thread.sleep(6000);

        // Valid play
        jukebox.insertCoin(5, 2);
    }
}
