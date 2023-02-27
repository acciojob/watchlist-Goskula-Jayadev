package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("movies") //doubt reagarding this
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity  addMovie(@RequestBody Movie addmovie){
        String s = movieService. addMovie(addmovie);
        if(s!=null){return new ResponseEntity<>("success", HttpStatus.CREATED);}
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String s = movieService.addDirector(director);
        if(s!=null){return new ResponseEntity<>("success", HttpStatus.CREATED);}
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String mov_name,@RequestParam("director") String dir_name){
        String s = movieService.addMovieDirectorPair(mov_name,dir_name);
        if(s!=null){return new ResponseEntity<>("success", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Movie obj = movieService.getMovieByName(name);
        if(obj!=null){return new ResponseEntity<>(obj, HttpStatus.ACCEPTED);}
        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
    }

    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity  getDirectorByName(@PathVariable String name){
        Director obj = movieService. getDirectorByName(name);
        if(obj!=null){return new ResponseEntity<>(obj, HttpStatus.ACCEPTED);}
        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
    }


    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){
        List<String> l = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(l, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> list = movieService.findAllMovies();
        if(list!=null){return new ResponseEntity<>(list, HttpStatus.ACCEPTED);}
        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("dir_name") String name){
        String s = movieService.deleteDirectorByName(name);
        if(s!=null){return new ResponseEntity<>("success", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String s = movieService.deleteAllDirectors();
        if(s!=null){return new ResponseEntity<>("success", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
