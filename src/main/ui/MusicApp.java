package ui;

import model.Playlist;
import model.Song;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Music Organizer Application
public class MusicApp {
    private static final String JSON_STORE = "./data/library.json";

    private Song song1;
    private Song song2;
    private Song song3;
    private Playlist playlist1;
    private Playlist playlist2;
    private Library library;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the Music Organizer application
    public MusicApp() throws FileNotFoundException {
        runMusic();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMusic() {
        boolean active = true;
        String userInfo;

        setup();

        while (active) {
            options();
            userInfo = input.next();
            userInfo = userInfo.toLowerCase();

            if (userInfo.equals("quit")) {
                active = false;
            } else {
                interp(userInfo);
            }
        }

        System.out.println("\nThank you, have a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void setup() {
        song1 = new Song("Gnaw", "Alex G", "Race", 201, "Indie");
        song2 = new Song("About A Girl", "Nirvana", "Bleach", 217, "Grunge");
        song3 = new Song("Angelina", "Pinegrove", "Everything So Far", 101, "Indie");
        playlist1 = new Playlist("Favorite Songs", "Martin");
        playlist2 = new Playlist("Study Songs", "Martin");
        library = new Library();
        library.addSong(song1);
        library.addSong(song2);
        library.addSong(song3);
        library.addPlaylist(playlist1);
        library.addPlaylist(playlist2);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void options() {
        System.out.println("\nOptions:");
        System.out.println("like -> like a song");
        System.out.println("unlike -> unlike a song");
        System.out.println("info -> list song / playlist info");
        System.out.println("list -> list songs in a playlist");
        System.out.println("add -> add a song to a playlist");
        System.out.println("remove -> remove a song from a playlist");
        System.out.println("merge -> add one playlist to another");
        System.out.println("save -> save the current state of the library");
        System.out.println("load -> load a file previously saved");
        System.out.println("quit\n");
    }


    // MODIFIES: this
    // EFFECTS: interprets userInfo and executes methods according to input given.
    private void interp(String userInfo) {
        if (userInfo.equals("like")) {
            goLike();
        } else if (userInfo.equals("unlike")) {
            goUnlike();
        } else if (userInfo.equals("info")) {
            goInfo();
        } else if (userInfo.equals("list")) {
            goList();
        } else if (userInfo.equals("add")) {
            goAdd();
        } else if (userInfo.equals("remove")) {
            goRemove();
        } else if (userInfo.equals("merge")) {
            goMerge();
        } else if (userInfo.equals("save")) {
            goSave();
        } else if (userInfo.equals("load")) {
            goLoad();
        } else {
            System.out.println("Not a valid option, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: likes the given song according to the like method in the Song class
    public void goLike() {
        System.out.println("\nWhich song?");
        this.listSongs();
        String userInfo = input.next();

        library.getSongs().get(Integer.parseInt(userInfo) - 1).like();
        System.out.println(library.getSongs().get(Integer.parseInt(userInfo) - 1).getName() + " is now liked!");
    }

    // MODIFIES: this
    // EFFECTS: unlikes the given song according to the like method in the Song class
    public void goUnlike() {
        System.out.println("\nWhich song?");
        this.listSongs();
        String userInfo = input.next();

        library.getSongs().get(Integer.parseInt(userInfo) - 1).unlike();
        System.out.println(library.getSongs().get(Integer.parseInt(userInfo) - 1).getName() + " is now unliked!");
    }

    // EFFECTS: lists Song or Playlist info according to the method in either class
    public void goInfo() {
        System.out.println("\nList what info?");
        this.listSongsAndPlaylists();
        String userInfo = input.next();

        if (Integer.parseInt(userInfo) <= library.getNumSongs()) {
            System.out.println(library.getSongs().get(Integer.parseInt(userInfo) - 1).listSongInfo());
        } else {
            System.out.println(library.getPlaylists().get(Integer.parseInt(userInfo)
                    - library.getNumSongs() - 1).listPlaylistInfo());
        }
    }

    //EFFECTS: lists playlist info according to the method in the Playlist class
    public void goList() {
        System.out.println("\nList which playlist's songs?");
        this.listPlaylists();
        String userInfo = input.next();
        System.out.println(library.getPlaylists().get(Integer.parseInt(userInfo) - 1).listSongs());
    }

    // MODIFIES: this
    // EFFECTS: adds a given song to a given playlist according to the add methods in either class
    public void goAdd() {
        System.out.println("\nSong:");
        this.listSongs();
        String userInfo1 = input.next();
        System.out.println("\nPlaylist:");
        this.listPlaylists();
        String userInfo2 = input.next();
        Song song = library.getSongs().get(Integer.parseInt(userInfo1) - 1);
        Playlist playlist = library.getPlaylists().get(Integer.parseInt(userInfo2) - 1);
        playlist.addSong(song);
        System.out.println(song.getName() + " added to " + playlist.getName());
    }

    // MODIFIES: this
    // EFFECTS: removes a given song from a given playlist according to the remove methods in either class
    public void goRemove() {
        System.out.println("\nSong:");
        this.listSongs();
        String userInfo1 = input.next();
        System.out.println("\nPlaylist:");
        this.listPlaylists();
        String userInfo2 = input.next();
        Song song = library.getSongs().get(Integer.parseInt(userInfo1) - 1);
        Playlist playlist = library.getPlaylists().get(Integer.parseInt(userInfo2) - 1);
        playlist.removeSong(song);
        System.out.println(song.getName() + " removed from " + playlist.getName());
    }

    // MODIFIES: this
    // EFFECTS: adds songs from first playlist onto second playlist according to the merge method in Playlist class
    public void goMerge() {
        System.out.println("\nFirst playlist:");
        this.listPlaylists();
        String userInfo1 = input.next();
        System.out.println("\nSecond playlist:");
        this.listPlaylists();
        String userInfo2 = input.next();
        Playlist playlist1 = library.getPlaylists().get(Integer.parseInt(userInfo1) - 1);
        Playlist playlist2 = library.getPlaylists().get(Integer.parseInt(userInfo2) - 1);
        playlist1.merge(playlist2);
        System.out.println(playlist2.getName() + " merged onto " + playlist1.getName());
    }

    // EFFECTS: saves library to file
    public void goSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Saved " + library.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads library from file
    public void goLoad() {
        try {
            library = jsonReader.read();
            System.out.println("Loaded " + library.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: lists songs in library with correct index
    public void listSongs() {
        for (int i = 0; i < library.getNumSongs(); i++) {
            System.out.println("(" + (i + 1) + ") " + library.getSongs().get(i).getName()
                    + " - " + library.getSongs().get(i).getArtist());
        }
    }

    // EFFECTS: lists playlists in library with correct index
    public void listPlaylists() {
        for (int i = 0; i < library.getNumPlaylists(); i++) {
            System.out.println("(" + (i + 1) + ") " + library.getPlaylists().get(i).getName());
        }
    }

    // EFFECTS: lists both songs and playlists in library with correct index.
    public void listSongsAndPlaylists() {
        int count = 1;
        for (Song s : library.getSongs()) {
            System.out.println("(" + count + ") " + s.getName() + " - " + s.getArtist());
            count++;
        }
        for (Playlist p : library.getPlaylists()) {
            System.out.println("(" + count + ") " + p.getName());
            count++;
        }
    }


}

