package com.jeremias.pinheiro.movie.api.entity;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
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
    private String description;
    @NotBlank
    private double rating;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;


}
