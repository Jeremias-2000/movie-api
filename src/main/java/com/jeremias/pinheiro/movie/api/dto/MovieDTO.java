package com.jeremias.pinheiro.movie.api.dto;


import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

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
