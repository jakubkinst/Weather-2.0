package cz.kinst.jakub.weather20;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;


/**
 * Created by jakubkinst on 02/12/15.
 */
public class Preferences {
	private static Preferences sInstance;
	private final SharedPreferences mPrefs;
	private final Context mContext;


	public static Preferences get() {
		if(sInstance == null)
			sInstance = new Preferences(Weather20Application.getContext());
		return sInstance;
	}


	public Preferences(Context context) {
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		mContext = context;
	}


	public boolean isTempMetric() {
		return mPrefs.getBoolean(getPrefKey(R.string.pref_key_metric), mContext.getResources().getBoolean(R.bool.pref_default_metric));
	}


	public void setTempMetric(boolean metric) {
		mPrefs.edit().putBoolean(getPrefKey(R.string.pref_key_metric), metric).commit();
	}


	private String getPrefKey(@StringRes int keyResourceId) {
		return mContext.getString(keyResourceId);
	}
}
