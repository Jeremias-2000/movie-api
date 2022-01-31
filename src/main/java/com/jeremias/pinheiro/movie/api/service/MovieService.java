package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import com.jeremias.pinheiro.movie.api.exception.FilmExceptionAlreadyExists;
import com.jeremias.pinheiro.movie.api.exception.MovieNotFoundException;
import com.jeremias.pinheiro.movie.api.mapper.MovieMapper;
import com.jeremias.pinheiro.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.*;


@Service
public class MovieService implements AbstractService{

    @Autowired
    private MovieRepository repository;



    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


    @Override
    public Page<MovieDTO> findMovies(Pageable pageable) {
        return repository.findAll(pageable).map(MovieMapper::convertToDTO);

    }



    @Override
    public Page<MovieDTO> findMovieByMovieGenre(MovieGenre movieGenre, Pageable pageable) {
        return repository.findMovieByMovieGenre(movieGenre,pageable).map(MovieMapper::convertToDTO);

    }


    @Override
    public MovieDTO findMovieByName(String name) {
        return MovieMapper.convertToDTO(repository.findMovieByName(name));

    }

    @Override
    public MovieDTO findMovieById(String id) {
        return MovieMapper.convertToDTO( repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException()));

    }

    @Override
    public MovieDTO updateMovie(String id, MovieDTO movieDTO) {
        checkThatTheMovieIsNotNull(Optional.ofNullable(movieDTO));
        return MovieMapper.convertToDTO(repository.findById(id)
                .map(movie -> MovieMapper.convertToEntity(movieDTO))
                .orElseThrow(() -> new MovieNotFoundException()));

    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        checkThatTheMovieIsNotNull(Optional.ofNullable(movieDTO));
        Movie movie = MovieMapper.convertToEntity(movieDTO);
        repository.save(movie);

        return movieDTO;
    }

    @Override
    public void deleteMovieById(String id) {
       repository.deleteById(id);
    }



    @Override
    public void checkThatTheMovieIsNotNull(Optional<MovieDTO> dto) {
        if (!dto.isPresent())
            throw new NullPointerException();
    }

    @Override
    public void checkIfTheMovieIsAlreadyRegistered(MovieDTO dto) {
        Movie convert = MovieMapper.convertToEntity(dto);
        Optional<Movie> check;
        check = Optional.ofNullable(repository.findMovieByName(convert.getName()));
        if (check.isPresent()){
            throw new FilmExceptionAlreadyExists();
        }
    }
}
