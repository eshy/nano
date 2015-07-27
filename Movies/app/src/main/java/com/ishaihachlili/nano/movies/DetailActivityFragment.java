package com.ishaihachlili.nano.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ishaihachlili.nano.movies.api.Model.MovieDetailsModel;
import com.ishaihachlili.nano.movies.api.MoviesApiClient;
import com.ishaihachlili.nano.movies.bus.events.GetMovieDetailsEvent;
import com.ishaihachlili.nano.movies.bus.events.GotMovieDetailsEvent;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends BaseFragment {
    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    static final String MOVIE_ID = "MOVIE_ID";

    private Integer mMovieId = 0;
    private MovieDetailsModel mMovieDetails;

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

        Bundle arguments = getArguments();
        if (arguments != null){
            mMovieId = arguments.getInt(DetailActivityFragment.MOVIE_ID);
        }
        View rootView =  inflater.inflate(R.layout.fragment_detail, container, false);

        mPosterImageView = (ImageView) rootView.findViewById(R.id.movie_poster);
        mTitleTextView = (TextView) rootView.findViewById(R.id.movie_title);
        mReleaseTextView = (TextView) rootView.findViewById(R.id.movie_release);
        mLengthTextView = (TextView) rootView.findViewById(R.id.movie_length);
        mRatingTextView = (TextView) rootView.findViewById(R.id.movie_rating);
        mSynopsisTextView = (TextView) rootView.findViewById(R.id.movie_synopsis);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Log.d(LOG_TAG, "DetailActivityFragment - onSaveInstanceState");
        if (mMovieDetails != null) {
//            Log.d(LOG_TAG, "DetailActivityFragment - onSaveInstanceState - Save Movie Details");
            Gson gson = new Gson();
            String json = gson.toJson(mMovieDetails);
            outState.putString("moviedetails", json);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d(LOG_TAG, "DetailActivityFragment - onActivityCreated");

        if (savedInstanceState != null){
//            Log.d(LOG_TAG, "DetailActivityFragment - onActivityCreated - Load Movie Details");

            //using gson instead of parcelable because the class already has the attributes for json
            Gson gson = new Gson();
            mMovieDetails = gson.fromJson(savedInstanceState.getString("moviedetails", "{}"), MovieDetailsModel.class);
            updateMovieDetails();
        }
    }

    private void getMovieDetails() {
        if (mMovieId > 0) {
            Bus.post(new GetMovieDetailsEvent(mMovieId));
        }
    }

    @Subscribe
    public void onGotMovieDetailsEvent(GotMovieDetailsEvent result){
        if (result.getResult() != null) {
            mMovieDetails = result.getResult();
            updateMovieDetails();

        }
    }

    private void updateMovieDetails() {
        if (mMovieDetails != null) {
            String posterPath = MoviesApiClient.BuildMoviePosterPath("w185", mMovieDetails.getPosterPath());
            Picasso.with(getActivity()).load(posterPath).into(mPosterImageView);
            mTitleTextView.setText(mMovieDetails.getTitle());
            mReleaseTextView.setText(mMovieDetails.getReleaseDate());
            if (mMovieDetails.getRuntime()>0){
                mLengthTextView.setText(mMovieDetails.getRuntime() + "min");
            }
            if (mMovieDetails.getVoteAverage()>0) {
                mRatingTextView.setText(String.format("%s/10 (%s votes)", mMovieDetails.getVoteAverage(), mMovieDetails.getVoteCount()));
            }
            mSynopsisTextView.setText(mMovieDetails.getOverview());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mMovieDetails == null) {
            getMovieDetails();
        }
    }
//YouTube play video intent
//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));

}
