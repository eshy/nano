package com.ishaihachlili.nano.movies.bus;

import com.ishaihachlili.nano.movies.api.Model.MovieDetailsModel;

public class GotMovieDetailsEvent {
    private MovieDetailsModel result;

    public GotMovieDetailsEvent(MovieDetailsModel results) {
        this.result = results;
    }

    public MovieDetailsModel getResult() {
        return result;
    }
}
