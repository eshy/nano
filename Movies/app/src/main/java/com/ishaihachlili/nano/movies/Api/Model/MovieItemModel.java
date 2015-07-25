package com.ishaihachlili.nano.movies.api.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MovieItemModel {
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("id")
    @Expose
    private Integer movieId;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
