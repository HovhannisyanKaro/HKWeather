package hk.com.utils;

import android.util.Log;

/**
 * Created by Hovhannisyan.Karo on 04.13.2018.
 */

public class LogUtils {

    private static final String TAG = "HK_LOG";
    private static final boolean isDebug = true;

    public static void d(String msg){
        if (isDebug)
            Log.d(TAG , msg);
    }

    public static void d(String mTag , String msg){
        if (isDebug)
            Log.d(TAG + "_" + mTag , msg);
    }


    public static void e(String errMsg){
        if (isDebug)
            Log.e(TAG , errMsg);
    }

    public static void e(String mTag , String errMsg){
        if (isDebug)
            Log.e(TAG + "_" + mTag , errMsg);
    }
}
