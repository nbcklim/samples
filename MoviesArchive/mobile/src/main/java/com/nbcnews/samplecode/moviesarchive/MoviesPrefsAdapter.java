package com.nbcnews.samplecode.moviesarchive;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nbcnews.samplecode.moviesarchive.core.android.Prefs;
import com.nbcnews.samplecode.moviesarchive.core.model.Movie;

/**
 * Created by KevinL on 3/24/15.
 */
public class MoviesPrefsAdapter extends RecyclerView.Adapter {

	private final Context context;
	private Movie[] movies;

	public MoviesPrefsAdapter(Context context) {
		this.context = context;
		update();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
		return new MovieCardHolder(card);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		MovieCardHolder cardHolder = (MovieCardHolder) holder;
		final Movie currMovie = movies[position];
		cardHolder.setTitle(currMovie.getTitle());
		cardHolder.setThumbUrl(currMovie.getImageUrl());
		cardHolder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, VideoActivity.class);
				intent.putExtra(Movie.EXTRA_KEY, currMovie.serialize());
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return movies == null ? 0 : movies.length;
	}

	public void update() {
		String moviesJson = new Prefs().get(Movie.PREFS_KEY);
		movies = TextUtils.isEmpty(moviesJson) ? null : new Gson().fromJson(moviesJson, Movie[].class);
		notifyDataSetChanged();
	}
}
