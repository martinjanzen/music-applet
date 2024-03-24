# My Personal Project - A Music Organizer

## Project Proposal

My music organizer application will have the capacity to sort music based on various criteria.
It will have the basic functionality of other music streaming applications but with added
dexterity and specialization in organization and information. My application can be used by *anyone* 
who wants to organize their favorite songs, and, more broadly, by anyone who **loves** music. 
This project is important to me because of
my own love for music. I find that a lot of music streaming applications lack some functionality
that I would want to implement, and while my application will not offer streaming, it is meant to
focus more on the organizational aspect of design. I want to make it easier for people to access
and sort their music, enhancing overall user experience and expression.

## User Stories

- As a user, I want to be able to "like" a song.
- As a user, I want to be able to "unlike" a song.
- As a user, I want to be able to add and remove songs (X) from a playlist (Y).
- As a user, I want to be able to merge playlists
- As a user, I want to be able to view song statistics (e.g. length, artist, title, album etc.).
- As a user, I want to be able to list songs within a playlist.
- As a user, I want to be able to add songs to my library.
- As a user, I want to be able to add playlists to my library.
- As a user, I want to be able to save library contents to file.
- As a user, I want to be able to be able to load my library contents from file.

## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by pressing the "ADD SONG" button, and then
  clicking the desired song to add when prompted by the pop-up.
- You can then remove a song (X) from a playlist (Y) by pressing the "REMOVE SONG" button, and then clicking the
  desired song when prompted by the pop-up.
- You can generate the second required action related to adding Xs to a Y by pressing the "MERGE" button, and then
  clicking the desired playlist to merge with.
- Additionally, you can list a song or playlists info by pressing its "LIST INFO" button and observing the pop-up.
- You can then see all these changes by pressing the "LIST SONGS" button.
- You can locate my visual component by saving or loading, an image representing a file will appear on the JOptionPane.
- You can save the state of my application by pressing the "SAVE" button and closing the application.
- You can reload the state of my application by pressing the "LOAD" button.

## Phase 4: Task 2

- When a song is added to a playlist, EventLog will display "Added a song to a playlist." **
- When a song is removed from a playlist, EventLog will display "Removed a song from a playlist." **
- When a playlist's songs are listed, EventLog will display "Listed a playlist's songs." **
- When a playlist's info is listed, EventLog will display "Listed a playlist's info." **
- When two playlists are merged, EventLog will display "Merged two playlists." **
- When a song is liked, EventLog will display "Liked a song." **
- When a song is unliked, EventLog will display "Unliked a song." **
- \** = (Along with the date and time the action was performed)

## Phase 4: Task 3

In my UML class diagram, there are no extends relationships between classes. This is because a lot of my classes get
other classes as fields, but this is not the greatest design. If I had more time, I would have made an abstract Panel
class which then would be extended or implemented by the SongPanel, PlaylistPanel, and SaveLoadPanel classes, as
these all share similar behaviors that can be abstracted. Similarly, I could have implemented the relationship between
my Library, Playlist, and Song classes better. Perhaps I could have treated these three classes using the composite
pattern, where Song is my leaf, Playlist is the composite, and Library is the branch. This way, I would be able to
call the Library a playlist, and treat a regular playlist as a playlist that only has Songs (leaves). This would clean
up some of the associations in my UML class diagram, but might also introduce issues of playlists containing other
playlists when this behavior is not desired. So overall refactoring my code this way would take further research to
decide whether this is a wise decision, and whether it's worth cleaning up associations when introducing such a risk.


### References

- JsonSerializationDemo-master.java
- AlarmSystem.java


