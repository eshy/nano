package com.ishaihachlili.nano.movies.api.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class TrailersModel {

    @Expose
    private List<YoutubeTrailerModel> youtube = new ArrayList<YoutubeTrailerModel>();

    /**
     *
     * @return
     * The youtube
     */
    public List<YoutubeTrailerModel> getYoutube() {
        return youtube;
    }

    /**
     *
     * @param youtube
     * The youtube
     */
    public void setYoutube(List<YoutubeTrailerModel> youtube) {
        this.youtube = youtube;
    }

}
