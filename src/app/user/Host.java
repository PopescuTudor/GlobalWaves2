package app.user;

import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.LibraryEntry;
import app.community.Announcement;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Host extends LibraryEntry {
    private final String username;
    private final int age;
    private final String city;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;
    public Host(final String name, final String username, final int age, final String city) {
        super(name);
        this.username = username;
        this.age = age;
        this.city = city;
        this.podcasts = new ArrayList<>();
        this.announcements = new ArrayList<>();
    }

    public String addPodcast(final Podcast podcast) {

        // check for a podcast with the same name
        for (final Podcast p : this.podcasts) {
            if (p.getName().equals(podcast.getName())) {
                return username + " has another podcast with the same name.";
            }
        }

        // add the podcast
        this.podcasts.add(podcast);
        Admin.getInstance().addPodcast(podcast);
        return username + " has added new podcast successfully.";
    }

    /**
     * Add announcement.
     *
     * @param announcement the announcement
     */
    public String addAnnouncement(final String name, final String description) {
        // check for an announcement with the same name
        for (final Announcement a : this.announcements) {
            if (a.getName().equals(name)) {
                return username + " has already added an announcement with this name.";
            }
        }

        // add the announcement
        this.announcements.add(new Announcement(name, description));

        return username + " has successfully added new announcement.";
    }

    /**
     * Removes announcement.
     *
     * @param name the name of the announcement
     */
    public String removeAnnouncement(final String name) {
        // check for an announcement with the same name
        for (final Announcement a : this.announcements) {
            if (a.getName().equals(name)) {
                this.announcements.remove(a);
                return username + " has successfully deleted the announcement.";
            }
        }

        return username + " has no announcement with the given name.";
    }

    /**
     * print host's page
     *
     * @return the string
     */
    public String printHostPage() {
        ArrayList<String> podcasts = new ArrayList<>();
        ArrayList<String> announcements = new ArrayList<>();

        for (Podcast podcast : this.podcasts) {
            podcasts.add(podcast.printPodcast());
        }
        for (Announcement announcement : this.announcements) {
            announcements.add(announcement.printAnnouncement());
        }

        return String.format("Podcasts:\n\t%s\n\nAnnouncements:\n\t%s", podcasts, announcements);
    }
}
