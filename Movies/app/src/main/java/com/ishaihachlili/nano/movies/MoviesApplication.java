package com.ishaihachlili.nano.movies;

import android.app.Application;
import android.util.Log;

import com.ishaihachlili.nano.movies.bus.ApiErrorEvent;
import com.ishaihachlili.nano.movies.bus.BusProvider;
import com.ishaihachlili.nano.movies.bus.MoviesManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by Ishai on 7/24/2015.
 */
public class MoviesApplication extends Application {
    private final String LOG_TAG = MoviesApplication.class.getSimpleName();

    private MoviesManager _moviesManager;
    private Bus _bus = BusProvider.getInstance();

    @Override
    public void onCreate() {
        //super.onCreate();
        _moviesManager = new MoviesManager(this, _bus);
        _bus.register(_moviesManager);
        _bus.register(this);
    }

    @Subscribe
    public void onApiError(ApiErrorEvent event) {
        //toast("Something went wrong, please try again.");
        Log.e(LOG_TAG, event.getErrorMessage());
    }
}
