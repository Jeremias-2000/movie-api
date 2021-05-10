package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController implements AbstractController{

    @Autowired
    private MovieService service;


    @Override
    public ResponseEntity<?> findAllMovies() {
        return ResponseEntity.ok(service.findMovies());
    }


    @Override
    public ResponseEntity<?> saveMovie(Movie movie) {
        return new ResponseEntity<>(service.save(movie), HttpStatus.CREATED);
    }
}
