package hk.com.controllers;

import android.app.Activity;
import android.content.Context;

import hk.com.api.ApiService;
import hk.com.api.RIdValues;
import hk.com.api.RetroClient;
import hk.com.entities.Weather;
import hk.com.entities.WeatherList;
import hk.com.interfacies.OnNetworkCallListener;
import hk.com.utils.ConnectionChecker;
import hk.com.utils.DialogUtils;
import hk.com.utils.LogUtils;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class NetworkController {

    private static boolean isConnected;
    private Context context;
    private static NetworkController networkController = null;
    private static ConnectionChecker connectionChecker = null;
    private ApiService apiService;


    private static final NetworkController ourInstance = new NetworkController();

    public static NetworkController getNetworkController() {
        if (networkController == null)
            networkController = new NetworkController();
        return networkController;
    }

    private NetworkController() {
        apiService = RetroClient.getApiService();
    }

    public void initNetworkController(Context context) {
        if (this.context != null)
            return;
        this.context = context;
        connectionChecker = new ConnectionChecker(context);
    }

    public boolean isConnected(boolean showDialog, final Context context) {
        isConnected = connectionChecker.isConnectionExist(context);
        if (!isConnected && showDialog) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtils.showNoInternetMsg(context);
                }
            });
        }
        return isConnected;
    }

    public void getWeathers(Activity activity, final OnNetworkCallListener onNetworkCallListener) {
        if (isConnected(true, activity)) {
            final Call<Weather> call = apiService.getWeatherInfo();
            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Response<Weather> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        DataController.getDataController().setFirstWeatherData(response.body());
                        onNetworkCallListener.onSuccess(RIdValues.GET_WEATHERS);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    onNetworkCallListener.onFailure(t.toString());
                }
            });
        } else {
            ViewController.getViewController().getMainActivity().setAdapter(false);
        }
    }

    public void getYourWeather(String countryName, Activity activity, final OnNetworkCallListener onNetworkCallListener) {
        if (isConnected(true, activity)) {
            Call<WeatherList> call = apiService.getFavoriteCitesWeatherList(countryName);
            call.enqueue(new Callback<WeatherList>() {
                @Override
                public void onResponse(Response<WeatherList> response, Retrofit retrofit) {
                    LogUtils.d(response.body().toString());
                    if (response.body() != null) {
                        DataController.getDataController().setYourWeather(response.body());
                        onNetworkCallListener.onSuccess(RIdValues.GET_YOU_WEATHER);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    LogUtils.d(t.toString());
                    onNetworkCallListener.onFailure(t.toString());
                }
            });
        }
    }

    public void getWeatherDaily(String countryName, Activity activity, final OnNetworkCallListener onNetworkCallListener) {
        if (isConnected(true, activity)) {
            Call<Weather> call = apiService.getWeatherDaily(countryName);
            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Response<Weather> response, Retrofit retrofit) {
                    LogUtils.d(response.body().toString());
                    if (response.body() != null) {
                        DataController.getDataController().setFewDaysData(response.body());
                        onNetworkCallListener.onSuccess(RIdValues.GET_FEW_DAYS_WEATHER);
                        LogUtils.d("Daily " + response.body().toString());
//                        onNetworkCallListener.onSuccess(RIdValues.GET_YOU_WEATHER);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    LogUtils.d("Daily " + t.toString());
                    onNetworkCallListener.onFailure(t.toString());
                }
            });
        }
    }
}
