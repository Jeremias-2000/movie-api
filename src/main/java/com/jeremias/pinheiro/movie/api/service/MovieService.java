package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.document.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import com.jeremias.pinheiro.movie.api.exception.FilmExceptionAlreadyExists;
import com.jeremias.pinheiro.movie.api.exception.MovieNotFoundException;
import com.jeremias.pinheiro.movie.api.mapper.MovieMapper;
import com.jeremias.pinheiro.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieService implements AbstractService{

    @Autowired
    private MovieRepository repository;






    public List<MovieDTO> findMovies() {
        return  repository.findAll()
                .stream().map(MovieMapper::convertToDTO)
                .collect(Collectors.toList());

    }



    public List<MovieDTO> findMovieByMovieGenre(MovieGenre movieGenre) {
        return repository.findByMovieGenre(movieGenre)
                .stream().map(MovieMapper::convertToDTO)
                .collect(Collectors.toList());

    }


    @Override
    public MovieDTO findMovieByName(String name) {
        return MovieMapper.convertToDTO(repository.findByNameIgnoreCase(name));

    }

    @Override
    public MovieDTO findMovieById(String id) {
        return MovieMapper.convertToDTO( repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException()));

    }

    @Override
    public MovieDTO updateMovie(String id, MovieDTO movieDTO) {
        checkThatTheMovieIsNotNull(Optional.ofNullable(movieDTO));
        Movie movie = repository.findById(id)
                .orElseThrow(()-> new MovieNotFoundException());
        movie.setName(movieDTO.getName());
        movie.setDate(movieDTO.getDate());
        movie.setMoviesDirector(movieDTO.getMoviesDirector());
        movie.setRating(movieDTO.getRating());
        movie.setMovieGenre(movieDTO.getMovieGenre());
        repository.save(movie);
        return movieDTO;
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
        check = Optional.ofNullable(repository.findByNameIgnoreCase(convert.getName()));
        if (check.isPresent()){
            throw new FilmExceptionAlreadyExists();
        }
    }
}
