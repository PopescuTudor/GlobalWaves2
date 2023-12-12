package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.community.Event;
import app.community.Merch;
import app.player.Player;
import app.searchBar.SearchBar;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class Artist extends LibraryEntry{
    private String username;
    private int age;
    private String city;
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merch;


    // albums, events, merchandise
    public Artist(final String name, final String username, final int age, final String city) {
        super(name);
        this.username = username;
        this.age = age;
        this.city = city;
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.merch = new ArrayList<>();
    }

    /**
     * Add album.
     *
     * @param name the name of the album
     * @param owner the owner of the album
     * @param description the description of the album
     * @param songs the songs of the album
     * @param releaseYear the release year of the album
     * @param timestamp the timestamp of the album
     */
    public String addAlbum(final String name, final String owner, final String description,
                           final ArrayList<Song> songs, final Integer releaseYear,
                           final int timestamp) {
        String message = null;

        // check for an album with the same name
        for (final Album a : this.albums) {
            if (a.getName().equals(name)) {
                message = username + " has another album with the same name.";
                break;
            }
        }
        if (message == null) {
            // check for songs with the same name in the album
            if (hasDuplicates(songs)) {
                message = username + " has the same song at least twice in this album.";
            }
            else {
                final Album album = new Album(name, owner, description, songs, releaseYear,
                                        timestamp);
                this.albums.add(album);
                Admin.getInstance().addSongs(songs);

                message = username + " has added new album successfully.";
            }
        }

        return message;
    }

    /**
     * remove album
     *
     * @param name the name of the album
     */
    public String removeAlbum(final String name) {
        String message = null;

        // check for an album with the same name
        for (final Album a : this.albums) {
            if (a.getName().equals(name)) {

                // check for any user that might listen to a song from the album
                for (final User user : Admin.getInstance().getUsers()) {
                    if(user.getPlayer().getSource() == null) {
                        continue;
                    }
                    Song song = (Song) user.getPlayer().getSource().getAudioFile();
                    if (song.getAlbum().equals(name)) {
                        message = username + " can't delete this album.";
                        return message;
                    }
                    // check for any playlist that might contain a song from the album
                    for (Playlist playlist : user.getPlaylists()) {
                        for (Song s : playlist.getSongs()) {
                            if (s.getAlbum().equals(name)) {
                                message = username + " can't delete this album.";
                                return message;
                            }
                        }
                    }
                }
                // remove all songs from the album from the admin's list
                Admin.getInstance().removeSongs(a);
                this.albums.remove(a);
                message = username + " deleted the album successfully.";
                break;
            }
        }
        if (message == null) {
            message = username + " doesn't have an album with the given name.";
        }

        return message;
    }

    private static boolean hasDuplicates(ArrayList<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            for (int j = i + 1; j < songs.size(); j++) {
                if (songs.get(i).getName().equals(songs.get(j).getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add event for the artist
     *
     * @param name the name of the event
     * @param description the description of the event
     * @param date the date of the event
     */
    public String addEvent(final String name, final String description, final String date) {
        String message = null;
        String DATE_REGEX =
                "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19\\d\\d|20[0-2][0-3])$";
        // check for an event with the same name
        for (final Event e : this.events) {
            if (e.getName().equals(name)) {
                message = username + " has another event with the same name.";
                break;
            }
        }
        if (message == null) {
            // check for a valid date
            if (!date.matches(DATE_REGEX)) {
                message = "Event for " + username + " does not have a valid date.";
            }
        }

        // add event
        if (message == null) {
            final Event event = new Event(name, description, date);
            this.events.add(event);
            message = username + " has added new event successfully.";
        }

        return message;
    }

    /**
     * Removes event for the artist
     *
     * @param name the name of the event
     */
    public String removeEvent(final String name) {
        String message = null;

        // check for an event with the same name
        if (this.events != null) {

            for (final Event e : this.events) {
                if (e.getName().equals(name)) {
                    this.events.remove(e);
                    message = username + " deleted the event successfully.";
                    break;
                }
            }
        }
        if (message == null) {
            message = username + " doesn't have an event with the given name.";
        }

        return message;
    }

    /**
     * add merch item for the artist
     *
     * @param name the name of the merch item
     * @param description the description of the merch item
     * @param price the price of the merch item
     */
    public String addMerch(final String name, final String description, final int price) {
        String message = null;

        // check for a merch item with the same name
        for (final Merch m : this.merch) {
            if (m.getName().equals(name)) {
                message = username + " has merchandise with the same name.";
                break;
            }
        }
        // check for negative price
        if (message == null) {
            if (price < 0) {
                message = "Price for merchandise can not be negative.";
            }
        }
        // add merch item
        if (message == null) {
            final Merch merch = new Merch(name, description, price);
            this.merch.add(merch);
            message = username + " has added new merchandise successfully.";
        }

        return message;
    }

    /**
     * print artist's page
     *
     * @return the string
     */
    public String printArtistPage() {
        ArrayList<String> albums = new ArrayList<>();
        ArrayList<String> merch = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();

        for (Album album : this.albums) {
            albums.add(album.printAlbum());
        }
        for (Merch merchItem : this.merch) {
            merch.add(merchItem.printMerch());
        }
        for (Event event : this.events) {
            events.add(event.printEvent());
        }
        return String.format("Albums:\n\t%s\n\nMerch:\n\t%s\n\nEvents:\n\t%s",
                albums, merch, events);
    }

}
