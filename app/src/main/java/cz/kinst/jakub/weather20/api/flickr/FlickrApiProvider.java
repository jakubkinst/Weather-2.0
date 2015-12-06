package cz.kinst.jakub.weather20.api.flickr;

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
public class FlickrApiProvider {
	private static WeatherApiInterface sInstance;


	public interface WeatherApiInterface {
		@GET("/services/rest/?method=flickr.photos.search&api_key=52d6e5322bc1464fe37ef369c9718ca0&format=json&nojsoncallback=1")
		Call<SearchResponse> getPhotosForLocation(@Query("lat") double latitude, @Query("lon") double longitude, @Query("tags") String tags);
	}


	public static WeatherApiInterface get() {
		if(sInstance == null) {
			OkHttpClient client = new OkHttpClient();
			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
			client.interceptors().add(interceptor);

			sInstance = new Retrofit.Builder()
					.client(client)
					.baseUrl("https://api.flickr.com")
					.addConverterFactory(GsonConverterFactory.create())
					.build()
					.create(WeatherApiInterface.class);
		}
		return sInstance;
	}
}
