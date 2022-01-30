package com.jeremias.pinheiro.movie.api.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException() {
        super("Filme não encontrado!");
    }
}
