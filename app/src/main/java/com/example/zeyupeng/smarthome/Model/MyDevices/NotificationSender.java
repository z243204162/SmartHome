package com.example.zeyupeng.smarthome.Model.MyDevices;

/**
 * Created by zeyu peng on 2017-07-20.
 */

public interface NotificationSender {
    String createNotification();
    void addNotificationSenderToControlerType();

    boolean getNotificationPermit();
    void setNotificationPermit(boolean notificationPermit);
}

