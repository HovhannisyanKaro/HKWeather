package hk.com.interfacies;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public interface OnNetworkCallListener {

    void onSuccess(int RId);

    void onFailure(String msg);
}
