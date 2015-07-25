package com.ishaihachlili.nano.movies;

import android.app.Fragment;

import com.ishaihachlili.nano.movies.bus.BusProvider;
import com.squareup.otto.Bus;

/**
 * Created by Ishai on 7/24/2015.
 */
public class BaseFragment extends Fragment {
    protected Bus Bus = BusProvider.getInstance();

    @Override
    public void onResume() {
        super.onResume();
        Bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Bus.unregister(this);
    }

}
