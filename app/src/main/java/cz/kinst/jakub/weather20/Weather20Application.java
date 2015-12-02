package cz.kinst.jakub.weather20;

import android.app.Application;
import android.content.Context;

/**
 * Created by jakubkinst on 02/12/15.
 */
public class Weather20Application extends Application {
    static Context sContext;

    public Weather20Application() {
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }

}
