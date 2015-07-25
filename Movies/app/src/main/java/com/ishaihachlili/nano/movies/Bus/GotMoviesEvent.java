package com.ishaihachlili.nano.movies.bus;

import com.ishaihachlili.nano.movies.api.Model.MovieResultsModel;

public class GotMoviesEvent {
    private MovieResultsModel results;


    public GotMoviesEvent(MovieResultsModel results) {
        this.results = results;
    }

    public MovieResultsModel getResults() {
        return results;
    }
}

