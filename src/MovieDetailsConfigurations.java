import java.util.ArrayList;
import java.util.List;

public class MovieDetailsConfigurations {
    private String name;
    private String director;
    private String genre; 
    
    private List<String> actors;

    private int releaseYear; // o by default

    private MediaMask medias;

    private static boolean hasABlank(String... strings) {
        for(int i = 0;i<strings.length;i++)
            if(strings[i] == null || strings[i].equals(null))
                return true;
        return false;
    }

    private static void validate(String str) {
        if(str == null || str.equals(null))
            throw new InvalidMovieRuntimeException("\nInvalid String.");
    }
    
    public MovieDetails assemble() {
        if(hasABlank(name, director, genre) || actors.isEmpty() || releaseYear == 0)
            throw new InvalidMovieRuntimeException("\nCannot assemble a movie that is not fully configured!");
        return new MovieDetails(name, director, genre, actors, releaseYear, medias);
    }

    public MovieDetailsConfigurations medias(MediaMask medias) {
        this.medias = medias;
        return this;
    }

    public MovieDetailsConfigurations name(String name) {
        validate(name);
        this.name = name;
        return this;
    }

    public MovieDetailsConfigurations director(String director) {
        validate(director);
        this.director = director;
        return this;
    }

    public MovieDetailsConfigurations genre(String genre) {
        validate(genre);
        this.genre = genre;
        return this;
    }

    public MovieDetailsConfigurations actors(List<String> actors) {
        // may need some validation
        this.actors = actors;
        return this;
    }

    public MovieDetailsConfigurations actors(String[] actors) {
        if(hasABlank(actors))
            throw new InvalidMovieRuntimeException("Invalid String.");
        this.actors = new ArrayList<>(actors.length);
        for(String actor : actors) {
            this.actors.add(actor);
        }
        return this;
    }

    public MovieDetailsConfigurations releaseYear(int releaseYear) {
        // may use an overloaded validate(int) imp. 
        this.releaseYear = releaseYear;
        return this;
    }
}