package hk.com.api;

import hk.com.entities.Weather;
import retrofit.Call;
import retrofit.http.GET;


public interface ApiService {

    String WEATHER = "group?id=524901,703448,2643743&units=metric&appid=df89b276f505b3cd46c078270f8cde72";

    @GET(WEATHER)
    Call<Weather> getWeatherInfo();

}
