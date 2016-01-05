package cz.kinst.jakub.weather20.tool;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;


/**
 * Created by jakubkinst on 28/12/15.
 */
public class ContextProvider {
	private static Context sContext;


	private ContextProvider() {
	}


	public static void initialize(Context context) {
		sContext = context;
	}


	public static Context getContext() {
		if(sContext == null)
			throw new IllegalStateException("Context was not properly initialized. You should call ContextProvider.initialize(this) in Application's constructor.");
		return sContext;
	}


	public static Resources getResources() {
		return getContext().getResources();
	}


	public static String getString(@StringRes int resourceId) {
		return getResources().getString(resourceId);
	}


	public static String getString(@StringRes int resourceId, Object... args) {
		return getResources().getString(resourceId, args);
	}


	public static int getColor(@ColorRes int resourceId) {
		return ContextCompat.getColor(getContext(), resourceId);
	}
}
