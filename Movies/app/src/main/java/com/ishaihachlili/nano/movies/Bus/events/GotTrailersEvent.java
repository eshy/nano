package com.ishaihachlili.nano.movies.bus.events;

import com.ishaihachlili.nano.movies.api.Model.TrailerResultsModel;

public class GotTrailersEvent {
    private TrailerResultsModel result;

    public GotTrailersEvent(TrailerResultsModel results) {
        this.result = results;
    }

    public TrailerResultsModel getResult() {
        return result;
    }
}
