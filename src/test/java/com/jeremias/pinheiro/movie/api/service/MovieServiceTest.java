package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import com.jeremias.pinheiro.movie.api.exception.MovieNotFoundException;
import com.jeremias.pinheiro.movie.api.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    MovieService  movieService;
    @Mock
    MovieRepository movieRepository;

    Movie movie1,movie2;
    MovieDTO dto1,dto2;
    List<Movie> movies;
    List<MovieDTO> movieDTOS;
    Page<MovieDTO> movieDTOPage;
    Page<Movie> moviePage;
    PageRequest pageRequest;
    @BeforeEach
    void setUp() {
        movie1 = new Movie(1L,"John wick 4",new Date(),"Chad Stahelski",
                " A história se concentra em John Wick," +
                " um assassino aposentado que busca vingança pelo roubo de seu carro " +
                "e a morte de seu cachorro, um presente de sua esposa recentemente falecida",
                5, MovieGenre.ACTION);
        movie2 = new Movie(2L,"Carga Explosiva",new Date()," Louis Leterrier","O ex-operador das Forças Especiais Frank Martin vive o que parece ser uma vida tranquila ao longo do Mediterrâneo francês. Na verdade ele é um transportador mercenário levando bens, humanos ou não, de um lugar para outro," +
                " sem fazer perguntas sobre que tipo de mercadoria foi contratado para transportar.",4.6,
                MovieGenre.ACTION);
        dto1 = new MovieDTO(1L,"John wick 4",new Date(),"Chad Stahelski",
                " A história se concentra em John Wick," +
                        " um assassino aposentado que busca vingança pelo roubo de seu carro " +
                        "e a morte de seu cachorro, um presente de sua esposa recentemente falecida",
                5, MovieGenre.ACTION);
        dto2 = new MovieDTO(2L,"Carga Explosiva",new Date()," Louis Leterrier","O ex-operador das Forças Especiais Frank Martin vive o que parece ser uma vida tranquila ao longo do Mediterrâneo francês. Na verdade ele é um transportador mercenário levando bens, humanos ou não, de um lugar para outro," +
                " sem fazer perguntas sobre que tipo de mercadoria foi contratado para transportar.",4.6,
                MovieGenre.ACTION);
        pageRequest = PageRequest.of(1, 10);
        movies = new ArrayList<>(Arrays.asList(movie1,movie2));
        movieDTOS = new ArrayList<>(Arrays.asList(dto1,dto2));
        moviePage = new PageImpl<>(movies,pageRequest,movies.size());
        movieDTOPage = new PageImpl<>(movieDTOS,pageRequest,movieDTOS.size());

    }

    @Test
    void findMovies() {
        when(movieRepository.findAll(pageRequest)).thenReturn(moviePage);
        assertEquals(movieDTOPage.toString(),movieService.findMovies(pageRequest).toString());
    }

    @Test
    void findMovieByMovieGenre() {

      when(movieRepository.findMovieByMovieGenre(MovieGenre.ACTION,pageRequest)).thenReturn(moviePage);
      assertEquals(movieDTOPage.toString(),movieService.findMovieByMovieGenre(MovieGenre.ACTION,pageRequest).toString());
    }

    @Test
    void findMovieByName() {
        when(movieRepository.findMovieByName(movie1.getName())).thenReturn(movie1);
       assertNotNull(movieService.findMovieByName(dto1.getName()));
    }

    @Test
    void findMovieById() {
        when(movieRepository.findById(movie1.getId())).thenReturn(ofNullable(movie1));
        assertNotNull(movieService.findMovieById(dto1.getId()));
        assertEquals(dto1.toString(),movieService.findMovieById(dto1.getId()).toString());
    }
    @Test
    void whenTheMovieWasNotFound(){
        MovieNotFoundException exception =  assertThrows(MovieNotFoundException.class,
                () -> movieService.findMovieById(anyLong()));
        assertEquals("Filme não encontrado!",exception.getMessage());
    }

    @Test
    void updateMovie() {
        when(movieRepository.findById(anyLong())).thenReturn(ofNullable(movie1));
        assertNotNull(dto1);
        assertEquals(dto1.toString(),movieService.updateMovie(anyLong(), dto1).toString());
    }

    @Test
    void save() {

    }

    @Test
    void deleteMovieById() {
        movieService.deleteMovieById(movie1.getId());
        verify(movieRepository,times(1)).deleteById(movie1.getId());
    }


}