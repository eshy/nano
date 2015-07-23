package com.ishaihachlili.nano.movies.Api;

import com.ishaihachlili.nano.movies.Api.Model.MovieModel;
import com.ishaihachlili.nano.movies.Api.Model.MovieResultsModel;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Ishai on 7/23/2015.
 */
public interface IMoviesDbApi {
    @GET("/discover/movie")
    MovieResultsModel movies(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);
}
