package hk.com.interfacies;

import android.view.View;

import hk.com.entities.WeatherList;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public interface OnRVWeatherClickListener {
    void onItemCLicked(View view, WeatherList weatherList);
}
