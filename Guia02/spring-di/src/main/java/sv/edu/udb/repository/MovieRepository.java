package sv.edu.udb.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import sv.edu.udb.repository.domain.Movie;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MovieRepository {
    private List<Movie> listOfMovies;

    @PostConstruct
    private void init(){
        final Movie movie_1 = Movie
            .builder()
                .id(1L)
                .name("Inception")
                .type("Sciencie Fiction")
                .releaseYear(2010)
            .build();
        final Movie movie_2 = Movie
            .builder()
                .id(2L)
                .name("Butterfly effect")
                .type("Science Fiction Thriller")
                .releaseYear(2004)
            .build();
        final Movie movie_3 = Movie
            .builder()
                .id(3L)
                .name("Interstellar")
                .type("Science Fiction")
                .releaseYear(2014)
            .build();
        this.listOfMovies = List.of(movie_1, movie_2, movie_3);

    }

    public Movie findMovieById(final Long id){

        if(id==null || id<0){
            throw new IllegalArgumentException("Id cannot be null");
        }
        return this.listOfMovies
                .stream()
                .filter(movie -> id.equals(movie.getId()))
                .findFirst()
                .orElseThrow
                        (() -> new NoSuchElementException("Movie not found"));
    }
}
