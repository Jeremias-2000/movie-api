package com.jeremias.pinheiro.movie.api.repository;

import com.jeremias.pinheiro.movie.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findMovieByMovieGenre(String genre);

    List<Movie> findMovieByName(String name);
}
