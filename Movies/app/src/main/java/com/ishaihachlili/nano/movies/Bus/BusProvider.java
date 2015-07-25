package com.ishaihachlili.nano.movies.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Ishai on 7/24/2015.
 */
public final class BusProvider {
    private static final Bus mBus = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance() {
        return mBus;
    }

    private BusProvider(){}
}
