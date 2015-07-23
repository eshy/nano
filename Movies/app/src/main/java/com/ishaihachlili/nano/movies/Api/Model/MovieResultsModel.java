package com.ishaihachlili.nano.movies.Api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MovieResultsModel {
    @SerializedName("results")
    @Expose
    private MovieItemModel[] movies;

    public MovieItemModel[] getMovies() {
        return movies;
    }

    public void setMovies(MovieItemModel[] movies) {
        this.movies = movies;
    }
}
