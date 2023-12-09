package fileio.input;

import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;

@Getter @Setter
public final class AlbumInput {

    private String name;
    private Integer releaseYear;
    private  String owner;
    private String description;
    private ArrayList<SongInput> songs;

    public AlbumInput() {
    }

    @Override
    public String toString() {
        return "AlbumInput{"
                + "name='" + name + '\''
                + ", releaseYear=" + releaseYear
                + ", description='" + description + '\''
                + ", owner='" + owner + '\''
                + ", songs=" + songs
                + '}';
    }
}
