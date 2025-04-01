package com.atharva.TMDB.model;

import lombok.Data;

import java.util.List;
@Data
public class MovieResponse {
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Data
    public static class Movie {
//        private String
        private String title;
        private String overview;
        private String popularity;
        private String release_date;
        private String vote_count;
        private String original_language;

        public String getOriginal_language() {
            return original_language;
        }

        public String getVote_count() {
            return vote_count;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getPopularity() {
            return popularity;
        }

        public String getTitle() {
            return title;
        }

        public String getOverview() {
            return overview;
        }
    }
}
