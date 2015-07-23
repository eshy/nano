package com.ishaihachlili.nano.movies.Api;

import com.ishaihachlili.nano.movies.Api.Model.*;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Ishai on 7/23/2015.
 */
public interface IMoviesDbApi {
    @GET("/discover/movie")
    MovieResultsModel movies(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("/movie/{id}")
    MovieDetailsModel movieDetails(@Path("id") Integer movieId, @Query("api_key") String apiKey);
}
