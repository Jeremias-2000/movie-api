package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstractService {
    Page<MovieDTO> findMovies(Pageable pageable);

    MovieDTO findMovieByName(String name);
    Page<MovieDTO> findMovieByMovieGenre(MovieGenre movieGenre,Pageable pageable);
    MovieDTO findMovieById(Long id);
    MovieDTO updateMovie(Long id, MovieDTO movieDTO);
    MovieDTO save(MovieDTO movieDTO);
    void deleteMovieById(Long id);
    Movie convertDTO (MovieDTO movieDTO);
    MovieDTO convertEntity(Movie movie);
    Page<MovieDTO> convertDTO(Page<Movie> movies);
    void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto);
    void checkIfTheMovieIsAlreadyRegistered(MovieDTO dto);
}
