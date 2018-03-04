package hk.com.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import hk.com.entities.WeatherList;
import hk.com.enums.Constants;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class WeatherDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WEATHERINFO.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_QUERY =
            "CREATE TABLE " +
                    Constants.TABLE_NAME.toString() +
                    "(" +
                    Constants.COUNTRY.toString() + " TEXT," +
                    Constants.COUNTRY_NAME.toString() + " TEXT PRIMARY KEY" +
                    ");";

    public void addWeatherInfo(WeatherList weatherList, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COUNTRY.toString(), convertObjectToString(weatherList));
        contentValues.put(Constants.COUNTRY_NAME.toString(), weatherList.getName());
        db.insertWithOnConflict(Constants.TABLE_NAME.toString(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor getWeatherInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {Constants.COUNTRY_NAME.toString(), Constants.COUNTRY.toString()};
        cursor = db.query(Constants.TABLE_NAME.toString(), projections, null, null, null, null, null, null);
        return cursor;
    }

    public Cursor getWeather(String name, SQLiteDatabase db) {
        String[] projections = {Constants.COUNTRY.toString()};
        String selection = Constants.COUNTRY_NAME.toString() + " LIKE ?";
        String[] selection_args = {name};

        Cursor cursor = db.query(Constants.TABLE_NAME.toString(), projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public void deleteInformation(String name, SQLiteDatabase db) {
        String selection = Constants.COUNTRY_NAME.toString() + " LIKE ?";
        String[] selection_args = {name};
        db.delete(Constants.TABLE_NAME.toString(), selection, selection_args);
    }

    private String convertObjectToString(WeatherList weatherList) {
        return new Gson().toJson(weatherList);
    }

    public WeatherDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
