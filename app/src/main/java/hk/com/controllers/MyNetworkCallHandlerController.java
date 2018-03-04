package hk.com.controllers;


import hk.com.api.RIdValues;
import hk.com.interfacies.OnNetworkCallListener;
import hk.com.utils.LogUtils;
import hk.com.utils.ToastUtils;

/**
 * Created by Hovhannisyan.Karo on 04.13.2018.
 */

public class MyNetworkCallHandlerController implements OnNetworkCallListener {

    private static MyNetworkCallHandlerController myNetworkCallHandlerController = null;

    private MyNetworkCallHandlerController() {
    }

    public static MyNetworkCallHandlerController getMyNetworkCallHandlerController() {
        if (myNetworkCallHandlerController == null) {
            myNetworkCallHandlerController = new MyNetworkCallHandlerController();
        }

        return myNetworkCallHandlerController;
    }

    @Override
    public void onSuccess(int RId) {
        switch (RId) {
            case RIdValues.GET_WEATHERS:
                ViewController.getViewController().getMainActivity().setAdapter(true);
                break;

        }
    }

    @Override
    public void onFailure(String errorMsg) {
        ToastUtils.t(ViewController.getViewController().getCurrentActivity(), errorMsg);
        LogUtils.e(errorMsg);

    }
}
