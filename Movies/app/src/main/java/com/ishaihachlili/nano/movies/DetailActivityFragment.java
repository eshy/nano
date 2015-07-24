package com.ishaihachlili.nano.movies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ishaihachlili.nano.movies.Api.Model.MovieDetailsModel;
import com.ishaihachlili.nano.movies.Api.MoviesApi;
import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private ImageView mPosterImageView;
    private TextView mTitleTextView;
    private TextView mReleaseTextView;
    private TextView mLengthTextView;
    private TextView mRatingTextView;
    private TextView mSynopsisTextView;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_detail, container, false);

        mPosterImageView = (ImageView) rootView.findViewById(R.id.movie_poster);
        mTitleTextView = (TextView) rootView.findViewById(R.id.movie_title);
        mReleaseTextView = (TextView) rootView.findViewById(R.id.movie_release);
        mLengthTextView = (TextView) rootView.findViewById(R.id.movie_length);
        mRatingTextView = (TextView) rootView.findViewById(R.id.movie_rating);
        mSynopsisTextView = (TextView) rootView.findViewById(R.id.movie_synopsis);

        return rootView;
    }

    private void getMovieDetails() {
        FetchMovieDetailsTask movieDetailsTask = new FetchMovieDetailsTask();

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            Integer movieId = intent.getIntExtra(Intent.EXTRA_TEXT, 0);
            if (movieId>0){
                movieDetailsTask.execute(movieId);
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getMovieDetails();
    }

    public class FetchMovieDetailsTask extends AsyncTask<Integer, Void, MovieDetailsModel> {
        private final String LOG_TAG = FetchMovieDetailsTask.class.getSimpleName();

        @Override
        protected MovieDetailsModel doInBackground(Integer... params) {
            if (params.length == 0) {
                return null;
            }
            return new MoviesApi().GetMovieDetails(params[0]);
        }

        @Override
        protected void onPostExecute(MovieDetailsModel movie) {
            if (movie != null) {
                String posterPath = MoviesApi.BuildMoviePosterPath("w185", movie.getPosterPath());
                Picasso.with(getActivity()).load(posterPath).into(mPosterImageView);
                mTitleTextView.setText(movie.getTitle());
                mReleaseTextView.setText(movie.getReleaseDate());
                if (movie.getRuntime()>0){
                    mLengthTextView.setText(movie.getRuntime() + "min");
                }
                if (movie.getVoteAverage()>0) {
                    mRatingTextView.setText(String.format("%s/10 (%s votes)", movie.getVoteAverage(), movie.getVoteCount()));
                }
                mSynopsisTextView.setText(movie.getOverview());
            }
        }
    }

}
