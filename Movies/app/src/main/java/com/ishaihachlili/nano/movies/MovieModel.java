package com.ishaihachlili.nano.movies;


/**
 * Created by Ishai on 7/23/2015.
 */
public class MovieModel {
    String _posterPath;
    Integer _movieId;

    public MovieModel(String posterPath, Integer movieId){
        _posterPath=posterPath;
        _movieId=movieId;
    }

    public String getMoviePath() {
        return "http://image.tmdb.org/t/p/" + "w185" + _posterPath;
    }

}
