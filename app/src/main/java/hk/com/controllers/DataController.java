package hk.com.controllers;

import hk.com.entities.Weather;
import hk.com.entities.WeatherList;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class DataController {
    private static final DataController ourInstance = new DataController();

    private Weather firstWeatherData;
    private WeatherList yourWeather;
    private Weather fewDaysData;



    public static DataController getDataController() {
        return ourInstance;
    }

    private DataController() {
    }

    public Weather getFewDaysData() {
        return fewDaysData;
    }

    public void setFewDaysData(Weather fewDaysData) {
        this.fewDaysData = fewDaysData;
    }

    public Weather getFirstWeatherData() {
        return firstWeatherData;
    }

    public void setFirstWeatherData(Weather firstWeatherData) {
        this.firstWeatherData = firstWeatherData;
    }

    public WeatherList getYourWeather() {
        return yourWeather;
    }

    public void setYourWeather(WeatherList yourWeather) {
        this.yourWeather = yourWeather;
    }
}
