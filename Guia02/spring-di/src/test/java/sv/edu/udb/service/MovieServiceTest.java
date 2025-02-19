package sv.edu.udb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.MovieRepository;
import sv.edu.udb.repository.domain.Movie;
import sv.edu.udb.service.implementation.MovieServiceImpl;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {
    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void shouldMovieServiceNotNull_When_SpringContextWorks(){
        assertNotNull(movieService);
    }

    @Test
    void showMovieRepositoryNotNull_When_DIWorks(){
        assertNotNull(movieService.getMovieRepository());
    }

    @Test
    void shouldGetAMovie_When_TheMovieIdExists(){
        final Long expectedMovieId = 1L;
        final String expectedMovieName = "Inception";
        final Integer expectedReleaseYear = 2010;

        final Movie actualMovie = movieRepository.findMovieById(expectedMovieId);

        assertEquals(actualMovie.getId(), expectedMovieId);
        assertEquals(actualMovie.getName(), expectedMovieName);
        assertEquals(actualMovie.getReleaseYear(), expectedReleaseYear);
    }

    @Test
    void shouldThrowNoSuchElementExcepcion_When_MovieIdDoesNotExists(){
        final Long fakeId = 4L;
        final String expectedErrorMessage = "Movie not found";
        final Exception exception = assertThrows(NoSuchElementException.class, () -> movieService.findMovieById(fakeId));
        assertEquals(exception.getMessage(), expectedErrorMessage);
    }

    @Test
    void shouldThrowIllegalArgumentException_When_NullIdProvided() {
        final Long nullId = null;
        assertThrows(IllegalArgumentException.class, () -> movieService.findMovieById(nullId));
    }

    @Test
    void shouldThrowIllegalArgumentException_When_NegativeIdProvided() {
        final Long negativeId = -4L;
        assertThrows(IllegalArgumentException.class, () -> movieService.findMovieById(negativeId));
    }


}
