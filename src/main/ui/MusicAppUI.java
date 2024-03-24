package ui;

import model.EventLog;
import model.Library;
import model.Playlist;
import model.Song;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// represents a MusicApp user interface that the user can interact with
public class MusicAppUI extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private Library library;
    private Song song1;
    private Song song2;
    private Song song3;
    private Playlist playlist1;
    private Playlist playlist2;

    private SongPanel gnawPanel;
    private SongPanel aboutAGirlPanel;
    private SongPanel angelinaPanel;

    private PlaylistPanel favoriteSongsPanel;
    private PlaylistPanel studySongsPanel;

    private SaveLoadPanel saveLoadPanel;

    // EFFECTS: constructs a new MusicAppUI
    public MusicAppUI() throws FileNotFoundException {
        this.setLayout(new GridLayout(3,2));

        setTitle("MUSIC ORGANIZER");
        setSize(WIDTH, HEIGHT);

        setup();

        addJFrame(gnawPanel);
        addJFrame(aboutAGirlPanel);
        addJFrame(angelinaPanel);
        addJFrame(favoriteSongsPanel);
        addJFrame(studySongsPanel);
        addJFrame(saveLoadPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                System.exit(0);
            }
        });



    }

    // Overloaded MusicAppUI constructor that uses given library
    public MusicAppUI(Library library) {
        this.setLayout(new GridLayout(3,2));

        setTitle("MUSIC ORGANIZER");
        setSize(WIDTH, HEIGHT);

        setup(library);

        addJFrame(gnawPanel);
        addJFrame(aboutAGirlPanel);
        addJFrame(angelinaPanel);
        addJFrame(favoriteSongsPanel);
        addJFrame(studySongsPanel);
        addJFrame(saveLoadPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: centers this on the screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: adds a given JInternalFrame to this JFrame
    private void addJFrame(JInternalFrame j) {
        j.setLayout(new GridLayout(3, 0));
        j.setVisible(true);
        this.add(j);
    }

    // EFFECTS: initializes required fields for MusicAppUI
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

        gnawPanel = new SongPanel(song1);
        aboutAGirlPanel = new SongPanel(song2);
        angelinaPanel = new SongPanel(song3);
        favoriteSongsPanel = new PlaylistPanel(playlist1, library);
        studySongsPanel = new PlaylistPanel(playlist2, library);
        saveLoadPanel = new SaveLoadPanel(library);
    }

    // EFFECTS: overloaded setup method that allows load from given library
    private void setup(Library library) {
        song1 = library.getSongs().get(0);
        song2 = library.getSongs().get(1);
        song3 = library.getSongs().get(2);
        playlist1 = library.getPlaylists().get(0);
        playlist2 = library.getPlaylists().get(1);
        library.addSong(song1);
        library.addSong(song2);
        library.addSong(song3);
        library.addPlaylist(playlist1);
        library.addPlaylist(playlist2);

        gnawPanel = new SongPanel(song1);
        aboutAGirlPanel = new SongPanel(song2);
        angelinaPanel = new SongPanel(song3);
        favoriteSongsPanel = new PlaylistPanel(playlist1, library);
        studySongsPanel = new PlaylistPanel(playlist2, library);
        saveLoadPanel = new SaveLoadPanel(library);
    }

}
