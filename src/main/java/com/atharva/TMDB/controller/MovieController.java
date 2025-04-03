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

    //Section-ACCOUNT
    //Get the public details of an account on TMDB.
    @GetMapping("/account/{accountId}")
    public Mono<String> getAccountDetails(@PathVariable String accountId) {
        return tmdbService.getAccountDetails(accountId);
    }

    //Mark a movie or TV show as a favourite.
    @PostMapping("/account/{accountId}/favorite")
    public Mono<String> addFavorite(
            @PathVariable String accountId,
            @RequestBody Map<String, Object> request) {

        int mediaId = (int) request.get("media_id");
        String mediaType = (String) request.get("media_type");
        boolean favorite = (boolean) request.get("favorite");

        return tmdbService.addFavorite(accountId, mediaId, mediaType, favorite);
    }



    //Add a movie or TV show to your watchlist.
    @PostMapping("/account/{accountId}/watchlist")
    public Mono<String> addToWatchlist(
            @PathVariable String accountId,
            @RequestBody Map<String, Object> request){
        int mediaId = (int)request.get("media_id");
        String mediaType = (String)request.get("media_type");
        boolean watchlist = (boolean)request.get("watchlist");
        return tmdbService.addToWatchList(accountId,mediaId,mediaType,watchlist);
    }

    //Get a users list of favourite movies.
    @GetMapping("/account/{accountId}/favorite/movies")
    public  Mono<String> getFavMovies(@PathVariable String accountId){
        return tmdbService.getFavoriteMovies(accountId);
    }

    //Get a users list of favourite TV shows.
    @GetMapping("/account/{accountId}/favorite/tv")
    public  Mono<String> getFavTv(@PathVariable String accountId){
        return tmdbService.getFavoriteTv(accountId);
    }

    //Get a users list of custom lists.
    @GetMapping("account/{accountId}/lists")
    public Mono<String> getUserList(String accountId){
        return tmdbService.getUserList(accountId);
    }

    //Get a users list of rated movies.
    @GetMapping("/account/{accountId}/rated/movies")
    public Mono<String> getUsersListOfRatedMovies(String accountId){
        return tmdbService.getUsersListOfRatedMovies(accountId);
    }

    //Get a users list of rated TV shows.
    @GetMapping("/account/{accountId}/rated/tv")
    public Mono<String> getUsersListOfRatedTv(String accountId){
        return tmdbService.getUsersListOfRatedTv(accountId);
    }

    //Get a users list of rated TV episodes.
    @GetMapping("account/{accountId}/rated/tv/episodes")
    public Mono<String> getListOfUserRatedTvEpisodes(String accountId){
        return tmdbService.getListOfUserRatedTvEpisodes(accountId);
    }

    //Get a list of movies added to a users watchlist.
    @GetMapping("account/{accountId}/watchlist/movies")
    public Mono<String> getListOfMoviesAddedToUsersWatchlist(String accountId){
        return tmdbService.getListOfMoviesAddedToUsersWatchlist(accountId);
    }

    //Get a list of TV shows added to a users watchlist.
    @GetMapping("account/{accountId}/watchlist/tv")
    public Mono<String> getListOfTvAddedToUsersWatchlist(String accountId){
        return tmdbService.getListOfTvAddedToUsersWatchlist(accountId);
    }
    //SECTION-ACCOUNT-END







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

    @GetMapping("/movie/{movieId}")
    public  Mono<String> getMovieById(@PathVariable String movieId){
        return tmdbService.getMovieByID(movieId);
    }

    @GetMapping("watch/providers/regions")
    public Mono<String> getAvailableRegions(){
        return tmdbService.getAvailableRegions();
    }

}
