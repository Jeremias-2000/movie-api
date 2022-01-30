package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import com.jeremias.pinheiro.movie.api.service.AbstractService;
import com.jeremias.pinheiro.movie.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private AbstractService service;


    public ResponseEntity<?> findAllMovies(Pageable pageable ) {



        Page<MovieDTO> movies = service.findMovies(pageable);
        if (movies.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            for (MovieDTO dto: movies) {
                long id = dto.getId();
                MovieGenre genre = dto.getMovieGenre();
                dto.add(linkTo(methodOn(MovieController.class).findMovieById(id)).withSelfRel());
                dto.add(linkTo(methodOn(MovieController.class).findMovieByMovieGenre(genre,pageable)).withSelfRel());
            }
        }
        return ResponseEntity.ok(movies);

    }


    public ResponseEntity<?> findMovieByMovieGenre(MovieGenre movieGenre,Pageable pageable) {
        return ResponseEntity.ok(service.findMovieByMovieGenre(movieGenre,pageable));
    }


    public ResponseEntity<?> saveMovie(MovieDTO movieDTO) {
       return new ResponseEntity<>(service.save(movieDTO),HttpStatus.CREATED);
    }



    public ResponseEntity<?> findMovieByName(String name) {
        return ResponseEntity.ok(service.findMovieByName(name));
    }


    public ResponseEntity<?> findMovieById(Long id) {

        return ResponseEntity.ok(service.findMovieById(id));

    }

    public ResponseEntity<?> updateMovieById(Long id, MovieDTO dto) {
        return ResponseEntity.ok(service.updateMovie(id, dto));
    }

    public ResponseEntity<?> deleteMovieById(Long id) {
        service.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }
}
