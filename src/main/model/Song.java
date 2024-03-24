package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents a song having a name, artist, album, length (sec), genre, and liked status.
public class Song implements Writable {
    private String name;                      // name of the song
    private String artist;                    // name of the artist
    private String album;                     // name of the album
    private int length;                       // length of song in seconds
    private String genre;                     // genre of the song
    private boolean liked;                    // true if song is liked, false otherwise


    // REQUIRES: name, artist, album, and genre are not empty Strings.
    //           length > 0.
    // EFFECTS: creates a song with given name, artist, album, length, and genre.
    public Song(String name, String artist, String album, int length, String genre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.genre = genre;
        this.liked = false;
    }

    // --------------------------- GETTERS --------------------------- //

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getLength() {
        return this.length;
    }

    public boolean isLiked() {
        return this.liked;
    }

    // --------------------------- --------------------------- --------------------------- //

    // MODIFIES: this
    // EFFECTS: changes liked status to true
    public void like() {
        Event liked = new Event("Liked a song.");
        EventLog.getInstance().logEvent(liked);
        this.liked = true;
    }

    // MODIFIES: this
    // EFFECTS: changes liked status to false
    public void unlike() {
        Event unliked = new Event("Unliked a song.");
        EventLog.getInstance().logEvent(unliked);
        this.liked = false;
    }


    // EFFECTS: lists song information in paragraph format. Liked status is indicated
    public String listSongInfo() {
        String name = this.getName();
        String artist = this.getArtist();
        String album = this.getAlbum();
        String genre = this.getGenre();
        String length = Integer.toString(this.getLength());
        String liked = "This song is liked!";
        String unliked = "This song is not liked!";
        Event listedInfo = new Event("Listed a song's info.");
        EventLog.getInstance().logEvent(listedInfo);
        if (this.isLiked()) {
            return name + " by " + artist + " on the album " + album + " is a(n) "
                    + genre + " song that is " + length + " seconds long. " + liked;
        } else {
            return name + " by " + artist + " on the album " + album + " is a(n) "
                    + genre + " song that is " + length + " seconds long. " + unliked;
        }

    }

    // EFFECTS: creates a new JSONObject with song info
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("artist", artist);
        json.put("album", album);
        json.put("length", length);
        json.put("genre", genre);
        json.put("liked", liked);
        return json;
    }

    // EFFECTS: new equals operator that compares just name and artist of songs
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Song song = (Song) o;

        if (!Objects.equals(name, song.name)) {
            return false;
        }
        if (!Objects.equals(artist, song.artist)) {
            return false;
        }
        return true;
    }
}
