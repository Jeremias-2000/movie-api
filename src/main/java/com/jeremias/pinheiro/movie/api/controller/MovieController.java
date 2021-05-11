package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
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

    public MovieController(MovieService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> findAllMovies() {
        return ResponseEntity.ok(service.findMovies());
    }


    @Override
    public ResponseEntity<?> saveMovie(MovieDTO movieDTO) {
        return new ResponseEntity<>(service.save(movieDTO), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> findMoviesByMovieGenre(String genre) {
        return ResponseEntity.ok(service.findMoviesByMovieGenre(genre));
    }

    @Override
    public ResponseEntity<?> findMovieByName(String name) {
        return ResponseEntity.ok(service.findMovieByName(name));
    }

    @Override
    public ResponseEntity<?> findMovieById(Long id) {
        return ResponseEntity.ok(service.findMovieById(id));
    }

    @Override
    public ResponseEntity<?> updateMovieById(Long id, MovieDTO dto) {
        return ResponseEntity.ok(service.updateMovie(id, dto));
    }

    @Override
    public ResponseEntity<?> deleteMovieById(Long id) {
        service.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }
}
