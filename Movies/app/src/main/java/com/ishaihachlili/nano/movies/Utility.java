package com.ishaihachlili.nano.movies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ishai on 7/25/2015.
 */
public class Utility {
    public static String getSortingOrder(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_sorting_key), context.getString(R.string.pref_sorting_default_value));
    }

}
