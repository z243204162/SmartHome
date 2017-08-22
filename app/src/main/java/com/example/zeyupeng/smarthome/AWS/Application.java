package com.example.zeyupeng.smarthome.AWS;

/**
 * Created by zeyu peng on 2017-05-22.
 */

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.amazonaws.mobile.AWSMobileClient;
import com.example.zeyupeng.smarthome.Initializer;

/**
 * Application class responsible for initializing singletons and other common components.
 */
public class Application extends MultiDexApplication {

    private final static String LOG_TAG = Application.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i("Application.onCreate", "Application.onCreate - Initializing application...");
        super.onCreate();
        initializeApplication();
        Log.i("Application.onCreate", "Application.onCreate - Application initialized OK");
    }

    private void initializeApplication() {

        // Initialize the AWS Mobile Client
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        // ... Put any application-specific initialization logic here ...

    }
}
