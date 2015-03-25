package com.nbcnews.samplecode.moviesarchive.core.model;

import android.net.Uri;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by KevinL on 3/24/15.
 */
public class Movie {
	public static final String EXTRA_KEY = "keys.extra.movies";
	public static String PREFS_KEY = "keys.prefs.movies";

	String[] collection;
	String[] description;
	String[] format;
	String[] identifier;
	String[] mediatype;
	Date[] publicdate;
	String[] subject;
	String[] title;

	public Movie(String[] collection, String[] description, String[] format, String[] identifier, String[] mediatype, Date[] publicdate, String[] subject, String[] title) {
		this.collection = collection;
		this.description = description;
		this.format = format;
		this.identifier = identifier;
		this.mediatype = mediatype;
		this.publicdate = publicdate;
		this.subject = subject;
		this.title = title;
	}

	public String serialize() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static Movie[] create(String serializedData) {
		Gson gson = new Gson();
		return gson.fromJson(serializedData, Movie[].class);
	}

	public String getTitle() {
		return title[0];
	}

	public String getImageUrl() {
		return "http://archive.org/services/get-item-image.php?identifier="+identifier[0];
	}

	public Uri getMovieUri() {
		return Uri.parse("https://archive.org/download/"+identifier[0]+"/format=MPEG4");
	}
}
