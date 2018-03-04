package hk.com.controllers;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hk.com.entities.Weather;
import hk.com.entities.WeatherCustom;
import hk.com.entities.WeatherList;
import hk.com.enums.Constants;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */


public class DBController {
    private static final DBController ourInstance = new DBController();

    public static DBController getInstance() {
        return ourInstance;
    }

    private DBController() {

    }

    public static void saveToSqlite(Activity context, WeatherList weatherList) throws JSONException {
        WeatherDBHelper weatherDBHelper = new WeatherDBHelper(context);
        SQLiteDatabase sqLiteDatabase = weatherDBHelper.getWritableDatabase();
        weatherDBHelper.addWeatherInfo(weatherList, sqLiteDatabase);
        weatherDBHelper.close();
    }

    public static List<WeatherList> getWeatherInfoFromSQLite(Activity activity) throws JSONException {
        WeatherDBHelper weatherDBHelper = new WeatherDBHelper(activity);
        SQLiteDatabase sqLiteDatabase = weatherDBHelper.getReadableDatabase();
        Cursor cursor = weatherDBHelper.getWeatherInfo(sqLiteDatabase);
        List<WeatherList> weatherLists = new ArrayList<>();
        String name = null;
        String outputarray = null;
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);
                outputarray = cursor.getString(1);
                Type type = new TypeToken<WeatherList>() {
                }.getType();
                Gson gson = new Gson();
                WeatherList weatherList = gson.fromJson(outputarray, type);
                weatherLists.add(weatherList);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();

        return weatherLists;
    }

    public static boolean isExists(Activity activity, String countryName) {
        WeatherDBHelper weatherDBHelper = new WeatherDBHelper(activity);
        SQLiteDatabase sqLiteDatabase = weatherDBHelper.getReadableDatabase();
        Cursor cursor = weatherDBHelper.getWeather(countryName, sqLiteDatabase);
        boolean isExists = false;
        if (cursor.moveToFirst()) {
            isExists = true;
        }
        sqLiteDatabase.close();
        return isExists;
    }


}
