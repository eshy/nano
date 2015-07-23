package com.ishaihachlili.nano.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.ishaihachlili.nano.movies.Api.Model.MovieModel;
import com.ishaihachlili.nano.movies.Api.MoviesApi;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MoviesArrayAdapter mMoviesAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.mainfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMoviesAdapter =new MoviesArrayAdapter(getActivity(), R.layout.list_item_movie);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_movies);
        gridView.setAdapter(mMoviesAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Integer movieId = mMoviesAdapter.getItem(position).getMovieId();
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, movieId);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void updateMovies() {
        FetchMoviesTask moviesTask = new FetchMoviesTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(getString(R.string.pref_sorting_key),
                getString(R.string.pref_sorting_default_value));
        moviesTask.execute(sortOrder);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, MovieModel[]> {
        private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

        @Override
        protected MovieModel[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            return new MoviesApi().GetMovies(params[0]);
        }

        @Override
        protected void onPostExecute(MovieModel[] result) {
            if (result != null) {
                mMoviesAdapter.clear();
                for(MovieModel movie : result) {
                    mMoviesAdapter.add(movie);
                }
            }
        }
    }
}
