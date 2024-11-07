import java.util.HashMap;

public class Client implements Comparable<Client>{
    @SuppressWarnings("unused")
    private String name;
    private HashMap<MovieDetails, Tuple<Integer, MovieMedia>> rentedMovies;
    private int totalPrice;
    private int lastUpdated;

    public Client(String name) {
        this.name = name;
        rentedMovies = new HashMap<>();
        totalPrice = 0;
        lastUpdated = 0;
    }

    public boolean rentMovie(String movieName, MovieMedia media) {
        // may be unable to rent
        MovieDetails movie = MovieStore.getInstance().rentMovie(this, movieName, media);
        if(movie != null) {
            rentedMovies.put(movie, new Tuple<Integer,MovieMedia>(MovieStore.getDay(), media));
            return true;
        }
        // TODO: Error handling
        return false;
    }

    public int returnMovie(String movieName) {
        MovieDetails movie = MovieStore.getInstance().returnMovie(this, movieName);
        if(movie != null) {
            calculatePrice(movie);
        }
        // TODO: Error handling
        return -1;
    }

    public int calculatePrice(MovieDetails movie) {
        var tuple = rentedMovies.get(movie);
        int delta = MovieStore.getDay() - tuple.x;
        int moviePrice =  delta * tuple.y.getCost();
        totalPrice+= moviePrice;
        return moviePrice;
    }

    public int recalculateTotalPrice() {
        int sum = 0;
        for(MovieDetails movie : rentedMovies.keySet()) {
            sum += calculatePrice(movie);
        }
        lastUpdated = MovieStore.getDay();
        return sum;
    }

    public void updateIfNeeded() {
        if(lastUpdated < MovieStore.getDay())
            recalculateTotalPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public int compareTo(Client o) {
        Client c = (Client)(o);
        c.updateIfNeeded();
        updateIfNeeded();
        if(totalPrice > c.totalPrice)
            return 1;
        else if(totalPrice < c.totalPrice)
            return -1;
        return 0;
    }

    public HashMap<MovieDetails, Tuple<Integer, MovieMedia>> getRentedMovies() {
        return rentedMovies;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public String getName() {
        return name;
    }

}
