package cz.kinst.jakub.weather20.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import cz.kinst.jakub.weather20.api.openweathermap.WeatherForecastResponse;
import cz.kinst.jakub.weather20.handler.ForecastItemHandler;


/**
 * Created by jakubkinst on 11/12/15.
 */
public class ForecastItemViewModel {
	public ObservableField<WeatherForecastResponse.Forecast> forecast = new ObservableField<>();
	private ForecastItemHandler handler;


	public ForecastItemViewModel(WeatherForecastResponse.Forecast forecast, ForecastItemHandler handler) {
		this.forecast.set(forecast);
		this.handler = handler;
	}


	public void onItemClicked(View v) {
		handler.onForecastClicked(forecast.get());
	}
}
