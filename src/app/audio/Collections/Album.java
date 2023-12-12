package app.audio.Collections;

import app.audio.Files.Song;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class Album extends AudioCollection {
    private final String description;
    private final ArrayList<Song> songs;
    private final Integer releaseYear;
    private final int timestamp;

    /**
     * initiates a new album
     * @param name the name of the album
     * @param owner the owner of the album
     * @param description the description of the album
     * @param releaseYear the release year of the album
     */
    public Album(final String name, final String owner, final String description,
                 final ArrayList<Song> songs, final Integer releaseYear) {
        this(name, owner, description, songs, releaseYear, 0);
    }

    /**
     * initiates a new album
     * @param name the name of the album
     * @param owner the owner of the album
     * @param description the description of the album
     * @param releaseYear the release year of the album
     * @param timestamp the timestamp of the album
     */
    public Album(final String name, final String owner, final String description,
                 final ArrayList<Song> songs, final Integer releaseYear, final int timestamp) {
        super(name, owner);
        this.description = description;
        this.songs = songs;
        this.timestamp = timestamp;
        this.releaseYear = releaseYear;
    }

    @Override
    public int getNumberOfTracks() {
        return this.songs.size();
    }

    @Override
    public Song getTrackByIndex(final int index) {
        return this.songs.get(index);
    }

    /**
     * method to print album in format required for printArtistsPage
     *
     * @return String
     */
    public String printAlbum() {
        String album = "";
        album += this.getName();
        return album;
    }

    /**
     * method to get total like in album
     *
     * @return int
     */
    public int getLikes() {
        int totalLikes = 0;
        for (final Song song : this.songs) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }
}
