package app.community;

import lombok.Getter;

@Getter
public class Event {
    private final String name;
    private final String description;
    private final String date;

    public Event(final String name, final String description, final String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * method to print event as required by printArtistPage
     *
     * @return String
     */
    public String printEvent() {
        String event = "";
        event += name + " - " + date + ":\n\t" + description;
        return event;
    }
}
