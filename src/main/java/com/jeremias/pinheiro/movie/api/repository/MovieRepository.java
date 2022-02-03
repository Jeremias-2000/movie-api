package com.jeremias.pinheiro.movie.api.repository;

import com.jeremias.pinheiro.movie.api.document.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {

    List<Movie> findByMovieGenre(MovieGenre genre);

    Movie findByNameIgnoreCase(String name);
}
