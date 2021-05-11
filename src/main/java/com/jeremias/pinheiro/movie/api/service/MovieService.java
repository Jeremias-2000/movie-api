package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.exception.FilmExceptionAlreadyExists;
import com.jeremias.pinheiro.movie.api.exception.MovieNotFoundException;
import com.jeremias.pinheiro.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;


@Service
public class MovieService implements AbstractService<Movie>{

    @Autowired
    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Movie> findMovies() {
        return repository.findAll();
    }

    @Override
    public List<Movie> findMoviesByMovieGenre(String genre) {
        return repository.findMovieByMovieGenre(genre);
    }

    @Override
    public Movie findMovieByName(String name) {
        return repository.findMovieByName(name);
    }

    @Override
    public Movie findMovieById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie search = findMovieById(id);

        search.setName(movieDTO.getName());
        search.setDescription(movieDTO.getDescription());
        search.setRating(movieDTO.getRating());
        search.setMovieGenre(movieDTO.getMovieGenre());
        return search;
    }

    @Override
    public Movie save(MovieDTO movieDTO) {
        checkThatTheMovieIsNotNull(ofNullable(movieDTO));
        checkIfTheMovieIsAlreadyRegistered(movieDTO);
        Movie movie = convertDTO(movieDTO);
        return repository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        Movie deleted = findMovieById(id);
        repository.delete(deleted);
    }

    @Override
    public Movie convertDTO(MovieDTO movieDTO) {
        Movie movie = null;
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movie.setMovieGenre(movieDTO.getMovieGenre());
        return movie;
    }

    @Override
    public void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto) {
        if (dto.isEmpty())
            throw new NullPointerException();
    }

    @Override
    public void checkIfTheMovieIsAlreadyRegistered(MovieDTO dto) {
        Movie convert = convertDTO(dto);
        Optional<Movie> check;
        check = Optional.ofNullable(repository.findMovieByName(convert.getName()));
        if (check.isPresent()){
            throw new FilmExceptionAlreadyExists();
        }
    }
}
