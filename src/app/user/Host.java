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
