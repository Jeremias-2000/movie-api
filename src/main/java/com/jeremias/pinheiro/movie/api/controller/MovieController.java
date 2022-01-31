package com.jeremias.pinheiro.movie.api.controller;

import com.jeremias.pinheiro.movie.api.dto.MovieDTO;

import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import com.jeremias.pinheiro.movie.api.service.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private AbstractService service;

    @GetMapping("/movies")
    public ResponseEntity<?> findAllMovies(Pageable pageable ) {



        Page<MovieDTO> movies = service.findMovies(pageable);
        if (movies.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else{
            for (MovieDTO dto: movies) {
                String id = dto.getId();
                MovieGenre genre = dto.getMovieGenre();
                dto.add(linkTo(methodOn(MovieController.class).findMovieById(id)).withSelfRel());
                dto.add(linkTo(methodOn(MovieController.class).findMovieByMovieGenre(genre,pageable)).withSelfRel());
            }
        }
        return ResponseEntity.ok(movies);

    }

    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<?> findMovieByMovieGenre(@PathVariable("genre") MovieGenre movieGenre, Pageable pageable) {
        return ResponseEntity.ok(service.findMovieByMovieGenre(movieGenre,pageable));
    }

    @GetMapping("/movie/name/{name}")
    public ResponseEntity<?> findMovieByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.findMovieByName(name));
    }

    @GetMapping("/movie/id/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable("id") String id) {

        return ResponseEntity.ok(service.findMovieById(id));

    }

    @PostMapping("/movie/register")
    public ResponseEntity<?> saveMovie(MovieDTO movieDTO) {
        return new ResponseEntity<>(service.save(movieDTO),HttpStatus.CREATED);
    }

    @PutMapping("/movie/update/id/{id}")
    public ResponseEntity<?> updateMovieById(@PathVariable("id") String id, MovieDTO dto) {
        return ResponseEntity.ok(service.updateMovie(id, dto));
    }
    @DeleteMapping("/movie/delete/id/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable("id") String id) {
        service.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }
}
