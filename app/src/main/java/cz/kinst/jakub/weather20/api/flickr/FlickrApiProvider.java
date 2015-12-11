package cz.kinst.jakub.weather20.api.flickr;

import cz.kinst.jakub.weather20.api.ApiProvider;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class FlickrApiProvider {
	private static FlickrInterface sInstance;


	public interface FlickrInterface {
		@GET("/services/rest/?method=flickr.photos.search&api_key=52d6e5322bc1464fe37ef369c9718ca0&format=json&nojsoncallback=1")
		Call<SearchResponse> getPhotosForLocation(@Query("lat") double latitude, @Query("lon") double longitude, @Query("tags") String tags);
	}


	public static FlickrInterface get() {
		if(sInstance == null)
			sInstance = ApiProvider.getRetrofitInterface("https://api.flickr.com", FlickrInterface.class);
		return sInstance;
	}
}
