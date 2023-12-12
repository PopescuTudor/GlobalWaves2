package app;

import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.PlayerSource;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
    @Getter
    private List<User> users = new ArrayList<>();
    @Getter
    private List<Artist> artists = new ArrayList<>();
    @Getter
    private List<Host> hosts = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();
    private List<Podcast> podcasts = new ArrayList<>();
    private int timestamp = 0;
    private final int LIMIT = 5;
    // singleton pattern
    private static Admin instance = null;

    private Admin() {
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Add songs.
     *
     * @param songs the songs
     */
    public void addSongs(final List<Song> songs) {
        this.songs.addAll(songs);
    }

    /**
     * remove songs from admin list contained in an album
     *
     * @param album the album
     */
    public void removeSongs(final Album album) {
        for (Song song : album.getSongs()) {
            songs.remove(song);
        }
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * remove podcast from admin list
     *
     * @param podcast the podcast
     */
    public void removePodcast(final Podcast podcast) {
        podcasts.remove(podcast);
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets albums.
     *
     * @return the albums
     */
    public List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        for (Artist artist : artists) {
            albums.addAll(artist.getAlbums());
        }
        return albums;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Gets artist.
     *
     * @param artistName the artist name
     * @return the artist
     */
    public Artist getArtist(final String artistName) {
        for (Artist artist : artists) {
            if (artist.getUsername().equals(artistName)) {
                return artist;
            }
        }
        return null;
    }

    /**
     * Gets host.
     *
     * @param hostName the host name
     * @return the host
     */

    public Host getHost(final String hostName) {
        for (Host host : hosts) {
            if (host.getUsername().equals(hostName)) {
                return host;
            }
        }
        return null;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * get top 5 albums
     *
     * @return List<String> names of top 5 albums
     */
    public List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(getAlbums());
        sortedAlbums.sort(Comparator.comparingInt(Album::getLikes).reversed());
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * Reset.
     */
    public void reset() {
        users = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }

    /**
     * Gets online normal users.
     *
     * @return the users
     */
    public List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isStatus()) {
                onlineUsers.add(user.getUsername());
            }
        }
        return onlineUsers;
    }

    /**
     * Add podcast from host to admin.
     *
     * @param podcast the podcast
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Add new normal user
     *
     * @param user the user
     */
    public void addNewUser(final User user) {
        users.add(user);
    }

    /**
     * Add new artist
     *
     * @param artist the artist
     */
    public void addNewArtist(final Artist artist) {
        artists.add(artist);
    }

    /**
     * Add new host
     *
     * @param host the host
     */
    public void addNewHost(final Host host) {
        hosts.add(host);
    }

    /**
     * show albuums of an artist
     *
     * @param artistUsername the artist
     */
    public ArrayList<Album> getAlbumsOfArtist(final String artistUsername) {
        for (Artist artist : artists) {
            if (artist.getUsername().equals(artistUsername)) {
                return artist.getAlbums();
            }
        }
        return null;
    }

    /**
     * get all users' names
     *
     * @return List<String>
     */
    public List<String> getAllUsers() {
        List<String> allUsers = new ArrayList<>();
        for (User user : users) {
            allUsers.add(user.getUsername());
        }
        for (Artist artist : artists) {
            allUsers.add(artist.getUsername());
        }
        for (Host host : hosts) {
            allUsers.add(host.getUsername());
        }
        return allUsers;
    }

    /**
     * delete user and its connections in the app
     *
     * @param username the user
     */
    public String deleteUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // delete its playlists
                for (Playlist playlist : user.getPlaylists()) {
                    for (User u : users) {
                        u.getFollowedPlaylists().remove(playlist);
                        playlist.decreaseFollowers();
                    }
                }
                for (Playlist playlist :user.getFollowedPlaylists()) {
                    playlist.decreaseFollowers();
                }
                users.remove(user);
                return username + " was successfully deleted.";
            }
        }
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                // check if someone is listening to a song of this artist
                for (User user : users) {
                    if (user.getPlayer().getSource() == null) {
                        continue;
                    }
                    Song song = (Song) user.getPlayer().getSource().getAudioFile();
                    if (song != null && song.getArtist().equals(username)) {
                        return username + " can't be deleted.";
                    }
                }

                // delete its songs and albums as well
                songs.removeIf(song -> song.getArtist().equals(username));
                for (User user : users) {
                    user.getLikedSongs().removeIf(song -> song.getArtist().equals(username));
                }
                artists.remove(artist);
                return username + " was successfully deleted.";
            }
        }
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                // check if someone is listening to a podcast of this host
                for (User user : users) {
                    if (user.getPlayer().getSource() == null) {
                        continue;
                    }
                    Podcast podcast = (Podcast) user.getPlayer().getSource().getAudioCollection();
                    if (podcast != null && podcast.getOwner().equals(username)) {
                        return username + " can't be deleted.";
                    }
                }
                // check that someone is looking at host page
                for (User user : users) {
                    if (user.getCurrentPageUsername() != null
                        && user.getCurrentPageUsername().equals(username)) {
                        return username + " can't be deleted.";
                    }
                }
                hosts.remove(host);
                return username + " was successfully deleted.";
            }
        }
        return "The username " + username + " doesn't exist.";
    }

}
