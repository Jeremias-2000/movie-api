package com.jeremias.pinheiro.movie.api.repository;

import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    Page<Movie> findMovieByMovieGenre(MovieGenre genre,Pageable pageable);

    Movie findMovieByName(String name);
}
