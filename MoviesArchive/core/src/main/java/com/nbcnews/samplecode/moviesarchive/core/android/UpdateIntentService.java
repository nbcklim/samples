package com.nbcnews.samplecode.moviesarchive.core.android;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.nbcnews.samplecode.moviesarchive.core.model.Movie;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * helper methods.
 */
public class UpdateIntentService extends IntentService {
	private static final String ACTION_UPDATE = "com.nbcnews.samplecode.moviesarchive.core.action.UPDATE";
	private static final String URL_MOVIES = "http://archive.org/services/collection-rss.php?mediatype=movies&query=format:(MPEG4)&output=json";

	private final OkHttpClient client = new OkHttpClient();

	/**
	 * Starts this service to perform action Update with the given parameters. If
	 * the service is already performing a task this action will be queued.
	 *
	 * @see IntentService
	 */
	public static void startActionUpdate(Context context) {
		Intent intent = new Intent(context, UpdateIntentService.class);
		intent.setAction(ACTION_UPDATE);
		context.startService(intent);
	}


	public UpdateIntentService() {
		super("UpdateIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent != null) {
			final String action = intent.getAction();
			if (ACTION_UPDATE.equals(action)) {
				try {
					handleActionUpdate(SystemClock.uptimeMillis());
				} catch (Exception e) {
					Log.w(UpdateIntentService.class.getSimpleName(), "exception in update", e);
				}
			}
		}
	}

	/**
	 * Handle action Update in the provided background thread with the provided
	 * parameters.
	 */
	private void handleActionUpdate(long uptimeMillis) throws Exception {
		if (shouldUpdate(uptimeMillis)) {
			Request request = new Request.Builder()
					.url(URL_MOVIES)
					.build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			String jsonStr = response.body().string();
			new Prefs().store(Movie.PREFS_KEY, jsonStr);
		}
	}

	private boolean shouldUpdate(long uptimeMillis) {
		return true;
	}
}
