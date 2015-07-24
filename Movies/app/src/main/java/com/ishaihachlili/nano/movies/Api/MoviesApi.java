package com.ishaihachlili.nano.movies.Api;

import com.ishaihachlili.nano.movies.Api.Model.*;

import retrofit.RestAdapter;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MoviesApi {
    private final String LOG_TAG = MoviesApi.class.getSimpleName();

    private static final String PosterBaseUrl = "http://image.tmdb.org/t/p/";
    private final String BaseUrl = "http://api.themoviedb.org/3/";

    private final String ApiKey = ""; //Add API KEY Here

    //filter movies that didn't get enough votes
    private final Integer minVoteCount = 10;

    public MovieItemModel[] GetMovies(String sortBy){
        MovieResultsModel result = getApi().movies(sortBy,minVoteCount, ApiKey);

        return result.getMovies();
    }

    public MovieDetailsModel GetMovieDetails(Integer movieId){
        MovieDetailsModel result = getApi().movieDetails(movieId, ApiKey);

        return result;
    }


    public static String BuildMoviePosterPath(String width, String posterPath) {
        return PosterBaseUrl + width + posterPath;
    }

    private IMoviesDbApi getApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BaseUrl)  //call your base url
                .build();

        IMoviesDbApi api = restAdapter.create(IMoviesDbApi.class);
        return api;
    }

}
