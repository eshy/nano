package com.ishaihachlili.nano.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.ishaihachlili.nano.movies.api.Model.MovieItemModel;
import com.ishaihachlili.nano.movies.bus.GetMoviesEvent;
import com.ishaihachlili.nano.movies.bus.GotMoviesEvent;
import com.squareup.otto.Subscribe;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {
    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    private MoviesArrayAdapter mMoviesAdapter;
    private MovieItemModel[] mMovies;
    private String mSortOrder;

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
        Log.d(LOG_TAG, "MainActivityFragment - onCreateView");

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "MainActivityFragment - onSaveInstanceState");
        if (mMovies != null && mMovies.length>0) {
            Log.d(LOG_TAG, "MainActivityFragment - onSaveInstanceState - Save Movies");
            Gson gson = new Gson();
            String json = gson.toJson(mMovies);
            outState.putString("movies", json);
            //outState.putString("movies_sort", Utility.getSortingOrdr(getActivity()));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "MainActivityFragment - onActivityCreated");

        if (savedInstanceState != null){
            Log.d(LOG_TAG, "MainActivityFragment - onActivityCreated - LoadMovies");
//            String sortOrder = Utility.getSortingOrdr(getActivity());
//            if (savedInstanceState.getString("movies_sort") == sortOrder) {
//            }

            //using gson instead of parcelable because the class already has the attributes for json
            Gson gson = new Gson();
            mMovies = gson.fromJson(savedInstanceState.getString("movies", "[]"), MovieItemModel[].class);
            updateAdapter();
        }
    }

    private void updateMovies() {
        Log.d(LOG_TAG, "MainActivityFragment - updateMovies");
        mSortOrder = Utility.getSortingOrdr(getActivity());
        Bus.post(new GetMoviesEvent(mSortOrder));
    }

    @Subscribe
    public void onGotMoviesEvent(GotMoviesEvent result){
        Log.d(LOG_TAG, "MainActivityFragment - onGotMoviesEvent");
        if (result.getResults() != null) {
            mMovies = result.getResults().getMovies();
            updateAdapter();
        }
    }

    private void updateAdapter() {
        Log.d(LOG_TAG, "MainActivityFragment - updateAdapter");
        mMoviesAdapter.clear();
        for(MovieItemModel movie : mMovies) {
            mMoviesAdapter.add(movie);
        }
    }

    @Override
    public void onStart() {
        Log.d(LOG_TAG, "MainActivityFragment - onStart");
        super.onStart();
        if (mMovies == null || mMovies.length==0 || mSortOrder != Utility.getSortingOrdr(getActivity())) {
            Log.d(LOG_TAG, "MainActivityFragment - onStart - get movies");
            updateMovies();
        }
    }
}
