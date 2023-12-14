package app;

import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.PlaylistOutput;
import app.audio.Collections.Podcast;
import app.audio.Collections.PodcastOutput;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        ArrayList<String> results = new ArrayList<>();

        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            Filters filters = new Filters(commandInput.getFilters());
            String type = commandInput.getType();

            results = user.search(filters, type);
            message = "Search returned " + results.size() + " results";

        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        objectNode.put("results", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.select(commandInput.getItemNumber());
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.load();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.playPause();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.repeat();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            Integer seed = commandInput.getSeed();
            message = user.shuffle(seed);
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.forward();
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.backward();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.like();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.next();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.prev();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.createPlaylist(commandInput.getPlaylistName(),
                    commandInput.getTimestamp());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.addRemoveInPlaylist(commandInput.getPlaylistId());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.switchPlaylistVisibility(commandInput.getPlaylistId());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message;
        if (!user.isStatus()) {
            message = commandInput.getUsername() + " is offline.";
        } else {
            message = user.follow();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getInstance().getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getInstance().getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * get top 5 albums
     *
     * @param commandInput the command input
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> albums = Admin.getInstance().getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Switch connection status of a user
     *
     * @param commandInput the command input
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());

        String message;
        if (user != null) {
            message = user.changeStatus();
        } else if (host != null || artist != null) {
            message = commandInput.getUsername() + " is not a normal user.";
        } else {
            message = "The username " +  commandInput.getUsername()
                    + " doesn't exist.";
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Gets all normal online users
     *
     * @param commandInput the command input
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        List<String> onlineUsers = Admin.getInstance().getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }

    /**
     * Gets all users on the platform
     *
     * @param commandInput the command input
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        List<String> allUsers = Admin.getInstance().getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(allUsers));

        return objectNode;
    }

    /**
     * Adds a new podcast for a host
     *
     * @param commandInput the command input
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        String message;
        if (host == null && user == null && artist == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (host == null) {
            message = commandInput.getUsername() + " is not a host.";
        } else {
            // create a podcast from epsiodesInput
            ArrayList<Episode> episodes = new ArrayList<>();
            for (int i = 0; i < commandInput.getEpisodes().size(); i++) {
                episodes.add(new Episode(commandInput.getEpisodes().get(i).getName(),
                        commandInput.getEpisodes().get(i).getDuration(),
                        commandInput.getEpisodes().get(i).getDescription()));
            }

            Podcast podcast = new Podcast(commandInput.getName(), commandInput.getUsername(),
                    episodes);
            message = host.addPodcast(podcast);
        }


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Removes a podcast for a host
     *
     * @param commandInput the command input
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        String message;
        if (host == null && user == null && artist == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (host == null) {
            message = commandInput.getUsername() + " is not a host.";
        } else {
            message = host.removePodcast(commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add a new album for artist
     *
     * @param commandInput the command input
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        String message;
        if (artist == null && user == null && host == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (artist == null) {
            message = commandInput.getUsername() + " is not an artist.";
        } else {
            // create a playlist from songsInput
            ArrayList<Song> songs = new ArrayList<>();
            for (int i = 0; i < commandInput.getSongs().size(); i++) {
                songs.add(new Song(commandInput.getSongs().get(i).getName(),
                                commandInput.getSongs().get(i).getDuration(),
                                commandInput.getSongs().get(i).getAlbum(),
                                commandInput.getSongs().get(i).getTags(),
                                commandInput.getSongs().get(i).getLyrics(),
                                commandInput.getSongs().get(i).getGenre(),
                                commandInput.getSongs().get(i).getReleaseYear(),
                                commandInput.getSongs().get(i).getArtist()));
            }

            message = artist.addAlbum(commandInput.getName(), commandInput.getUsername(),
                    commandInput.getDescription(), songs, commandInput.getReleaseYear(),
                    commandInput.getTimestamp());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove album for artist
     *
     * @param commandInput the command input
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        String message;
        if (artist == null && user == null && host == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (artist == null) {
            message = commandInput.getUsername() + " is not an artist.";
        } else {
            message = artist.removeAlbum(commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Adds a new user
     *
     * @param commandInput the command input
     */
    public static ObjectNode addUser(final CommandInput  commandInput) {

        String message = null;
        // check if the username is already taken
        for (User user : Admin.getInstance().getUsers()) {
            if (user.getUsername().equals(commandInput.getUsername())) {
                message = "The username " + commandInput.getUsername() + " is already taken.";
                break;
            }
        }
        for (Host host : Admin.getInstance().getHosts()) {
            if (host.getUsername().equals(commandInput.getUsername())) {
                message = "The username " + commandInput.getUsername() + " is already taken.";
                break;
            }
        }
        for (Artist artist : Admin.getInstance().getArtists()) {
            if (artist.getUsername().equals(commandInput.getUsername())) {
                message = "The username " + commandInput.getUsername() + " is already taken.";
                break;
            }
        }

        if (message == null) {
            switch (commandInput.getType()) {
                case "user" -> {
                    User user = new User(commandInput.getUsername(), commandInput.getAge(),
                                    commandInput.getCity());
                    Admin.getInstance().addNewUser(user);
                    message = "The username " + commandInput.getUsername()
                            + " has been added successfully.";
                }
                case "artist" -> {
                    Artist artist = new Artist(commandInput.getUsername(),
                            commandInput.getUsername(), commandInput.getAge(),
                            commandInput.getCity());
                    Admin.getInstance().addNewArtist(artist);
                    message = "The username " + commandInput.getUsername()
                                + " has been added successfully.";
                }
                case "host" -> {
                    Host host = new Host(commandInput.getUsername(), commandInput.getUsername(),
                            commandInput.getAge(),
                            commandInput.getCity());
                    Admin.getInstance().addNewHost(host);
                    message = "The username " + commandInput.getUsername()
                            + " has been added successfully.";
                }
                default -> {
                    message = "Invalid type.";
                }
            }
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * deletes user and its connections in the app
     *
     * @param commandInput the command input
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        String message = Admin.getInstance().deleteUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * show albums of an artist
     *
     * @param commandInput the command input
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        ArrayList<Album> albums = Admin.getInstance().getAlbumsOfArtist(commandInput.getUsername());
        List<AlbumOutput> results = new ArrayList<>();
        for (Album album : albums) {
            results.add(new AlbumOutput(album));
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * prints the page that the user is currently on
     *
     * @param commandInput the command input
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        String message = user.printCurrentPage();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());

        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add event for an Artist
     *
     * @param commandInput the command input
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        String message;
        if (artist == null && user == null && host == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (artist == null) {
            message = commandInput.getUsername() + " is not an artist.";
        } else {
            message = artist.addEvent(commandInput.getName(), commandInput.getDescription(),
                    commandInput.getDate());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * remove event for an artist
     *
     * @param commandInput the command input
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        String message;
        if (artist == null && user == null && host == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (artist == null) {
            message = commandInput.getUsername() + " is not an artist.";
        } else {
            message = artist.removeEvent(commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add merch item for an Artist
     *
     * @param commandInput the command input
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        String message;

        if (artist == null && user == null && host == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (artist == null) {
            message = commandInput.getUsername() + " is not an artist.";
        } else {
            message = artist.addMerch(commandInput.getName(), commandInput.getDescription(),
                    commandInput.getPrice());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add announcement for a host
     *
     * @param commandInput the command input
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        String message;
        if (host == null && user == null && artist == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (host == null) {
            message = commandInput.getUsername() + " is not a host.";
        } else {
            message = host.addAnnouncement(commandInput.getName(), commandInput.getDescription());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Deletes the announcement with the given name
     *
     * @param commandInput the command input
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        String message;
        if (host == null && user == null && artist == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (host == null) {
            message = commandInput.getUsername() + " is not a host.";
        } else {
            message = host.removeAnnouncement(commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * show podcasts of a host
     *
     * @param commandInput the command input
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ArrayList<Podcast> podcasts = Objects.requireNonNull(
                        Admin.getInstance().getHost(commandInput.getUsername()))
                        .getPodcasts();
        List<PodcastOutput> results = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            results.add(new PodcastOutput(podcast));
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * changes the page for a normal user
     *
     * @param commandInput the command input
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message;
        if (!commandInput.getNextPage().equals("Home")
                && !commandInput.getNextPage().equals("LikedContent")) {
            message = commandInput.getUsername() + " is trying to access a non-existent page.";
        } else {
            message = user.changePage(commandInput.getNextPage());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }
}
