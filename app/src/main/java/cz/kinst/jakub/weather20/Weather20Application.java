package cz.kinst.jakub.weather20;

import android.app.Application;

import cz.kinst.jakub.weather20.tool.ContextProvider;


/**
 * Created by jakubkinst on 02/12/15.
 */
public class Weather20Application extends Application {
	public Weather20Application() {
		ContextProvider.initialize(this);
	}
}
