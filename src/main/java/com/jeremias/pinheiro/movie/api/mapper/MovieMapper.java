package com.jeremias.pinheiro.movie.api.mapper;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.document.Movie;

public class MovieMapper {
    public static Movie convertToEntity(MovieDTO movieDTO) {
        return Movie.builder()
                .id(movieDTO.getId())
                .name(movieDTO.getName())
                .date(movieDTO.getDate())
                //.moviesDirector(movieDTO.getMoviesDirector())
                .description(movieDTO.getDescription())
                //.rating(movieDTO.getRating())
                .movieGenre(movieDTO.getMovieGenre())
                .build();
    }





    public static MovieDTO convertToDTO(Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .date(movie.getDate())
                //.moviesDirector(movie.getMoviesDirector())
                .description(movie.getDescription())
                //.rating(movie.getRating())
                .movieGenre(movie.getMovieGenre())
                .build();
    }
}
