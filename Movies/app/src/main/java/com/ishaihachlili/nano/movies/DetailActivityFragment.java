package com.ishaihachlili.nano.movies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ishaihachlili.nano.movies.Api.Model.MovieDetailsModel;
import com.ishaihachlili.nano.movies.Api.MoviesApi;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
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
        protected void onPostExecute(MovieDetailsModel result) {
            if (result != null) {
            }
        }
    }

}
