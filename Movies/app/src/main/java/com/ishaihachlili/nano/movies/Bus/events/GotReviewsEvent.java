package com.ishaihachlili.nano.movies.bus.events;

import com.ishaihachlili.nano.movies.api.Model.ReviewResultsModel;

public class GotReviewsEvent {
    private ReviewResultsModel result;

    public GotReviewsEvent(ReviewResultsModel results) {
        this.result = results;
    }

    public ReviewResultsModel getResult() {
        return result;
    }
}
