package com.atharva.TMDB.service;

import com.atharva.TMDB.model.MovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.xml.transform.sax.SAXResult;
import java.util.Map;

@Service
public class TmdbService {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.token}")  // This should be the API Read Access Token (v4 auth)
    private String apiToken;


    public TmdbService(WebClient webClient) {
        this.webClient = webClient;
    }

    //Section-ACCOUNT
    //Get the public details of an account on TMDB.
    public Mono<String> getAccountDetails(String accountId) {
        return webClient.get()
                .uri("/account/" + accountId)
                .headers(headers -> {
                    headers.setBearerAuth(apiToken);  // Set Bearer token dynamically
                    headers.set("Accept", "application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Mark a movie or TV show as a favourite.
    public Mono<String> addFavorite(String accountId, int mediaId, String mediaType, boolean favorite) {
        String url = "/account/" + accountId + "/favorite";

        Map<String, Object> requestBody = Map.of(
                "media_type", mediaType,  // "movie" or "tv"
                "media_id", mediaId,
                "favorite", favorite
        );

        return webClient.post()
                .uri(url)
                .headers(headers -> {
                    headers.setBearerAuth(apiToken);  // Authentication
                    headers.set("Accept", "application/json");
                    headers.set("Content-Type", "application/json");
                })
                .bodyValue(requestBody)  // Set JSON request body
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> addToWatchList(String accountId, int mediaId, String mediaType, boolean watchlist) {
        String url = "/account/" + accountId + "/watchlist";

        Map<String, Object> requestBody = Map.of(
                "media_type", mediaType,  // "movie" or "tv"
                "media_id", mediaId,
                "watchlist", watchlist
        );

        return webClient.post()
                .uri(url)
                .headers(headers -> {
                    headers.setBearerAuth(apiToken);  // Authentication
                    headers.set("Accept", "application/json");
                    headers.set("Content-Type", "application/json");
                })
                .bodyValue(requestBody)  // Set JSON request body
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a users list of favourite movies.
    public Mono<String> getFavoriteMovies(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/favorite/movies")
                .headers(headers -> {
                    headers.setBearerAuth(apiToken);  // Set Bearer token dynamically
                    headers.set("Accept", "application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a users list of favourite TV shows.
    public Mono<String> getFavoriteTv(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/favorite/tv")
                .headers(headers -> {
                    headers.setBearerAuth(apiToken);  // Set Bearer token dynamically
                    headers.set("Accept", "application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a users list of custom lists.
    public Mono<String> getUserList(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/lists")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept", "application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a users list of rated movies.
    public Mono<String> getUsersListOfRatedMovies(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/rated/movies")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept","application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a users list of rated TV shows.
    public Mono<String> getUsersListOfRatedTv(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/rated/tv")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept","application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a users list of rated TV episodes.
    public Mono<String> getListOfUserRatedTvEpisodes(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/rated/tv/episodes")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept","application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a list of movies added to a users watchlist.
    public Mono<String> getListOfMoviesAddedToUsersWatchlist(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/watchlist/movies")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept","application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //Get a list of TV shows added to a users watchlist.
    public Mono<String> getListOfTvAddedToUsersWatchlist(String accountId){
        return webClient.get()
                .uri("/account/"+accountId+"/watchlist/tv")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept","application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    //SECTION-ACCOUNT-END






    //Section-Discover
    public Mono<MovieResponse> discoverMovies() {
        return webClient.get()
                .uri("/discover/movie?api_key=" + apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);

    }

    public Mono<MovieResponse> discoverTv(){
        return webClient.get()
                .uri("/discover/tv?api_key="+apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);
    }

    //Section-MOVIE LISTS
    public Mono<MovieResponse> getPopularMovies() {
        return webClient.get()
                .uri("/movie/popular?api_key=" + apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);

    }

    public Mono<MovieResponse> getTopRatedMovies(){
        return webClient.get()
                .uri("/movie/top_rated?api_key="+apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);
    }

    public Mono<MovieResponse> getUpcomingMovies(){
        return webClient.get()
                .uri("/movie/upcoming?api_key="+apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);
    }

    public Mono<MovieResponse> getNowPlayingMovies(){
        return webClient.get()
                .uri("/movie/now_playing?api_key="+apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);
    }



    public Mono<String> getMovieByID(String movieId) {
        return webClient.get()
                .uri("/movie/" + movieId)
                .headers(headers -> {
                    headers.setBearerAuth(apiToken);  // Set Bearer token dynamically
                    headers.set("Accept", "application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getAvailableRegions(){
        return webClient.get()
                .uri("/watch/providers/regions")
                .headers(headers->{
                    headers.setBearerAuth(apiToken);
                    headers.set("Accept", "application/json");
                })
                .retrieve()
                .bodyToMono(String.class);
    }





}

