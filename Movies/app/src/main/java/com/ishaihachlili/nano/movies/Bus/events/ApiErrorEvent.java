package com.ishaihachlili.nano.movies.bus.events;

import retrofit.RetrofitError;

/**
 * Created by Ishai on 7/24/2015.
 */
public class ApiErrorEvent {
    private RetrofitError error;

    public ApiErrorEvent(RetrofitError error) {
        this.error = error;
    }

    public RetrofitError getError() {
        return error;
    }

    public String getErrorMessage() {
        return error.getMessage();
    }
}
