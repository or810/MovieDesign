import java.util.ArrayList;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        MovieDetails movie = new MovieDetailsConfigurations()
                        .name("Intersteller")
                        .director("George")
                        .actors(new String[]{"Peter Parker", "Johnny depp"})
                        .genre("Comedy")
                        .releaseYear(2023)
                        .medias(new MediaMask(MovieMedia.DVD, MovieMedia.BLU_RAY, MovieMedia.CASETTE))
                        .assemble();
        System.out.println(movie);
        
        MovieStore store = MovieStore.getInstance();
        store.addMovie(movie);

        Client client = store.addClient("Shrek");
        client.rentMovie("Intersteller", MovieMedia.DVD);
        store.passTime(2);
        client.returnMovie("Intersteller");

        Predicate<Client> predicate = (someClient -> {
            for(Tuple<Integer, MovieMedia> tuple : someClient.getRentedMovies().values()) {
                if(MovieStore.getDay() - tuple.x < 5)
                    return true;
            }
            return false;
        });
        ArrayList<Client> clients = store.filterClients(predicate);
        System.out.println(clients.size());
    }
}
