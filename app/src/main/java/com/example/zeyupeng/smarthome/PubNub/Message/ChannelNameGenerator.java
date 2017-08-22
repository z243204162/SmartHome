package com.example.zeyupeng.smarthome.PubNub.Message;

import com.amazonaws.mobile.AWSMobileClient;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class ChannelNameGenerator {
    public static final String QUERY_STATUS_CHANNEL="QUERY_STATUS";
    public static final String ANSWER_STATUS_CHANNEL="ANSWER_STATUS";
    public static final String CONTROL_CHANNEL="CONTROL";
    public static final String MONITOR_CHANNEL="MONITOR";
    public static final String PUSH_NOTIFICATION_CHANNEL="NOTIFICATION";


    public static String getControlChannel() {
        return CONTROL_CHANNEL+AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID();
    }

    public static String getMonitorChannel() {
        return MONITOR_CHANNEL+AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID();
    }

    public static String getQueryStatusChannel() {
        return QUERY_STATUS_CHANNEL+AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID();
    }

    public static String getAnswerStatusChannel() {
        return ANSWER_STATUS_CHANNEL+AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID();
    }

    public static String getNotificationChannel(){
        return PUSH_NOTIFICATION_CHANNEL+AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID();
    }
}
