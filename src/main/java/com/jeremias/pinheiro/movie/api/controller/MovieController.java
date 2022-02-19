package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import com.jeremias.pinheiro.movie.api.service.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private AbstractService service;

    @GetMapping("/movies")
    public ResponseEntity<?> findAllMovies( ) {

        return ResponseEntity.ok(service.findMovies());

    }

    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<?> findMovieByMovieGenre(@PathVariable("genre") MovieGenre movieGenre) {
        return ResponseEntity.ok(service.findMovieByMovieGenre(movieGenre));
    }

    @GetMapping("/movie/name/{name}")
    public ResponseEntity<?> findMovieByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.findMovieByName(name));
    }

    @GetMapping("/movie/id/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable("id") String id) {

        return ResponseEntity.ok(service.findMovieById(id));

    }

    @PostMapping("/movie/register")
    public ResponseEntity<?> saveMovie(@RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(service.save(movieDTO),HttpStatus.CREATED);
    }


    @PutMapping("/movie/update/id/{id}")
    public ResponseEntity<?> updateMovieById(@PathVariable("id") String id,@RequestBody MovieDTO dto) {
        return ResponseEntity.ok(service.updateMovie(id, dto));
    }

    @DeleteMapping("/movie/delete/id/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable("id") String id) {
        service.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }
}
