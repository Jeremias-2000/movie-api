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
import java.util.stream.Collectors;

import static java.util.Optional.*;


@Service
public class MovieService implements AbstractService<MovieDTO>{

    @Autowired
    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<MovieDTO> findMovies() {
        List<Movie> movies = repository.findAll();
        return convertDTO(movies);
    }

    @Override
    public List<MovieDTO> convertDTO(List<Movie> movies) {
        return movies.stream().map(MovieDTO :: new).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findMoviesByMovieGenre(String genre) {
        List<Movie> movies =  repository.findMovieByMovieGenre(genre);
        return convertDTO(movies);
    }

    @Override
    public MovieDTO findMovieByName(String name) {
        MovieDTO dto = convertEntity(repository.findMovieByName(name));
        return dto;
    }

    @Override
    public MovieDTO findMovieById(Long id) {
        MovieDTO dto = convertEntity( repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id)));
        return dto;
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Movie search = convertDTO(findMovieById(id));

        search.setName(movieDTO.getName());
        search.setDescription(movieDTO.getDescription());
        search.setRating(movieDTO.getRating());
        search.setMovieGenre(movieDTO.getMovieGenre());
        return movieDTO;
    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {

        Movie movie = convertDTO(movieDTO);
        repository.save(movie);
        return movieDTO;
    }

    @Override
    public void deleteMovieById(Long id) {
        Movie deleted = convertDTO(findMovieById(id));
        repository.delete(deleted);
    }

    @Override
    public Movie convertDTO(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movie.getId());
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movie.setMovieGenre(movieDTO.getMovieGenre());
        return movie;
    }

    @Override
    public MovieDTO convertEntity(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setDescription(movie.getDescription());
        dto.setRating(movie.getRating());
        dto.setMovieGenre(movie.getMovieGenre());
        return dto;
    }

    @Override
    public void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto) {
        if (!dto.isPresent())
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
