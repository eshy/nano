package com.ishaihachlili.nano.movies.bus;

import android.content.Context;

import com.ishaihachlili.nano.movies.api.MoviesApiClient;
import com.ishaihachlili.nano.movies.api.Model.*;
import com.ishaihachlili.nano.movies.bus.events.*;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ishai on 7/24/2015.
 */
public class MoviesManager {
    private Context mContext;
    private Bus mBus;
    private MoviesApiClient sMoviesApiClient;

    public MoviesManager(Context context, Bus bus){
        mContext=context;
        mBus=bus;
        sMoviesApiClient = MoviesApiClient.getClient();
    }

    @Subscribe
    public void onGetMoviesEvent(GetMoviesEvent getMoviesEvent){
        String sortBy = getMoviesEvent.getSortBy();

        Callback<MovieResultsModel> callback = new Callback<MovieResultsModel>() {
            @Override
            public void success(MovieResultsModel movieResultsModel, Response response) {
                mBus.post(new GotMoviesEvent(movieResultsModel));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new ApiErrorEvent(error));
            }
        };

        sMoviesApiClient.getMovies(sortBy, callback);
    }

    @Subscribe
    public void onGetMovieDetailsEvent(GetMovieDetailsEvent getMovieDetailsEvent){
        Integer movieId = getMovieDetailsEvent.getMovieId();

        Callback<MovieDetailsModel> callback = new Callback<MovieDetailsModel>() {
            @Override
            public void success(MovieDetailsModel movieDetailsModel, Response response) {
                mBus.post(new GotMovieDetailsEvent(movieDetailsModel));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new ApiErrorEvent(error));
            }
        };

        sMoviesApiClient.getMovieDetails(movieId, callback);
    }


}
