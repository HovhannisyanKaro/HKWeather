package hk.com.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import hk.com.hkweather.R;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class DialogUtils {

    public static void showNoInternetMsg(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.check_internet))
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
