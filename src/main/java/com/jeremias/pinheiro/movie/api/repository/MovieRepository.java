package com.jeremias.pinheiro.movie.api.repository;

import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {

    List<Movie> findMovieByMovieGenre(MovieGenre genre);

    Movie findMovieByName(String name);
}
