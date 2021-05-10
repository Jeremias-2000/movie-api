package com.jeremias.pinheiro.movie.api.entity;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private double rating;
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;


}
