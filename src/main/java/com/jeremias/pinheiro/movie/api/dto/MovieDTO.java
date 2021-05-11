package com.jeremias.pinheiro.movie.api.dto;


import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String name;
    private String description;
    private double rating;
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;


}
