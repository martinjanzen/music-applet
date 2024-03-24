package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// test suite for Playlist class
public class PlaylistTest {
    private Song testSong;
    private Song testSong2;
    private Playlist testPlaylist;
    private Playlist testPlaylist2;

    @BeforeEach
    public void runBefore() {
        testSong = new Song("Gnaw", "Alex G", "Race", 201, "Indie");
        testSong2 = new Song("About A Girl", "Nirvana", "Bleach", 217, "Grunge");
        testPlaylist = new Playlist("Favorite Songs", "Martin");
        testPlaylist2 = new Playlist("Study Songs", "Martin");
    }

    @Test
    public void testConstructor() {
        assertEquals("Favorite Songs", testPlaylist.getName());
        assertEquals("Martin", testPlaylist.getCreator());
        assertEquals(new ArrayList<Song>(), testPlaylist.getSongs());
        assertEquals(0, testPlaylist.getNumSongs());

    }

    @Test
    public void testAddRemove() {
        assertFalse(testPlaylist.removeSong(testSong));
        assertEquals(0, testPlaylist.getNumSongs());

        assertTrue(testPlaylist.addSong(testSong));
        assertEquals(1, testPlaylist.getNumSongs());
        assertEquals(testSong, testPlaylist.getSongs().get(0));

        assertFalse(testPlaylist.addSong(testSong));
        assertEquals(1, testPlaylist.getNumSongs());
        assertEquals(testSong, testPlaylist.getSongs().get(0));

        assertTrue(testPlaylist.removeSong(testSong));
        assertEquals(0, testPlaylist.getNumSongs());
    }

    @Test
    public void testListSongs() {
        assertEquals("This playlist is empty!", testPlaylist.listSongs());

        assertTrue(testPlaylist.addSong(testSong));
        assertTrue(testPlaylist.addSong(testSong2));
        assertEquals("Gnaw - Alex G\nAbout A Girl - Nirvana\n", testPlaylist.listSongs());

    }

    @Test
    public void testListPlaylistInfo() {
        assertEquals("Favorite Songs is a playlist created by Martin that has no songs.",
                testPlaylist.listPlaylistInfo());

        assertTrue(testPlaylist.addSong(testSong));
        assertEquals("Favorite Songs is a playlist created by Martin that has 1 song(s).",
                testPlaylist.listPlaylistInfo());

        assertTrue(testPlaylist.addSong(testSong2));
        assertEquals("Favorite Songs is a playlist created by Martin that has 2 song(s).",
                testPlaylist.listPlaylistInfo());

    }

    @Test
    public void testMerge1() {
        assertTrue(testPlaylist.addSong(testSong));
        assertTrue(testPlaylist2.addSong(testSong2));
        assertEquals(testPlaylist, (testPlaylist.merge(testPlaylist2)));
    }

    @Test
    public void testMerge2() {
        assertTrue(testPlaylist.addSong(testSong));
        assertTrue(testPlaylist2.addSong(testSong2));
        assertEquals("Favorite Songs", (testPlaylist.merge(testPlaylist2)).getName());
    }

    @Test
    public void testMerge3() {
        assertTrue(testPlaylist.addSong(testSong));
        assertTrue(testPlaylist2.addSong(testSong2));
        assertEquals(2, (testPlaylist.merge(testPlaylist2)).getNumSongs());
    }

    @Test
    public void testMerge4() {
        assertTrue(testPlaylist.addSong(testSong));
        assertTrue(testPlaylist2.addSong(testSong2));
        assertEquals(testSong, (testPlaylist.merge(testPlaylist2)).getSongs().get(0));
    }

    @Test
    public void testMerge5() {
        assertTrue(testPlaylist.addSong(testSong));
        assertTrue(testPlaylist2.addSong(testSong2));
        assertEquals(testSong2, (testPlaylist.merge(testPlaylist2)).getSongs().get(1));
    }
}
