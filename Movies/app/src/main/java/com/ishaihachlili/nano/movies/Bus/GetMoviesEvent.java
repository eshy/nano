package com.ishaihachlili.nano.movies.bus;

import com.ishaihachlili.nano.movies.api.Model.MovieResultsModel;

/**
 * Created by Ishai on 7/24/2015.
 */
public class GetMoviesEvent {
    private String sortBy;

    public GetMoviesEvent(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }
}


