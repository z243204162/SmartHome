package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.StatusOfHub;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class RefreshToken {

    public static final String REFRESH_TOKEN="REFRESH_TOKEN";
}
