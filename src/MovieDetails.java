import java.util.List;

public class MovieDetails {

    // any sensitive info?

    private String name;
    private String director;
    private String genre; 
    
    private List<String> actors;

    private int releaseYear;

    private MediaMask medias;

    
    MovieDetails(String name, String director, String genre, List<String> actors, int releaseYear, MediaMask medias) {
        this.name = name;
        this.director = director;
        this.genre = genre;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.medias = medias;
    }

    public String toString() {
        return 
        "[" + 
            "\n\tName: " + name + 
            "\n\tdirector: " + director + 
            "\n\tGenre: " + genre + 
            "\n\tRelease Year: " + releaseYear + 
            "\n\tActors: " + actors +
            "\n\tMedias: " + medias + 
        "\n]";
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getActors() {
        return actors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public MediaMask getMedias() {
        return medias;
    }
    
}
