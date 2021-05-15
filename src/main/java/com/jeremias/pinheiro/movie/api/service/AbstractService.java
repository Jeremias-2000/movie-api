package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstractService<M> {
    List<M> findMovies();

    M findMovieByName(String name);
    List<M> findMovieByMovieGenre(MovieGenre movieGenre);
    M findMovieById(Long id);
    M updateMovie(Long id, MovieDTO movieDTO);
    M save(MovieDTO movieDTO);
    void deleteMovieById(Long id);
    Movie convertDTO (MovieDTO movieDTO);
    M convertEntity(Movie movie);
    List<M> convertDTO(List<Movie> movies);
    void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto);
    void checkIfTheMovieIsAlreadyRegistered(MovieDTO dto);
}
