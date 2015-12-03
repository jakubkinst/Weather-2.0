package cz.kinst.jakub.weather20.weatherapi;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import cz.kinst.jakub.weather20.BuildConfig;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class WeatherApiProvider {
	private static final String WEATHER_APP_ID = "2de143494c0b295cca9337e1e96b00e0";

	private static WeatherApiInterface sInstance;


	public interface WeatherApiInterface {
		@GET("/data/2.5/weather?appid=" + WEATHER_APP_ID)
		Call<CurrentWeatherResponse> getCurrent(@Query("lat") double latitude, @Query("lon") double longitude);

		@GET("/data/2.5/forecast/daily?cnt=16&appid=" + WEATHER_APP_ID)
		Call<WeatherForecastResponse> getForecast(@Query("lat") double latitude, @Query("lon") double longitude);
	}


	public static WeatherApiInterface get() {
		if(sInstance == null) {
			OkHttpClient client = new OkHttpClient();
			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
			client.interceptors().add(interceptor);

			sInstance = new Retrofit.Builder()
					.client(client)
					.baseUrl("http://api.openweathermap.org")
					.addConverterFactory(GsonConverterFactory.create())
					.build()
					.create(WeatherApiInterface.class);
		}
		return sInstance;
	}
}
