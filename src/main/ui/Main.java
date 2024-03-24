package ui;

import java.io.FileNotFoundException;

// Represents a space where the MusicApp can be run
public class Main {
    public static void main(String[] args) {
        try {
            new MusicAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
