package com.jeremias.pinheiro.movie.api.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum MovieGenre {
    ACTION("action"),
    ANIMATION("animation"),
    ADVENTURE("adventure"),
    COMEDY("comedy"),
    DOCUMENTARY("documentary"),
    FANTASY("fantasy"),
    WESTERN("western"),
    SCIENCEFICTION("sciencefiction"),
    WAR("war"),
    MUSICALS("musicals"),
    ROMANCE("romance"),
    HORROR("horror"),
    THRILLER("thriller"),
    DRAMA("drama");

    private final String description;





}
