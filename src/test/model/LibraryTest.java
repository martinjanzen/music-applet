package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test suite for Library class
public class LibraryTest {
    private Library testLibrary;
    private Song testSong;
    private Song testSong2;
    private Playlist testPlaylist;
    private Playlist testPlaylist2;

    @BeforeEach
    public void runBefore() {
        testLibrary = new Library();
        testSong = new Song("Gnaw", "Alex G", "Race", 201, "Indie");
        testSong2 = new Song("About A Girl", "Nirvana", "Bleach", 217, "Grunge");
        testPlaylist = new Playlist("Favorite Songs", "Martin");
        testPlaylist2 = new Playlist("Study Songs", "Martin");
    }

    @Test
    public void testConstructor() {
        assertEquals("Library", testLibrary.getName());
        assertEquals(0, testLibrary.getNumSongs());
        assertEquals(0, testLibrary.getNumPlaylists());
    }

    @Test
    public void testAddSongs() {
        assertEquals(0, testLibrary.getNumSongs());
        testLibrary.addSong(testSong);
        assertEquals(1, testLibrary.getNumSongs());
        assertEquals(testSong, testLibrary.getSongs().get(0));

        testLibrary.addSong(testSong2);
        assertEquals(2, testLibrary.getNumSongs());
        assertEquals(testSong2, testLibrary.getSongs().get(1));

    }

    @Test
    public void testAddPlaylists() {
        assertEquals(0, testLibrary.getNumPlaylists());
        testLibrary.addPlaylist(testPlaylist);
        assertEquals(1, testLibrary.getNumPlaylists());
        assertEquals(testPlaylist, testLibrary.getPlaylists().get(0));

        testLibrary.addPlaylist(testPlaylist2);
        assertEquals(2, testLibrary.getNumPlaylists());
        assertEquals(testPlaylist2, testLibrary.getPlaylists().get(1));
    }

}
