package com.jeremias.pinheiro.movie.api.dto;


import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO extends RepresentationModel<MovieDTO> {



    private Long id;
    @NotNull
    private String name;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @NotNull
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
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.movieGenre = movie.getMovieGenre();

    }
}
