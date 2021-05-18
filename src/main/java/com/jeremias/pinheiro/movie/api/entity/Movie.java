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
@Table
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Movie  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank

    private Date date;
    @NotBlank
    private String moviesDirector;
    @NotBlank
    private String description;
    @NotBlank
    private double rating;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;



}
