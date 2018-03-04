package hk.com.controllers;

import android.app.Activity;
import android.content.Context;

import hk.com.api.ApiService;
import hk.com.api.RIdValues;
import hk.com.api.RetroClient;
import hk.com.entities.Weather;
import hk.com.interfacies.OnNetworkCallListener;
import hk.com.utils.ConnectionChecker;
import hk.com.utils.DialogUtils;
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
}
