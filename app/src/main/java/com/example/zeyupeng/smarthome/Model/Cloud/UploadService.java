package com.example.zeyupeng.smarthome.Model.Cloud;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.zeyupeng.smarthome.SettingKeys;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zeyu peng on 2017-07-20.
 */

public class UploadService extends Service {
    private Timer mTimer;
    private UploadTool mUploadTool;
    private int timeInterval;

    public UploadService() {
        mTimer = new Timer();
        mUploadTool = new UploadTool();

    }
    public void getSettings(){
        SharedPreferences sharedPref =this.getSharedPreferences(SettingKeys.SMART_HOME_SETTING,MODE_PRIVATE);
        timeInterval=sharedPref.getInt(SettingKeys.UPLOADOR_TIME_INTERVAL,5);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("uuuuuuupload service","service is called");
        getSettings();
        uploadData ();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        stopUpload();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void uploadData (){

        Log.i("settingggggggg","upload interval....."+timeInterval);
        this.mTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                mUploadTool.uploadData();
                Log.i("uuuuuuupload service","service is running");
            }
        },0,timeInterval*60*1000);
    }

    public void stopUpload(){
        this.mTimer.cancel();
    }
}
