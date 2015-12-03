package cz.kinst.jakub.weather20.viewmodel;

import android.Manifest;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.Random;

import cz.kinst.jakub.weather20.BR;
import cz.kinst.jakub.weather20.MainActivity;
import cz.kinst.jakub.weather20.R;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.flickrapi.FlickrApiProvider;
import cz.kinst.jakub.weather20.flickrapi.SearchResponse;
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
	private static final String CALL_FLICKR = "flickr";

	public final ObservableList<WeatherForecastResponse.Forecast> weatherForecast = new ObservableArrayList<>();
	public final ObservableField<ItemView> forecastItemView = new ObservableField<>(ItemView.of(BR.forecast, R.layout.forecast_item));
	public final ObservableField<CurrentWeatherResponse> currentWeather = new ObservableField<>();
	public final ObservableField<String> locationPhotoUrl = new ObservableField<>();
	public final ObservableField<Location> location = new ObservableField<>();


	@Override
	public void onViewAttached(boolean firstAttachment) {
		super.onViewAttached(firstAttachment);
		if(firstAttachment) {
			if(((MainActivity) getActivity()).checkGrantedPermission(Manifest.permission.ACCESS_FINE_LOCATION))
				onLocationGranted();
			else
				((MainActivity) getActivity()).requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, (results) -> {
					if(results.get(Manifest.permission.ACCESS_FINE_LOCATION))
						onLocationGranted();
				});
		}
	}


	public void onRefreshClicked(View view) {
		refreshWeather();
	}


	private void refreshWeather() {
		if(location.get() == null)
			return;

		// get location image
		enqueueCall(CALL_FLICKR, FlickrApiProvider.get().getPhotosForLocation(location.get().getLatitude(), location.get().getLongitude(), "City"), new Callback<SearchResponse>() {
			@Override
			public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
				int index = new Random().nextInt(response.body().getPhotos().getPhoto().size());
				locationPhotoUrl.set(response.body().getPhotos().getPhoto().get(index).getUrl());
			}


			@Override
			public void onFailure(Throwable t) {

			}
		});

		// get current weather
		enqueueCall(CALL_WEATHER_CURRENT, WeatherApiProvider.get().getCurrent(location.get().getLatitude(), location.get().getLongitude()), new Callback<CurrentWeatherResponse>() {

			@Override
			public void onResponse(Response<CurrentWeatherResponse> response, Retrofit retrofit) {
				currentWeather.set(response.body());
			}


			@Override
			public void onFailure(Throwable t) {

			}
		});

		// get forecast
		enqueueCall(CALL_WEATHER_FORECAST, WeatherApiProvider.get().getForecast(location.get().getLatitude(), location.get().getLongitude()), new Callback<WeatherForecastResponse>() {
			@Override
			public void onResponse(Response<WeatherForecastResponse> response, Retrofit retrofit) {
				weatherForecast.clear();
				weatherForecast.addAll(response.body().getList());
			}


			@Override
			public void onFailure(Throwable t) {

			}
		});

		Snackbar.make(getView().getBinding().getRoot(), "Refreshing weather...", Snackbar.LENGTH_SHORT).show();
	}


	@SuppressWarnings("all")
	private void onLocationGranted() {
		LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
		location.set(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		refreshWeather();
	}

}
