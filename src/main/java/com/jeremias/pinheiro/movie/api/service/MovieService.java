package com.jeremias.pinheiro.movie.api.service;

import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService implements AbstractService<Movie>{

    @Autowired
    private MovieRepository repository;


    @Override
    public List<Movie> findMovies() {
        return repository.findAll();
    }
}
