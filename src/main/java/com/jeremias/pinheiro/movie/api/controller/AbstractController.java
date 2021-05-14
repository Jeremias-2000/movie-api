package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public interface AbstractController {

    @GetMapping("/search/all")
    ResponseEntity<?> findAllMovies(Pageable pageable);



    @GetMapping("/search/type/name/{name}")
    ResponseEntity<?> findMovieByName(@PathVariable("name") String name);

    @GetMapping("/search/type/id/{id}")
    ResponseEntity<?> findMovieById(@PathVariable("id") Long id,Pageable pageable);

    @PutMapping("/update/id/{id}")
    ResponseEntity<?> updateMovieById(@PathVariable("id") Long id,@RequestBody MovieDTO dto);

    @PostMapping("/create")
    ResponseEntity<?> saveMovie(@RequestBody MovieDTO movieDTO);

    @DeleteMapping("/delete/id/{id}")
    ResponseEntity<?> deleteMovieById(@PathVariable("id") Long id);
}
