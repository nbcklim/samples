package com.nbcnews.samplecode.moviesarchive.core.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by KevinL on 3/24/15.
 */
public class Prefs {

	private static final String PREFS_NAME = "prefs.moviesarchive.data";

	public void store(String key, String str) {
		SharedPreferences prefs = MoviesArchiveApp.get().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.edit()
			.putString(key, str)
			.apply();
	}

	public void registerForChanges(SharedPreferences.OnSharedPreferenceChangeListener listener) {
		SharedPreferences prefs = MoviesArchiveApp.get().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterForChanges(SharedPreferences.OnSharedPreferenceChangeListener listener) {
		SharedPreferences prefs = MoviesArchiveApp.get().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}

	@Nullable
	public String get(String key) {
		SharedPreferences prefs = MoviesArchiveApp.get().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return prefs.getString(key, null);
	}
}
