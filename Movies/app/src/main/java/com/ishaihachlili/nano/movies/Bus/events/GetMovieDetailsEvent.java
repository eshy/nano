package com.ishaihachlili.nano.movies.bus.events;

public class GetMovieDetailsEvent {
    private Integer movieId;
    private MovieDetailsType detailsType;

    public GetMovieDetailsEvent(Integer movieId, MovieDetailsType detailsType) {
        this.movieId = movieId;
        this.detailsType = detailsType;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public MovieDetailsType getDetailsType() {
        return detailsType;
    }
}

