package com.jeremias.pinheiro.movie.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO extends RepresentationModel<MovieDTO> {


    private long id;

    @NotBlank
    private String name;

    @JsonSerialize(@JsonFormat(pattern = "dd-MM-yyyy"))
    @JsonDeserialize( @JsonFormat(pattern = "dd-MM-yyyy"))
    private LocalDate date;
    @NotBlank
    private String moviesDirector;
    @NotBlank
    private String description;
    @NotBlank
    private double rating;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;


    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.date = movie.getDate();
        this.moviesDirector = movie.getMoviesDirector();
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.movieGenre = movie.getMovieGenre();

    }
}
