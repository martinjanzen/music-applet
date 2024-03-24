package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test suite for Song class.
class SongTest {
    private Song testSong;


    @BeforeEach
    public void runBefore() {
        testSong = new Song("Gnaw", "Alex G", "Race", 201, "Indie");
    }

    @Test
    public void constructorTest() {
        assertEquals("Gnaw", testSong.getName());
        assertEquals("Alex G", testSong.getArtist());
        assertEquals("Race", testSong.getAlbum());
        assertEquals(201, testSong.getLength());
        assertEquals("Indie", testSong.getGenre());
        assertFalse(testSong.isLiked());
    }

    @Test
    public void testLikeFunction() {
        testSong.like();
        assertTrue(testSong.isLiked());

        testSong.unlike();
        assertFalse(testSong.isLiked());
    }

    @Test
    public void testListSongInfo() {
        assertEquals("Gnaw by Alex G on the album Race is a(n) Indie song that is 201 seconds long." +
                        " This song is not liked!",
                testSong.listSongInfo());
        testSong.like();
        assertEquals("Gnaw by Alex G on the album Race is a(n) Indie song that is 201 seconds long." +
                        " This song is liked!",
                testSong.listSongInfo());

    }

    @Test
    public void testNewEquals() {
        Object o = null;
        assertFalse(testSong.equals(o));

        o = new Song("Gnaw", "John", "Race", 201, "Indie");
        assertFalse(testSong.equals(o));

        o = new Playlist("stub", "stub");
        assertFalse(testSong.equals(o));

    }

}