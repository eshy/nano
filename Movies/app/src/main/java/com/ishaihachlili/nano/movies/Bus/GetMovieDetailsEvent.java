package com.ishaihachlili.nano.movies.bus;

public class GetMovieDetailsEvent {
    private Integer movieId;

    public GetMovieDetailsEvent(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getMovieId() {
        return movieId;
    }
}
