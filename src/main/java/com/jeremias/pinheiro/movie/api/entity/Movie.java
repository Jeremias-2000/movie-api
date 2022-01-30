package com.jeremias.pinheiro.movie.api.entity;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Date date;

    private String moviesDirector;

    private String description;

    private double rating;

    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;


}


