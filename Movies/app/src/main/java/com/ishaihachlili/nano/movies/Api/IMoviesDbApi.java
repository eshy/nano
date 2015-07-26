package com.ishaihachlili.nano.movies.api;

import com.ishaihachlili.nano.movies.api.Model.*;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Ishai on 7/23/2015.
 */
public interface IMoviesDbApi {
    @GET("/discover/movie")
    void movies(@Query("sort_by") String sortBy, @Query("vote_count.gte") Integer minVoteCount, @Query("api_key") String apiKey, Callback<MovieResultsModel> callback);

    @GET("/movie/{id}")
    void movieDetails(@Path("id") Integer movieId, @Query("api_key") String apiKey, Callback<MovieDetailsModel> callback);

    @GET("/movie/{id}/videos")
    void movieTrailers(@Path("id") Integer movieId, @Query("api_key") String apiKey, Callback<TrailerResultsModel> callback);

    @GET("/movie/{id}/reviews")
    void movieReviews(@Path("id") Integer movieId, @Query("api_key") String apiKey, Callback<ReviewResultsModel> callback);
}
