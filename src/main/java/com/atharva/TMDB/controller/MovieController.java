package com.atharva.TMDB.controller;

import com.atharva.TMDB.model.MovieResponse;
import com.atharva.TMDB.service.TmdbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final TmdbService tmdbService;

    public MovieController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/popular")
    public Mono<MovieResponse> getPopularMovies() {
        return tmdbService.getPopularMovies();
    }

    @GetMapping("/discover/movies")
    public Mono<MovieResponse> discoverMovies(){
        return tmdbService.discoverMovies();
    }

    @GetMapping("/discover/tv")
    public Mono<MovieResponse> discoverTv(){
        return tmdbService.discoverTv();
    }

    @GetMapping("/movie/top_rated")
    public Mono<MovieResponse> getTopRatedMovies(){
        return tmdbService.getTopRatedMovies();
    }

    @GetMapping("/movie/upcoming")
    public Mono<MovieResponse> getUpcomingMovies(){
        return tmdbService.getUpcomingMovies();
    }

}
