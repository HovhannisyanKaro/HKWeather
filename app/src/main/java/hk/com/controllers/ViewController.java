package hk.com.controllers;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.ref.WeakReference;

import hk.com.activities.SplashScreeenActivity;
import hk.com.activities.MainActivity;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class ViewController {
    private static ViewController viewController = null;

    public static ViewController getViewController() {
        if (viewController == null)
            viewController = new ViewController();
        return viewController;
    }

    private ViewController() {

    }

    //Activities
    private WeakReference<Activity> currentActivity;
    private WeakReference<SplashScreeenActivity> splashScreeenActivity;
    private WeakReference<MainActivity> mainActivity;


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = new WeakReference<>(mainActivity);
        currentActivity = new WeakReference<Activity>(this.mainActivity.get());
    }

    public void setSplashScreeenActivity(SplashScreeenActivity splashScreeenActivity) {
        this.splashScreeenActivity = new WeakReference<>(splashScreeenActivity);
        currentActivity = new WeakReference<Activity>(this.splashScreeenActivity.get());
    }

    public MainActivity getMainActivity() {
        return isREferenceNotNull(mainActivity) ? mainActivity.get() : null;
    }

    public SplashScreeenActivity getSplashScreenActivity() {
        return isREferenceNotNull(splashScreeenActivity) ? splashScreeenActivity.get() : null;
    }

    public Activity getCurrentActivity() {
        return isREferenceNotNull(currentActivity) ? currentActivity.get() : null;
    }

    private <T> boolean isREferenceNotNull(WeakReference<T> weakReference) {
        return weakReference != null && weakReference.get() != null;
    }

    public void replaceFragment(FragmentManager fm, @IdRes int containerId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
