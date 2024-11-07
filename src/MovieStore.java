import java.util.ArrayList;
import java.util.function.Predicate;

public class MovieStore {
    private ArrayList<MovieDetails> movies = new ArrayList<>();

    private static MovieStore instance = new MovieStore();

    private static int day = 0;

    private ArrayList<Client> clients = new ArrayList<>();

    public static int getDay() {
        return day;
    }

    public void passTime(int delta) {
        day += delta;
    }

    public void updateClients() {
        for(Client client : clients)
            client.updateIfNeeded();
    }

    public void maintainSort() {
        updateClients();
        movies.sort(null);
    }

    public Client addClient(String name) {
        Client client = new Client(name);
        clients.add(client);
        return client;
    }

    public MovieDetails rentMovie(Client client, String movieName, MovieMedia media) {
        if(!clients.contains(client))
            clients.add(client);
        // TODO: may check if movie is available for grab...
        MovieDetails movie = getMovie(movieName); 
        if(movie.getMedias().isSet(media))
            return movie;
        return null;
    }

    private MovieDetails getMovie(String name) {
        for(MovieDetails movie : movies)
            if(movie.getName().equals(name))
                return movie;
        return null;
    }

    public MovieDetails returnMovie(Client client, String movieName) {
        return getMovie(movieName);
    }

    public boolean addMovie(MovieDetails movie) {
        return movies.add(movie);
    }

    public boolean removeMovie(MovieDetails movie) {
        return movies.remove(movie);
    }

    public boolean removeMovie(String name) {
        return movies.remove(getMovie(name));
    }

    public static MovieStore getInstance() {
        return instance;
    }

    public ArrayList<MovieDetails> filterMovieDetails(Predicate<MovieDetails> predicate) {
        ArrayList<MovieDetails> temp = (ArrayList<MovieDetails>) movies.clone();
        temp.removeIf(predicate);
        return temp;
    }

    public ArrayList<Client> filterClients(Predicate<Client> predicate) {
        ArrayList<Client> temp = (ArrayList<Client>) clients.clone();
        temp.removeIf(predicate);
        return temp;
    }
}
