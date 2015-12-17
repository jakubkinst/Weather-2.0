package cz.kinst.jakub.weather20.preferences.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;

import java.util.Set;


/**
 * Created by jakubkinst on 17/12/15.
 */
public class EnhancedEditor {
	private final Context mContext;
	private SharedPreferences.Editor mEditor;


	public EnhancedEditor(SharedPreferences.Editor editor, Context context) {
		mEditor = editor;
		mContext = context;
	}


	public SharedPreferences.Editor getEditor() {
		return mEditor;
	}


	public boolean commit() {
		return getEditor().commit();
	}


	public void apply() {
		getEditor().apply();
	}


	public String getPrefKey(@StringRes int key) {
		return mContext.getString(key);
	}


	public EnhancedEditor putBoolean(@StringRes int key, boolean value) {
		getEditor().putBoolean(getPrefKey(key), value);
		return this;
	}


	public EnhancedEditor putInt(@StringRes int key, int value) {
		getEditor().putInt(getPrefKey(key), value);
		return this;
	}


	public EnhancedEditor putString(@StringRes int key, String defaultValue) {
		getEditor().putString(getPrefKey(key), defaultValue);
		return this;
	}


	public EnhancedEditor putFloat(@StringRes int key, float defaultValue) {
		getEditor().putFloat(getPrefKey(key), defaultValue);
		return this;
	}


	public EnhancedEditor putLong(@StringRes int key, long defaultValue) {
		getEditor().putLong(getPrefKey(key), defaultValue);
		return this;
	}


	public EnhancedEditor putStringSet(@StringRes int key, Set<String> defaultValues) {
		getEditor().putStringSet(getPrefKey(key), defaultValues);
		return this;
	}
}
