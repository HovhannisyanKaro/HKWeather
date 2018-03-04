package hk.com.controllers;

import hk.com.entities.Weather;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class DataController {
    private static final DataController ourInstance = new DataController();

    private Weather firstWeatherData;



    public static DataController getDataController() {
        return ourInstance;
    }

    private DataController() {
    }

    public Weather getFirstWeatherData() {
        return firstWeatherData;
    }

    public void setFirstWeatherData(Weather firstWeatherData) {
        this.firstWeatherData = firstWeatherData;
    }
}
