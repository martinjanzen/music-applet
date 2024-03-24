package ui;

import model.Library;
import model.Playlist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a Playlist Panel that acts as a JInternalFrame
public class PlaylistPanel extends JInternalFrame implements ActionListener {
    private Playlist playlist;
    private Library library;

    private JButton addSong = new JButton("ADD SONG");
    private JButton removeSong = new JButton("REMOVE SONG");
    private JButton listSongs = new JButton("LIST SONGS");
    private JButton listInfo = new JButton("LIST INFO");
    private JButton merge = new JButton("MERGE");

    // EFFECTS: constructs a new PlaylistPanel
    public PlaylistPanel(Playlist playlist, Library library) {
        super(playlist.getName());
        this.playlist = playlist;
        this.library = library;

        this.add(addSong);
        addSong.addActionListener(this);
        this.add(removeSong);
        removeSong.addActionListener(this);
        this.add(listSongs);
        listSongs.addActionListener(this);
        this.add(listInfo);
        listInfo.addActionListener(this);
        this.add(merge);
        merge.addActionListener(this);
    }


    // MODIFIES: this
    // EFFECTS: interprets the button pressed and performs actions accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSong) {
            doAddSong();
        }

        if (e.getSource() == removeSong) {
            doRemoveSong();
        }

        if (e.getSource() == listSongs) {
            doListSongs();
        }

        if (e.getSource() == listInfo) {
            doListInfo();
        }

        if (e.getSource() == merge) {
            doMerge();
        }

    }

    // MODIFIES: this
    // EFFECTS: opens a new interactions pane that prompts user to choose a playlist to merge with
    public void doMerge() {
        String[] options = {"Favorite Songs", "Study Songs", "Cancel"};
        var option = JOptionPane.showOptionDialog(this,
                "Which playlist would you like to merge with?",
                "Merge which playlist?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);
        if (option != 3) {
            this.playlist.merge(library.getPlaylists().get(option));
        }
    }

    // EFFECTS: opens a new interactions pane that displays playlist information
    public void doListInfo() {
        JOptionPane.showMessageDialog(null, this.playlist.listPlaylistInfo(),
                this.playlist.getName() + " Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: opens a new interactions pane that displays songs on playlist
    public void doListSongs() {
        JOptionPane.showMessageDialog(null, this.playlist.listSongs(),
                "Songs in " + this.playlist.getName(), JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: opens a new interactions pane that prompts user to remove a song from the playlist (this)
    public void doRemoveSong() {
        String[] options = {"Gnaw", "About A Girl", "Angelina"};
        var option = JOptionPane.showOptionDialog(this,
                "Which song would you like to remove?",
                "Remove which song?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);
        this.playlist.removeSong(library.getSongs().get(option));
    }

    // MODIFIES: this
    // EFFECTS: opens a new interactions pane that prompts user to add a song to the playlist (this)
    public void doAddSong() {
        String[] options = {"Gnaw", "About A Girl", "Angelina"};
        var option = JOptionPane.showOptionDialog(this, "Which song would you like to add?",
                "Add which song?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);
        this.playlist.addSong(library.getSongs().get(option));
    }


}
