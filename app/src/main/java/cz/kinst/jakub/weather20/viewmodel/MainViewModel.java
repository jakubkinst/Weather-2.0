package cz.kinst.jakub.weather20.viewmodel;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.Random;

import cz.kinst.jakub.view.StatefulLayout;
import cz.kinst.jakub.viewmodelbinding.permissions.PermissionHelperProvider;
import cz.kinst.jakub.weather20.BR;
import cz.kinst.jakub.weather20.R;
import cz.kinst.jakub.weather20.api.flickr.FlickrApiProvider;
import cz.kinst.jakub.weather20.api.flickr.SearchResponse;
import cz.kinst.jakub.weather20.api.openweathermap.CurrentWeatherResponse;
import cz.kinst.jakub.weather20.api.openweathermap.WeatherApiProvider;
import cz.kinst.jakub.weather20.api.openweathermap.WeatherForecastResponse;
import cz.kinst.jakub.weather20.databinding.ActivityMainBinding;
import cz.kinst.jakub.weather20.handler.ForecastItemHandler;
import cz.kinst.jakub.weather20.preferences.Preferences;
import cz.kinst.jakub.weather20.viewmodel.extensions.RetrofitCallViewModel;
import me.tatarka.bindingcollectionadapter.ItemView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by jakubkinst on 02/12/15.
 */
public class MainViewModel extends RetrofitCallViewModel<ActivityMainBinding> implements SharedPreferences.OnSharedPreferenceChangeListener, ForecastItemHandler {

	// Call IDs
	private static final String CALL_WEATHER_CURRENT = "weather_current";
	private static final String CALL_WEATHER_FORECAST = "weather_forecast";
	private static final String CALL_FLICKR_IMAGE = "flickr_image";

	private static final double DEFAULT_LATITUDE = 50.088182;
	private static final double DEFAULT_LONGITUDE = 14.420210;

	// public fields and observables
	public final ItemView forecastItemView = ItemView.of(BR.itemViewModel, R.layout.item_forecast);
	public final ObservableList<ForecastItemViewModel> weatherForecast = new ObservableArrayList<>();
	public final ObservableField<CurrentWeatherResponse> currentWeather = new ObservableField<>();
	public final ObservableField<String> locationPhotoUrl = new ObservableField<>();
	public final ObservableField<Location> location = new ObservableField<>();
	public final ObservableField<StatefulLayout.State> state = new ObservableField<>(StatefulLayout.State.PROGRESS);


	@Override
	public void onViewAttached(boolean firstAttachment) {
		super.onViewAttached(firstAttachment);

		// do on first attachment only - ViewModel has just been created
		if(firstAttachment) {
			state.set(StatefulLayout.State.PROGRESS);
			if(((PermissionHelperProvider) getActivity()).getPermissionHelper().checkGrantedPermission(Manifest.permission.ACCESS_FINE_LOCATION))
				onLocationGranted();
			else
				((PermissionHelperProvider) getActivity()).getPermissionHelper().requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, (results) -> {
					if(results.get(Manifest.permission.ACCESS_FINE_LOCATION))
						onLocationGranted();
				});

			Preferences.get().registerListener(this);
		}
	}


	public void onClickRefresh(View view) {
		refreshWeather();
		Snackbar.make(view, R.string.message_refreshing_weather, Snackbar.LENGTH_SHORT).show();
	}


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		refreshWeather();
	}


	@Override
	public void onModelRemoved() {
		Preferences.get().unregisterListener(this);
		super.onModelRemoved();
	}


	@Override
	public void onForecastClicked(WeatherForecastResponse.Forecast forecast) {
		Snackbar.make(getBinding().getRoot(), "Forecast item clicked", Snackbar.LENGTH_SHORT).show();
	}


	@SuppressWarnings("ResourceType")
	private void onLocationGranted() {
		LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
		location.set(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		refreshWeather();
	}


	private void refreshWeather() {
		// get location image
		enqueueCall(CALL_FLICKR_IMAGE, FlickrApiProvider.get().getPhotosForLocation(getLatitude(), getLongitude(), "City"), new Callback<SearchResponse>() {
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
		enqueueCall(CALL_WEATHER_CURRENT, WeatherApiProvider.get().getCurrent(getLatitude(), getLongitude()), new Callback<CurrentWeatherResponse>() {

			@Override
			public void onResponse(Response<CurrentWeatherResponse> response, Retrofit retrofit) {
				currentWeather.set(response.body());
				if(!weatherForecast.isEmpty()) state.set(StatefulLayout.State.CONTENT);
			}


			@Override
			public void onFailure(Throwable t) {

			}
		});

		// get forecast
		enqueueCall(CALL_WEATHER_FORECAST, WeatherApiProvider.get().getForecast(getLatitude(), getLongitude()), new Callback<WeatherForecastResponse>() {
			@Override
			public void onResponse(Response<WeatherForecastResponse> response, Retrofit retrofit) {
				weatherForecast.clear();
				weatherForecast.addAll(Stream.of(response.body().getList()).map(f -> new ForecastItemViewModel(f, MainViewModel.this)).collect(Collectors.toList()));
				if(currentWeather.get() != null) state.set(StatefulLayout.State.CONTENT);
			}


			@Override
			public void onFailure(Throwable t) {

			}
		});
	}


	private double getLongitude() {
		return location.get() != null ? location.get().getLongitude() : DEFAULT_LONGITUDE;
	}


	private double getLatitude() {
		return location.get() != null ? location.get().getLatitude() : DEFAULT_LATITUDE;
	}


}
