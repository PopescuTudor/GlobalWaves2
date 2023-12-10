package app.community;

import lombok.Getter;

@Getter
public class Announcement {
    private final String name;
    private final String description;

    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * method to print announcement as required by printHostPage
     *
     * @return String
     */
    public String printAnnouncement() {
        String announcement = "";
        announcement += name + ":\n\t" + description + "\n";
        return announcement;
    }
}
