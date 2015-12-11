package cz.kinst.jakub.weather20.api.openweathermap;

import cz.kinst.jakub.weather20.api.ApiProvider;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class WeatherApiProvider {
	private static final String WEATHER_APP_ID = "2de143494c0b295cca9337e1e96b00e0";

	private static WeatherInterface sInstance;


	public interface WeatherInterface {
		@GET("/data/2.5/weather?appid=" + WEATHER_APP_ID)
		Call<CurrentWeatherResponse> getCurrent(@Query("lat") double latitude, @Query("lon") double longitude);

		@GET("/data/2.5/forecast/daily?cnt=16&appid=" + WEATHER_APP_ID)
		Call<WeatherForecastResponse> getForecast(@Query("lat") double latitude, @Query("lon") double longitude);
	}


	public static WeatherInterface get() {
		if(sInstance == null)
			sInstance = ApiProvider.getRetrofitInterface("http://api.openweathermap.org", WeatherInterface.class);
		return sInstance;
	}
}
