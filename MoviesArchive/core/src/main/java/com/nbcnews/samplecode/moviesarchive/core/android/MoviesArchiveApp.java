package com.nbcnews.samplecode.moviesarchive.core.android;

import android.app.Application;

/**
 * Created by KevinL on 3/24/15.
 */
public class MoviesArchiveApp extends Application {
	private static MoviesArchiveApp instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static MoviesArchiveApp get() {
		return instance;
	}

}
