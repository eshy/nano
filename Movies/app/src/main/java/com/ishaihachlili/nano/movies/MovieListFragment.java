package com.ishaihachlili.nano.movies;

import android.os.Bundle;
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
import com.ishaihachlili.nano.movies.bus.events.GetMoviesEvent;
import com.ishaihachlili.nano.movies.bus.events.GotMoviesEvent;
import com.squareup.otto.Subscribe;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends BaseFragment {
    private final String LOG_TAG = MovieListFragment.class.getSimpleName();

    private MoviesArrayAdapter mMoviesAdapter;
    private GridView mGridView;
    private int mPosition = GridView.INVALID_POSITION;
    private MovieItemModel[] mMovies;

    private static final String SELECTED_POSITION_KEY = "selected_position";
    private static final String MOVIE_LIST_KEY = "movies";

    public interface Callback {
        public void onItemSelected (Integer movieId, Boolean isFirst);
    }

    public MovieListFragment() {
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
        Log.d(LOG_TAG, "MovieListFragment - onCreateView");

        mMoviesAdapter =new MoviesArrayAdapter(getActivity(), R.layout.list_item_movie);

        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        mGridView = (GridView) rootView.findViewById(R.id.gridview_movies);
        mGridView.setAdapter(mMoviesAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Integer movieId = mMoviesAdapter.getItem(position).getMovieId();
                ((Callback) getActivity()).onItemSelected(movieId, false);
                mPosition=position;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_POSITION_KEY)){
            mPosition = savedInstanceState.getInt(SELECTED_POSITION_KEY);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "MovieListFragment - onSaveInstanceState");

        if (mPosition != GridView.INVALID_POSITION) {
            outState.putInt(SELECTED_POSITION_KEY, mPosition);
        }
        if (mMovies != null && mMovies.length>0) {
            Log.d(LOG_TAG, "MovieListFragment - onSaveInstanceState - Save Movies");
            Gson gson = new Gson();
            String json = gson.toJson(mMovies);
            outState.putString(MOVIE_LIST_KEY, json);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "MovieListFragment - onActivityCreated");

        if (savedInstanceState != null){
            Log.d(LOG_TAG, "MovieListFragment - onActivityCreated - LoadMovies");
            //using gson instead of parcelable because the class already has the attributes for json
            if (savedInstanceState.containsKey(MOVIE_LIST_KEY)) {
                Gson gson = new Gson();
                mMovies = gson.fromJson(savedInstanceState.getString(MOVIE_LIST_KEY, "[]"), MovieItemModel[].class);
                updateAdapter();
            }
        }
    }

    void onSortingChanged() {
        updateMovies();
    }

    private void updateMovies() {
        Log.d(LOG_TAG, "MovieListFragment - updateMovies");
        Bus.post(new GetMoviesEvent(Utility.getSortingOrder(getActivity())));
    }

    @Subscribe
    public void onGotMoviesEvent(GotMoviesEvent result){
        Log.d(LOG_TAG, "MovieListFragment - onGotMoviesEvent");
        if (result.getResults() != null) {
            mMovies = result.getResults().getMovies();
            updateAdapter();

            //load the first movie when loading movies
            Integer movieId = mMoviesAdapter.getItem(0).getMovieId();
            Log.d(LOG_TAG, "MovieListFragment - onGotMoviesEvent - load movieId=" + movieId);
            ((Callback) getActivity()).onItemSelected(movieId, true);
        }
    }

    private void updateAdapter() {
        Log.d(LOG_TAG, "MovieListFragment - updateAdapter");
        mMoviesAdapter.clear();

        if (mMovies==null || mMovies.length==0){ return; }

        for(MovieItemModel movie : mMovies) {
            mMoviesAdapter.add(movie);
        }
    }

    @Override
    public void onStart() {
        Log.d(LOG_TAG, "MovieListFragment - onStart");
        super.onStart();

        if (mMovies == null || mMovies.length==0) {
//            Log.d(LOG_TAG, "MovieListFragment - onStart - get movies (mSortOrder=" + mSortOrder + ", Sort Setting=" + Utility.getSortingOrder(getActivity()));
            updateMovies();
        }
    }
}
