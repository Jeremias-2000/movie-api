package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface AbstractService<M> {
    List<M> findMovies();
    List<M> findMoviesByMovieGenre(String genre);
    M findMovieByName(String name);
    M findMovieById(Long id);
    M updateMovie(Long id, MovieDTO movieDTO);
    M save(MovieDTO movieDTO);
    void deleteMovieById(Long id);
    M convertDTO (MovieDTO movieDTO);
    void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto);
    void checkIfTheMovieIsAlreadyRegistered(MovieDTO dto);
}
