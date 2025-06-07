package com.atharva.TMDB.controller;

import com.atharva.TMDB.model.MovieResponse;
import com.atharva.TMDB.service.TmdbService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final TmdbService tmdbService;

    public MovieController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }



    @GetMapping("/discover/movies")
    public Mono<MovieResponse> discoverMovies(){
        return tmdbService.discoverMovies();
    }

    @GetMapping("/discover/tv")
    public Mono<MovieResponse> discoverTv(){
        return tmdbService.discoverTv();
    }

    //MOVIE LISTS
    @GetMapping("/popular")
    public Mono<MovieResponse> getPopularMovies() {
        return tmdbService.getPopularMovies();
    }

    @GetMapping("/movie/top_rated")
    public Mono<MovieResponse> getTopRatedMovies(){
        return tmdbService.getTopRatedMovies();
    }

    @GetMapping("/movie/upcoming")
    public Mono<MovieResponse> getUpcomingMovies(){
        return tmdbService.getUpcomingMovies();
    }

    @GetMapping("/movie/now_playing")
    public Mono<MovieResponse> getNowPlayingMovies(){
        return tmdbService.getNowPlayingMovies();
    }

    @GetMapping("/account/{accountId}")
    public Mono<String> getAccountDetails(@PathVariable String accountId) {
        return tmdbService.getAccountDetails(accountId);
    }

    @GetMapping("/movie/{movieId}")
    public  Mono<String> getAccountById(@PathVariable String movieId){
        return tmdbService.getMovieByID(movieId);
    }

    @PostMapping("/account/{accountId}/favorite")
    public Mono<String> markAsFavorite(
            @PathVariable String accountId,
            @RequestBody Map<String, Object> request) {

        int mediaId = (int) request.get("media_id");
        String mediaType = (String) request.get("media_type");
        boolean favorite = (boolean) request.get("favorite");

        return tmdbService.markAsFavorite(accountId, mediaId, mediaType, favorite);
    }




}
