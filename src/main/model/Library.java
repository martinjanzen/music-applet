package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a library having songs and playlists
public class Library implements Writable {
    private String name;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;

    // Constructs a new library with no songs and no playlists
    public Library() {
        this.name = "Library";
        this.songs = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    // MODIFIES: this
    // EFFECTS: adds a song to the library
    public void addSong(Song song) {
        this.songs.add(song);
    }

    // MODIFIES: this
    // EFFECTS: adds a playlist to the library
    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    // EFFECTS: returns the number of songs in a library
    public int getNumSongs() {
        return this.songs.size();
    }

    // EFFECTS: returns the number of playlists in a library
    public int getNumPlaylists() {
        return this.playlists.size();
    }

    // EFFECTS: converts library to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());
        json.put("playlists", playlistsToJson());
        return json;
    }

    // EFFECTS: returns songs in this library as a JSON array
    public JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songs) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns playlists in this library as a JSON array
    public JSONArray playlistsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Playlist p : playlists) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }


}
