package com.ishaihachlili.nano.movies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ishaihachlili.nano.movies.Api.Model.MovieItemModel;
import com.ishaihachlili.nano.movies.Api.MoviesApi;
import com.squareup.picasso.Picasso;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MoviesArrayAdapter extends ArrayAdapter<MovieItemModel> {
    private final Activity _context;

    public MoviesArrayAdapter(Activity context, int resource) {
        super(context, resource);
        _context=context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = _context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_movie, null, true);
        MovieItemModel movie = getItem(position);
        if (movie != null) {
            ImageView imageView = (ImageView) rowView.findViewById(R.id.movie_poster);
            String posterPath = MoviesApi.BuildMoviePosterPath("w185", movie.getPosterPath());
            Picasso.with(_context).load(posterPath).into(imageView);
        }

        return rowView;
    };
}
