package com.jeremias.pinheiro.movie.api.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
    }
}