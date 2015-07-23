package com.ishaihachlili.nano.movies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ishaihachlili.nano.movies.Api.Model.MovieModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MoviesArrayAdapter extends ArrayAdapter<MovieModel> {
    private final Activity _context;

    public MoviesArrayAdapter(Activity context, int resource) {
        super(context, resource);
        _context=context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = _context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_movie, null, true);
        MovieModel movie = getItem(position);
        if (movie != null) {
            ImageView imageView = (ImageView) rowView.findViewById(R.id.poster);
            String posterPath = movie.getMoviePosterPath();
            Picasso.with(_context).load(posterPath).into(imageView);
        }

        return rowView;
    };
}
