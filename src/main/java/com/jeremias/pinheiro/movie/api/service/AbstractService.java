package com.jeremias.pinheiro.movie.api.service;

import java.util.List;

public interface AbstractService<M> {
    List<M> findMovies();
}
