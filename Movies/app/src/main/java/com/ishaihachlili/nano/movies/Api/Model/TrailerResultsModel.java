package com.ishaihachlili.nano.movies.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ishai on 7/26/2015.
 */
public class TrailerResultsModel {
    @Expose
    private Integer id;
    @Expose
    private List<TrailerModel> trailers = new ArrayList<TrailerModel>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The trailers
     */
    public List<TrailerModel> getTrailers() {
        return trailers;
    }

    /**
     *
     * @param trailers
     * The results
     */
    public void setTrailers(List<TrailerModel> trailers) {
        this.trailers = trailers;
    }
}

