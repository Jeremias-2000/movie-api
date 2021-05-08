package com.jeremias.pinheiro.movie.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {
    @Id
    private long id;
    private String name;
    private String description;
    private double rating;

}
