package app.audio.Collections;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Class to output essential information about an album
 */
@Getter @Setter
public class AlbumOutput {

    private final String name;
    private final ArrayList<String> songs;

    public AlbumOutput(final Album album) {
        this.name = album.getName();
        this.songs = new ArrayList<>();
        for (int i = 0; i < album.getSongs().size(); i++) {
            songs.add(album.getSongs().get(i).getName());
        }
    }
}
