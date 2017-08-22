package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.StatusOfHub;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.SettingKeys;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zeyu peng on 2017-07-14.
 */

public class OnlineStatusCheckingService extends Service {
    private MessageEditer mMessageEditer;
    private MessageSender mMessageSender;
    private Timer mTimer;
    private int timeInterval;

    public OnlineStatusCheckingService() {
        mMessageEditer = new MessageEditer();
        mMessageSender = new MessageSender(ChannelNameGenerator.getQueryStatusChannel());
        mTimer = new Timer();

    }

    public void getSettings(){
        SharedPreferences sharedPref =this.getSharedPreferences(SettingKeys.SMART_HOME_SETTING,MODE_PRIVATE);
        timeInterval=sharedPref.getInt(SettingKeys.ONLINE_DETECTOR_TIME_INTERVAL,15);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getSettings();
        checkHubStatus();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        stopChecking();
        super.onDestroy();
    }

    public void checkHubStatus (){

        Log.i("settingggggggg","online status interval....."+timeInterval);
        this.mTimer.schedule(new TimerTask(){
            @Override
            public void run() {

                mMessageSender.sendRealTimeMessage(
                        mMessageEditer.getMessage(PackageType.PACKAGE_TYPE_HUB_STATUS
                                ,""
                                ,MessageAction.QUERY_HUB_STATUS));

                if(!HubStatusDataOnMobile.getInstance().isQueryReceived()){
                    HubStatusDataOnMobile.getInstance().setOnlineStatus(StatusOfHub.OFFLINE);
                }else{
                    HubStatusDataOnMobile.getInstance().setOnlineStatus(StatusOfHub.ONLINE);
                }
                HubStatusDataOnMobile.getInstance().setQueryReceived(false);
            }
        },0,timeInterval*1000);
    }

    public void stopChecking(){
        this.mTimer.cancel();
    }
}
