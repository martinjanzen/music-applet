package persistence;

import model.Library;
import model.Playlist;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses library from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        Library l = new Library();
        addSongs(l, jsonObject);
        addPlaylists(l, jsonObject);
        return l;
    }

    // MODIFIES: l
    // EFFECTS: parses songs from JSON object and adds them to library
    private void addSongs(Library l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(l, nextSong);
        }
    }

    // MODIFIES: l
    // EFFECTS: parses song from JSON object and adds it to library
    private void addSong(Library l, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        String album = jsonObject.getString("album");
        int length = jsonObject.getInt("length");
        String genre = jsonObject.getString("genre");
        boolean liked = jsonObject.getBoolean("liked");

        Song song = new Song(name, artist, album, length, genre);
        if (liked) {
            song.like();
        }
        l.addSong(song);
    }

    // MODIFIES: l
    // EFFECTS: parses playlists from JSON object and adds them to library
    private void addPlaylists(Library l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("playlists");
        for (Object json : jsonArray) {
            JSONObject nextPlaylist = (JSONObject) json;
            addPlaylist(l, nextPlaylist);
        }
    }

    // MODIFIES: l
    // EFFECTS: parses playlist from JSON object and adds it to library
    private void addPlaylist(Library l, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String creator = jsonObject.getString("creator");
        JSONArray songs = jsonObject.getJSONArray("songs");
        ArrayList<Song> listOfSongs = l.getSongs();
        Playlist playlist = new Playlist(name, creator);
        for (Object json : songs) {
            JSONObject nextSong = (JSONObject) json;
            Song newSong = makeNewSong(nextSong);
            for (Song s : listOfSongs) {
                if (s.equals(newSong)) {
                    playlist.addSong(s);
                    break;
                }
            }
        }
        l.addPlaylist(playlist);
    }

    // MODIFIES: this
    // EFFECTS: makes a new Song object according to the given JSONObject
    private Song makeNewSong(JSONObject nextSong) {
        Song newSong = new Song(
                nextSong.getString("name"),
                nextSong.getString("artist"),
                nextSong.getString("album"),
                nextSong.getInt("length"),
                nextSong.getString("genre"));
        if (nextSong.getBoolean("liked")) {
            newSong.like();
        }
        return newSong;
    }

}
