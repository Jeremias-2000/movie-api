package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstractService<M> {
    Page<M> findMovies(Pageable pageable);

    M findMovieByName(String name);
    M findMovieById(Long id);
    M updateMovie(Long id, MovieDTO movieDTO);
    M save(MovieDTO movieDTO);
    void deleteMovieById(Long id);
    Movie convertDTO (MovieDTO movieDTO);
    M convertEntity(Movie movie);
    Page<M> convertDTO(Page<Movie> movies);
    void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto);
    void checkIfTheMovieIsAlreadyRegistered(MovieDTO dto);
}
