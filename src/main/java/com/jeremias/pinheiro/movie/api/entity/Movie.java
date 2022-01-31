package com.jeremias.pinheiro.movie.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;


import java.util.Date;

import static com.fasterxml.jackson.databind.deser.std.DateDeserializers.*;



@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Movie  {

    @Id
    private String id;

    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date date;

    private String moviesDirector;

    private String description;

    private double rating;


    private MovieGenre movieGenre;


}


