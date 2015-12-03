package cz.kinst.jakub.weather20;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by jakubkinst on 03/12/15.
 */
public class BindingAdapters {
	@BindingAdapter({"imageUrl"})
	public static void setImageUrl(ImageView imageView, String url) {
		Glide.with(imageView.getContext())
				.load(url)
				.thumbnail(0.5f)
				.into(imageView);
	}
}
