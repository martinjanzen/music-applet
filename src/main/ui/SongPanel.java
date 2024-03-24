package ui;

import model.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a panel that displays actions you can perform on song via buttons
public class SongPanel extends JInternalFrame implements ActionListener {
    private JButton likeButton = new JButton("LIKE");
    private JButton unlikeButton = new JButton("UNLIKE");
    private JButton listInfoButton = new JButton("LIST INFO");

    private Song song;

    // EFFECTS: constructs a new SongPanel
    public SongPanel(Song song) {
        super(song.getName() + " - " + song.getArtist());
        this.song = song;

        this.add(likeButton);
        likeButton.addActionListener(this);
        this.add(unlikeButton);
        unlikeButton.addActionListener(this);
        this.add(listInfoButton);
        listInfoButton.addActionListener(this);

    }

    // MODIFIES: this
    // EFFECTS: interprets the button pressed and performs actions accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == likeButton) {
            this.song.like();
        }

        if (e.getSource() == unlikeButton) {
            this.song.unlike();
        }

        if (e.getSource() == listInfoButton) {
            JOptionPane.showMessageDialog(null, this.song.listSongInfo(),
                        this.song.getName() + " Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
