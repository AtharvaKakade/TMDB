package com.atharva.TMDB.service;

import com.atharva.TMDB.model.MovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class TmdbService {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<MovieResponse> getPopularMovies() {
        return webClient.get()
                .uri("/movie/popular?api_key=" + apiKey)
                .retrieve()
                .bodyToMono(MovieResponse.class);

    }
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
    



}

