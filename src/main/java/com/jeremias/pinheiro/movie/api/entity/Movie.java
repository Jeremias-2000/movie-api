package com.jeremias.pinheiro.movie.api.entity;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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


}
