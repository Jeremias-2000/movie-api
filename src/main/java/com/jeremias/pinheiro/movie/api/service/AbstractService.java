package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;

import java.util.List;

public interface AbstractService<M> {
    List<M> findMovies();
    List<M> findMoviesByMovieGenre(String genre);
    List<M> findMovieByName(String name);
    M findMovieById(Long id);
    M updateMovie(Long id, MovieDTO movieDTO);
    M save(MovieDTO movieDTO);
    void deleteMovieById(Long id);
    M convertDTO (MovieDTO movieDTO);
}
