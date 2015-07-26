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
        MovieDetailsType detailsType = getMovieDetailsEvent.getDetailsType();

        switch (detailsType){
            case BasicInfo:
                getMovieDetails(movieId);
                break;
            case Trailers:
                getMovieTrailers(movieId);
                break;
            case Reviews:
                getMovieReviews(movieId);
                break;
        }

    }

    private void getMovieDetails(Integer movieId) {
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

    private void getMovieTrailers(Integer movieId) {
        Callback<TrailerResultsModel> callback = new Callback<TrailerResultsModel>() {
            @Override
            public void success(TrailerResultsModel trailerResultsModel, Response response) {
                mBus.post(new GotTrailersEvent(trailerResultsModel));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new ApiErrorEvent(error));
            }
        };

        sMoviesApiClient.getMovieTrailers(movieId, callback);
    }

    private void getMovieReviews(Integer movieId) {
        Callback<ReviewResultsModel> callback = new Callback<ReviewResultsModel>() {
            @Override
            public void success(ReviewResultsModel reviewResultsModel, Response response) {
                mBus.post(new GotReviewsEvent(reviewResultsModel));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new ApiErrorEvent(error));
            }
        };

        sMoviesApiClient.getMovieReviews(movieId, callback);
    }

}
