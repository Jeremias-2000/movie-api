package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;
import com.jeremias.pinheiro.movie.api.entity.Movie;
import com.jeremias.pinheiro.movie.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class MovieController implements AbstractController{

    @Autowired
    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> findAllMovies(Pageable pageable) {
        Page<MovieDTO> movies = service.findMovies(pageable);
        if (movies.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            for (MovieDTO dto: movies) {
                long id = dto.getId();
                dto.add(linkTo(methodOn(MovieController.class).findMovieById(id,pageable)).withSelfRel());
            }
        }
        return ResponseEntity.ok(movies);
    }


    @Override
    public ResponseEntity<?> saveMovie(MovieDTO movieDTO) {
       return new ResponseEntity<>(service.save(movieDTO),HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> findMovieByName(String name) {
        return ResponseEntity.ok(service.findMovieByName(name));
    }

    @Override
    public ResponseEntity<?> findMovieById(Long id,Pageable pageable) {
        /*
        Optional<MovieDTO> search = ofNullable(service.findMovieById(id));
        if (search.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            search.get().add(linkTo(methodOn(MovieController.class).findAllMovies(pageable)).withRel("Listar filmes :"));
            return ResponseEntity.ok(search);
        }

         */
        return ResponseEntity.ok(service.findMovieById(id));
    }

    @Override
    public ResponseEntity<?> updateMovieById(Long id, MovieDTO dto) {
        return ResponseEntity.ok(service.updateMovie(id, dto));
    }

    @Override
    public ResponseEntity<?> deleteMovieById(Long id) {
        service.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }
}
