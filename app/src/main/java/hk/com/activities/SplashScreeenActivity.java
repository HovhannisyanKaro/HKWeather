package hk.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import hk.com.controllers.NetworkController;
import hk.com.controllers.ViewController;
import hk.com.hkweather.R;
import hk.com.utils.ActivityUtils;

public class SplashScreeenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.setFullScreen(this);
        setContentView(R.layout.activity_splash_screeen);
        initControllers();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, SPLASH_TIME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewController.getViewController().setSplashScreeenActivity(this);
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initControllers() {
        NetworkController.getNetworkController().initNetworkController(getApplicationContext());
    }
}
