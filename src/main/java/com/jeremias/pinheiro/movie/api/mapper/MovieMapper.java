package com.jeremias.pinheiro.movie.api.mapper;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.document.Movie;

public class MovieMapper {
    public static Movie convertToEntity(MovieDTO movieDTO) {
        return Movie.builder()
                .id(movieDTO.getId())
                .name(movieDTO.getName())
                .date(movieDTO.getDate())

                .description(movieDTO.getDescription())

                .movieGenre(movieDTO.getMovieGenre())
                .build();
    }





    public static MovieDTO convertToDTO(Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .date(movie.getDate())

                .description(movie.getDescription())

                .movieGenre(movie.getMovieGenre())
                .build();
    }
}
