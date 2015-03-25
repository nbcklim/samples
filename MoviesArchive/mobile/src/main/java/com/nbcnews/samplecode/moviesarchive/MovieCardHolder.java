package com.nbcnews.samplecode.moviesarchive;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by KevinL on 3/24/15.
 */
public class MovieCardHolder extends RecyclerView.ViewHolder {
	private final View card;
	private TextView titleView;
	private ImageView imageView;

	public MovieCardHolder(View card) {
		super(card);
		this.card = card;
		titleView = (TextView) card.findViewById(R.id.title_text);
		imageView = (ImageView) card.findViewById(R.id.thumb);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setThumbUrl(String thumbUrl) {
		Picasso.with(imageView.getContext()).load(thumbUrl).fit().centerCrop().into(imageView);
	};

	public void setOnClickListener(View.OnClickListener onClickListener) {
		card.setOnClickListener(onClickListener);
	}
}
