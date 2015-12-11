package cz.kinst.jakub.weather20.utility;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by jakubkinst on 03/12/15.
 */
public class BindingAdapters {
	@BindingAdapter({"imageUrl"})
	public static void setImageUrl(ImageView imageView, String url) {
		if(url != null && !url.trim().isEmpty())
			Glide.with(imageView.getContext())
					.load(url)
					.crossFade()
					.thumbnail(0.5f)
					.into(imageView);
	}


	@BindingAdapter({"hide"})
	public static void setHide(View view, boolean hide) {
		view.setVisibility(hide ? View.GONE : View.VISIBLE);
	}


	@BindingAdapter({"show"})
	public static void setShow(View view, boolean show) {
		view.setVisibility(show ? View.VISIBLE : View.GONE);
	}


	@BindingAdapter({"invisible"})
	public static void setInvisible(View view, boolean invisible) {
		view.setVisibility(invisible ? View.INVISIBLE : View.VISIBLE);
	}

}
