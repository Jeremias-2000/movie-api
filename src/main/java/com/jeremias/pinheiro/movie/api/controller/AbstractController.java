package com.jeremias.pinheiro.movie.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface AbstractController {

    @GetMapping("/all")
    ResponseEntity<?> findAllMovies();
}
