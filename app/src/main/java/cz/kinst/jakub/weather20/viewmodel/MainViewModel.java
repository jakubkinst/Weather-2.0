package cz.kinst.jakub.weather20.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.design.widget.Snackbar;
import android.view.View;

import cz.kinst.jakub.weather20.BR;
import cz.kinst.jakub.weather20.R;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.weatherapi.CurrentWeatherResponse;
import cz.kinst.jakub.weather20.weatherapi.WeatherApiProvider;
import cz.kinst.jakub.weather20.weatherapi.WeatherForecastResponse;
import me.tatarka.bindingcollectionadapter.ItemView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jakubkinst on 02/12/15.
 */
public class MainViewModel extends BaseCallViewModel<ActivityMainBinding> {

    private static final String CALL_WEATHER_CURRENT = "weather_current";
    private static final String CALL_WEATHER_FORECAST = "weather_forecast";
    private final ObservableList<WeatherForecastResponse.Forecast> mWeatherForecast = new ObservableArrayList<>();
    private final ItemView mForecastItemView = ItemView.of(BR.forecast, R.layout.forecast_item);
    private CurrentWeatherResponse mCurrentWeather;

    @Override
    public void onViewAttached(boolean firstAttachment) {
        super.onViewAttached(firstAttachment);
        if (firstAttachment)
            refreshWeather(null);
    }


    public void refreshWeather(View view) {
        enqueueCall(CALL_WEATHER_CURRENT, WeatherApiProvider.get().getCurrent("Prague, Czech Republic"), new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Response<CurrentWeatherResponse> response, Retrofit retrofit) {
                mCurrentWeather = response.body();
                notifyPropertyChanged(BR.currentWeather);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        enqueueCall(CALL_WEATHER_FORECAST, WeatherApiProvider.get().getForecast("Prague, Czech Republic"), new Callback<WeatherForecastResponse>() {
            @Override
            public void onResponse(Response<WeatherForecastResponse> response, Retrofit retrofit) {
                mWeatherForecast.clear();
                mWeatherForecast.addAll(response.body().getList());
                notifyPropertyChanged(BR.weatherForecast);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        Snackbar.make(getView().getBinding().getRoot(), "Refreshing weather...", Snackbar.LENGTH_SHORT).show();
    }

    @Bindable
    public CurrentWeatherResponse getCurrentWeather() {
        return mCurrentWeather;
    }

    @Bindable
    public ObservableList<WeatherForecastResponse.Forecast> getWeatherForecast() {
        return mWeatherForecast;
    }

    @Bindable
    public ItemView getForecastItemView() {
        return mForecastItemView;
    }
}
