package cz.kinst.jakub.weather20.handler;

import cz.kinst.jakub.weather20.api.openweathermap.WeatherForecastResponse;


/**
 * Created by jakubkinst on 11/12/15.
 */
public interface ForecastItemHandler {
	void onForecastClicked(WeatherForecastResponse.Forecast forecast);
}
