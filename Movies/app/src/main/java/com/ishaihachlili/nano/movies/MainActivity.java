package com.ishaihachlili.nano.movies;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements MovieListFragment.Callback {
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
    private String mSortBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTwoPane = findViewById(R.id.movie_detail_container) != null;
        if (mTwoPane ) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new MovieDetailsFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity - onResume");
        String sortBy = Utility.getSortingOrder(this);
        if (mSortBy == null || sortBy == null || !sortBy.equals(mSortBy)){
        //if (sortBy != null && !sortBy.equals(mSortBy)){
            mSortBy = sortBy;
            Log.d(LOG_TAG, "MainActivity - onResume - call onSortingChanged");
            MovieListFragment fragment = (MovieListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_movies);
            if ( null != fragment ) {
                fragment.onSortingChanged();
            }
        }
    }

    @Override
    public void onItemSelected(Integer movieId, Boolean isFirst) {
        Log.d(LOG_TAG, "MainActivity - onItemSelected");
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailsFragment.MOVIE_ID, movieId);

            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DETAILFRAGMENT_TAG)
                    .commit();
        } else if (!isFirst) {
            Intent intent = new Intent(this, MovieDetailsActivity.class)
                    .putExtra(Intent.EXTRA_TEXT, movieId);
            startActivity(intent);
        }
    }
}
