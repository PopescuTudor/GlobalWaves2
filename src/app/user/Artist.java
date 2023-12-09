package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.player.Player;
import app.searchBar.SearchBar;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class Artist {

    private String username;
    private int age;
    private String city;
    private ArrayList<Album> albums;


    // albums, events, merchandise
    public Artist(final String username, final int age, final String city) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.albums = new ArrayList<>();
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
}
