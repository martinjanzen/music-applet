package persistence;

import model.Song;
import model.Playlist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// test suite for general Json properties
public class JsonTest {
    protected void checkSong(String name, String artist, String album, int length, String genre, Song song) {
        assertEquals(name, song.getName());
        assertEquals(artist, song.getArtist());
        assertEquals(album, song.getAlbum());
        assertEquals(length, song.getLength());
        assertEquals(genre, song.getGenre());
    }

    protected void checkPlaylist(String name, String creator, Playlist playlist) {
        assertEquals(name, playlist.getName());
        assertEquals(creator, playlist.getCreator());
    }

}
