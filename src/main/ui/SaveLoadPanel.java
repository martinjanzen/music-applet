package ui;

import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents a panel with save and load buttons that save and load your library state to file
public class SaveLoadPanel extends JInternalFrame implements ActionListener {
    private Library library;

    private JButton saveButton = new JButton("SAVE");
    private JButton loadButton = new JButton("LOAD");

    private static final String JSON_STORE = "./data/library.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ImageIcon floppy;

    // EFFECTS: constructs a new SaveLoadPanel
    public SaveLoadPanel(Library library) {
        super("Save/Load");

        this.library = library;

        this.add(saveButton);
        saveButton.addActionListener(this);
        this.add(loadButton);
        loadButton.addActionListener(this);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        floppy = new ImageIcon("floppy.png");


    }

    // MODIFIES: this
    // EFFECTS: interprets the button pressed and performs actions accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            this.goSave();
        }

        if (e.getSource() == loadButton) {
            this.goLoad();
        }
    }

    // EFFECTS: saves library to file
    public void goSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved " + library.getName() + " to " + JSON_STORE,
                    "Saved", JOptionPane.INFORMATION_MESSAGE, floppy);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE,
                    "Failed to save", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // EFFECTS: loads library from file
    public void goLoad() {
        try {
            this.library = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + library.getName() + " from " + JSON_STORE,
                    "Loaded", JOptionPane.INFORMATION_MESSAGE, floppy);
            new MusicAppUI(this.library);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE,
                    "Failed to load", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
