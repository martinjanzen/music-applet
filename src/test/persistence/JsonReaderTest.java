package persistence;

import model.Song;
import model.Playlist;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test suite for JsonReader class
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library l = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            Library l = reader.read();
            assertEquals("Library", l.getName());
            assertEquals(0, l.getNumSongs());
            assertEquals(0, l.getNumPlaylists());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            Library l = reader.read();
            assertEquals("Library", l.getName());
            List<Song> songs = l.getSongs();
            List<Playlist> playlists = l.getPlaylists();
            assertEquals(3, l.getNumSongs());
            assertEquals(2, l.getNumPlaylists());
            checkSong("Gnaw","Alex G","Race",201, "Indie", songs.get(0));
            checkSong("About A Girl","Nirvana","Bleach",217, "Grunge", songs.get(1));
            checkSong("Angelina","Pinegrove","Everything So Far",101, "Indie", songs.get(2));
            checkPlaylist("Favorite Songs","Martin", playlists.get(0));
            checkPlaylist("Study Songs","Martin", playlists.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testTestLibrary() {
        JsonReader reader = new JsonReader("./data/testLibrary.json");
        try {
            Library l = reader.read();
            assertEquals("Library", l.getName());
            List<Song> songs = l.getSongs();
            List<Playlist> playlists = l.getPlaylists();
            assertEquals(3, l.getNumSongs());
            assertEquals(2, l.getNumPlaylists());
            checkSong("Gnaw","Alex G","Race",201, "Indie", songs.get(0));
            checkSong("About A Girl","Nirvana","Bleach",217, "Grunge", songs.get(1));
            checkSong("Angelina","Pinegrove","Everything So Far",101, "Indie", songs.get(2));
            checkPlaylist("Favorite Songs","Martin", playlists.get(0));
            checkPlaylist("Study Songs","Martin", playlists.get(1));

            checkSong("Gnaw","Alex G","Race",201, "Indie", playlists.get(0).getSongs().get(0));
            checkSong("About A Girl","Nirvana","Bleach",217, "Grunge", playlists.get(1).getSongs().get(0));
            checkSong("Angelina","Pinegrove","Everything So Far",101, "Indie", playlists.get(1).getSongs().get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testOneSongsInPlaylistLibrary() {
        JsonReader reader = new JsonReader("./data/testOneSongInPlaylistLibrary.json");
        try {
            Library l = reader.read();
            assertEquals("Library", l.getName());
            List<Song> songs = l.getSongs();
            List<Playlist> playlists = l.getPlaylists();
            assertEquals(1, l.getNumSongs());
            assertEquals(2, l.getNumPlaylists());
            checkSong("About A Girl","Nirvana","Bleach",217, "Grunge", songs.get(0));
            checkPlaylist("Favorite Songs","Martin", playlists.get(0));
            checkPlaylist("Study Songs","Martin", playlists.get(1));

            checkSong("About A Girl","Nirvana","Bleach",217, "Grunge", playlists.get(1).getSongs().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }


}