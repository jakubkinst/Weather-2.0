package cz.kinst.jakub.weather20.preferences.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.StringRes;

import java.util.Set;


/**
 * Created by jakubkinst on 17/12/15.
 */
public class BasePreferences {

	private final SharedPreferences mPrefs;
	private final Context mContext;


	protected BasePreferences(Context context) {
		mPrefs = context.getSharedPreferences("___pref___" + getClass().getSimpleName().toLowerCase(), Context.MODE_PRIVATE);
		mContext = context;
	}


	public void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
		mPrefs.registerOnSharedPreferenceChangeListener(listener);
	}


	public void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
		getPrefs().unregisterOnSharedPreferenceChangeListener(listener);
	}


	public SharedPreferences getPrefs() {
		return mPrefs;
	}


	// Convenience resource accessors
	public boolean getBoolean(@StringRes int key, boolean defaultValue) {
		return getPrefs().getBoolean(getPrefKey(key), defaultValue);
	}


	public int getInt(@StringRes int key, int defaultValue) {
		return getPrefs().getInt(getPrefKey(key), defaultValue);
	}


	public String getString(@StringRes int key, String defaultValue) {
		return getPrefs().getString(getPrefKey(key), defaultValue);
	}


	public float getFloat(@StringRes int key, float defaultValue) {
		return getPrefs().getFloat(getPrefKey(key), defaultValue);
	}


	public long getLong(@StringRes int key, long defaultValue) {
		return getPrefs().getLong(getPrefKey(key), defaultValue);
	}


	public Set<String> getStringSet(@StringRes int key, Set<String> defaultValues) {
		return getPrefs().getStringSet(getPrefKey(key), defaultValues);
	}


	protected String getPrefKey(@StringRes int keyResourceId) {
		return mContext.getString(keyResourceId);
	}


	protected EnhancedEditor getEditor() {
		return new EnhancedEditor(getPrefs().edit(), getContext());
	}


	protected Context getContext() {
		return mContext;
	}


	protected Resources getResources() {
		return getContext().getResources();
	}
}
