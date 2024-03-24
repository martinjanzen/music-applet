package persistence;

import model.Song;
import model.Playlist;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test suite for JsonWriter class
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library l = new Library();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            l = reader.read();
            assertEquals("Library", l.getName());
            assertEquals(0, l.getNumSongs());
            assertEquals(0, l.getNumPlaylists());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            Library l = new Library();
            l.addSong(new Song("Gnaw", "Alex G", "Race", 201, "Indie"));
            l.addSong(new Song("About A Girl", "Nirvana", "Bleach", 217, "Grunge"));
            l.addSong(new Song("Angelina", "Pinegrove", "Everything So Far", 101, "Indie"));
            l.addPlaylist(new Playlist("Favorite Songs", "Martin"));
            l.addPlaylist(new Playlist("Study Songs", "Martin"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            l = reader.read();
            assertEquals("Library", l.getName());
            List<Song> songs = l.getSongs();
            assertEquals(3, l.getNumSongs());
            checkSong("Gnaw", "Alex G", "Race", 201, "Indie", songs.get(0));
            checkSong("About A Girl", "Nirvana", "Bleach", 217, "Grunge", songs.get(1));
            checkSong("Angelina", "Pinegrove", "Everything So Far", 101, "Indie", songs.get(2));

            List<Playlist> playlists = l.getPlaylists();
            assertEquals(2, l.getNumPlaylists());
            checkPlaylist("Favorite Songs", "Martin", playlists.get(0));
            checkPlaylist("Study Songs", "Martin", playlists.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}