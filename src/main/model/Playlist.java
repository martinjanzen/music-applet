package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a playlist having a name, creator, and list of songs
public class Playlist implements Writable {
    private String name;             // name of the playlist
    private String creator;          // name of the playlist creator
    private ArrayList<Song> songs;   // list of songs on the playlist

    // EFFECTS: constructor for Playlist
    public Playlist(String name, String creator) {
        this.name = name;
        this.creator = creator;
        this.songs = new ArrayList<>();
    }

    // --------------------------- GETTERS --------------------------- //

    public String getName() {
        return this.name;
    }

    public String getCreator() {
        return this.creator;
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public int getNumSongs() {
        return this.songs.size();
    }

    // --------------------------- --------------------------- --------------------------- //

    // MODIFIES: this, song
    // EFFECTS: if song is already on playlist, return false.
    //          if song is not on playlist, add song to playlist AND
    //                                      return true.
    public boolean addSong(Song song) {
        Event added = new Event("Added a song to a playlist.");
        if (!this.getSongs().contains(song)) {
            this.songs.add(song);
            EventLog.getInstance().logEvent(added);
            return true;
        } else {
            return false;
        }
    }


    // MODIFIES: this, song
    // EFFECTS: if song is not on playlist, return false.
    //          if song is on playlist, remove song from playlist AND
    //                                  return true.
    public boolean removeSong(Song song) {
        Event removed = new Event("Removed a song from a playlist.");
        if (this.getSongs().contains(song)) {
            this.songs.remove(song);
            EventLog.getInstance().logEvent(removed);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns "song - artist" in order with a linebreak after every song for a playlist
    //          if a playlist has no songs, return "This playlist is empty!"
    public String listSongs() {
        StringBuffer result = new StringBuffer();
        Event listedSongs = new Event("Listed a playlist's songs.");
        EventLog.getInstance().logEvent(listedSongs);
        if (!this.getSongs().isEmpty()) {
            for (Song s : songs) {
                result.append(s.getName() + " - " + s.getArtist() + "\n");
            }
        } else {
            result.append("This playlist is empty!");
        }
        return result.toString();
    }

    // EFFECTS: returns name, creator, and numSongs in a user-friendly way.
    //          if playlist is empty, return a special statement.
    public String listPlaylistInfo() {
        String name = this.getName();
        String creator = this.getCreator();
        String numSongs = Integer.toString(this.getNumSongs());
        Event listedInfo = new Event("Listed a playlist's info.");
        EventLog.getInstance().logEvent(listedInfo);
        if (!this.getSongs().isEmpty()) {
            return name + " is a playlist created by " + creator + " that has " + numSongs + " song(s).";
        } else {
            return name + " is a playlist created by " + creator + " that has no songs.";
        }
    }


    // REQUIRES: both playlists must not be empty
    // MODIFIES: this
    // EFFECTS: adds all songs from playlist2 to this playlist in order of called playlist, playlist2.
    public Playlist merge(Playlist playlist2) {
        ArrayList<Song> p2Songs = playlist2.getSongs();
        Event merged = new Event("Merged two playlists.");
        EventLog.getInstance().logEvent(merged);
        for (Song s : p2Songs) {
            this.addSong(s);
        }
        return this;
    }

    // converts Playlist to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("creator", creator);
        json.put("songs", songs);
        return json;
    }


}
