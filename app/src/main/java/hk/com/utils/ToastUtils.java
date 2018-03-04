package hk.com.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Hovhannisyan.Karo on 04.11.2017.
 */

public class ToastUtils {
    private final static boolean isDebug = true;
    
    public static void t(Context context, String msg){
        if (isDebug){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
