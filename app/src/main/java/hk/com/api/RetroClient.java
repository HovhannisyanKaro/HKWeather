package hk.com.api;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.JacksonConverterFactory;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Hovhannisyan.Karo on 04.11.2017.
 */

public class RetroClient {

    private static final String ROOT_URL = "http://api.openweathermap.org/data/2.5/";

    private static retrofit.Retrofit getRetrofitInstance() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(100, TimeUnit.SECONDS);
        client.setReadTimeout(100, TimeUnit.SECONDS);

        return new retrofit.Retrofit.Builder().baseUrl(ROOT_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(JacksonConverterFactory.create()).client(client).build();
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
