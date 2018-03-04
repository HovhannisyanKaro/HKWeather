package hk.com.api;

import hk.com.entities.Weather;
import hk.com.entities.WeatherList;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface ApiService {

    String WEATHER = "group?id=5202009,4171563&units=metric&appid=df89b276f505b3cd46c078270f8cde72";

    @GET(WEATHER)
    Call<Weather> getWeatherInfo();

    @GET("weather?&units=metric&APPID=df89b276f505b3cd46c078270f8cde72")
    Call<WeatherList> getFavoriteCitesWeatherList(@Query("q")String citiesNames);

    @GET("forecast?&appid=df89b276f505b3cd46c078270f8cde72")
    Call<Weather> getWeatherDaily(@Query("q")String citiesNames);



}
