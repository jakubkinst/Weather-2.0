package cz.kinst.jakub.weather20.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import cz.kinst.jakub.weather20.BuildConfig;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by jakubkinst on 11/12/15.
 */
public class ApiProvider {
	public static <T> T getRetrofitInterface(String url, Class<T> apiInterface) {
		OkHttpClient client = new OkHttpClient();
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
		client.interceptors().add(interceptor);

		return new Retrofit.Builder()
				.client(client)
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(apiInterface);
	}
}
