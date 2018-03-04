package hk.com.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hovhannisyan.Karo on 04.11.2017.
 */

public class ConnectionChecker {
    private Context context;

    public ConnectionChecker(Context context){
        this.context = context;
    }

    public boolean isConnectionExist(Context context){
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()){
            return true;
        }else{
            return false;
        }

    }
}
