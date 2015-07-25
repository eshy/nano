package com.ishaihachlili.nano.movies.api;

import com.ishaihachlili.nano.movies.api.Model.*;
import com.ishaihachlili.nano.movies.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MoviesApiClient {
//    private final String LOG_TAG = MoviesApiClient.class.getSimpleName();

    private static final String PosterBaseUrl = "http://image.tmdb.org/t/p/";
    private final String BaseUrl = "http://api.themoviedb.org/3/";

    private final String ApiKey = ""; //Add API KEY Here

    //filter movies that didn't get enough votes
    private final Integer minVoteCount = 10;

    private static MoviesApiClient mMoviesApiClient;
    private static RestAdapter mRestAdapter;
    private static IMoviesDbApi mMoviesDbApi;

    public static MoviesApiClient getClient() {
        if(mMoviesApiClient == null){
            mMoviesApiClient = new MoviesApiClient();
        }
        return mMoviesApiClient;
    }

    private MoviesApiClient() {
        mRestAdapter = new RestAdapter.Builder()
                .setLogLevel(BuildConfig.DEBUG ?
                        RestAdapter.LogLevel.FULL :
                        RestAdapter.LogLevel.NONE)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(BaseUrl)
                .build();
        mMoviesDbApi = mRestAdapter.create(IMoviesDbApi.class);
    }

    public void getMovies(String sortBy, Callback<MovieResultsModel> callback){
        mMoviesDbApi.movies(sortBy, minVoteCount, ApiKey, callback);
    }

    public void getMovieDetails(Integer movieId, Callback<MovieDetailsModel> callback){
        mMoviesDbApi.movieDetails(movieId, ApiKey, callback);
    }

    public static String BuildMoviePosterPath(String width, String posterPath) {
        return PosterBaseUrl + width + posterPath;
    }
}
