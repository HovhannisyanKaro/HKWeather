package hk.com.entities;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class WeatherCustom {
    private String name;
    private WeatherList weatherList;

    public WeatherCustom(String name, WeatherList weatherList) {
        this.name = name;
        this.weatherList = weatherList;
    }

    public WeatherCustom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherList getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(WeatherList weatherList) {
        this.weatherList = weatherList;
    }
}
