package cz.kinst.jakub.weather20.preferences;

import android.content.Context;

import cz.kinst.jakub.weather20.R;
import cz.kinst.jakub.weather20.preferences.base.BasePreferences;
import cz.kinst.jakub.weather20.tool.ContextProvider;


/**
 * Created by jakubkinst on 02/12/15.
 */
public class Preferences extends BasePreferences {
	private static Preferences sInstance;


	protected Preferences(Context context) {
		super(context);
	}


	public static Preferences get() {
		if(sInstance == null) sInstance = new Preferences(ContextProvider.getContext());
		return sInstance;
	}


	public boolean isTempMetric() {
		return getBoolean(R.string.pref_key_metric, getResources().getBoolean(R.bool.pref_default_metric));
	}


	public void setTempMetric(boolean metric) {
		getEditor().putBoolean(R.string.pref_key_metric, metric).commit();
	}
}
