package com.nbcnews.samplecode.moviesarchive;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.nbcnews.samplecode.moviesarchive.core.model.Movie;

/**
 * Created by KevinL on 3/24/15.
 */
public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		final ProgressBar progressBar = (ProgressBar) findViewById(R.id.video_prog_view);
		final ImageView thumbView = (ImageView) findViewById(R.id.video_thumb_view);
		final VideoView videoView = (VideoView) findViewById(R.id.video_view);
		MediaController mediaController = new MediaController(this);
		mediaController.setAnchorView(videoView);
		videoView.setMediaController(mediaController);
		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				thumbView.setVisibility(View.GONE);
				progressBar.setVisibility(View.GONE);
				videoView.start();
			}
		});
		videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				finish();
			}
		});
		final String movieJson = getIntent().getStringExtra(Movie.EXTRA_KEY);

		new PlayMovieTask(videoView).execute(movieJson);
	}

	private class PlayMovieTask extends AsyncTask<String, Void, Uri>{

		private final VideoView videoView;

		public PlayMovieTask(VideoView videoView) {
			this.videoView = videoView;
		}

		@Override
		protected Uri doInBackground(String... params) {
			Movie movie = new Gson().fromJson(params[0], Movie.class);

			return movie.getMovieUri();
		}

		@Override
		protected void onPostExecute(Uri uri) {
			super.onPostExecute(uri);
			videoView.setVideoURI(uri);
		}
	}
}
