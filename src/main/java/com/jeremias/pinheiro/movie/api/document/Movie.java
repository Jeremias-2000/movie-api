package com.jeremias.pinheiro.movie.api.document;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import java.util.Date;


@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Movie  {

    @Id
    private String id;
    private String name;
    private Date date;
   // private String moviesDirector;
    private String description;
   // private double rating;
    private MovieGenre movieGenre;




}


