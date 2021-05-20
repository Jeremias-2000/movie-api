package com.jeremias.pinheiro.movie.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;


@Getter
@AllArgsConstructor
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
    ROMANCE("romance"),HORROR("horror")
    ,THRILLER("thriller")
    ,DRAMA("drama");

    private final String description;

    public static MovieGenre of(String value){
        return Stream.of(MovieGenre.values())
                .filter(it -> it.getDescription().equals(value))
                .findFirst()
                .orElseThrow();
    }



}
