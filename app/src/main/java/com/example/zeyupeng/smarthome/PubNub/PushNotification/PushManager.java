package com.example.zeyupeng.smarthome.PubNub.PushNotification;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;

/**
 * Created by zeyu peng on 2017-07-20.
 */

public class PushManager implements Observer{
    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;


    public PushManager() {
        mMessageSender = new MessageSender(ChannelNameGenerator.getNotificationChannel());
        mMessageEditer = new MessageEditer();
    }

    @Override
    public void update(ObserverActions action, Object object) {

        switch (action){
            case DEVICE_SEND_NOTIFICATION:
                AbstractDevice device=(AbstractDevice)object;
                if(device.getControlerType().contains(ControlerType.NOTIFICATION_SENDER)){
                    NotificationSender sender=(NotificationSender) object;
                    if(sender.getNotificationPermit()){
                        mMessageSender.pushNotification(
                                mMessageEditer.getNotificationMessage(
                                        device.getProductName()
                                        ,sender.createNotification()));
                        Log.i("nnnnnnnnnnotification",sender.createNotification()+"");
                    }
                }
                break;
            case ROOM_SEND_NOTIFICATION:
                Log.i("rooooooooooooom","notification");
                Room room =(Room)object;
                if(room.getTemperature()>40)
                    mMessageSender.pushNotification(
                            mMessageEditer.getNotificationMessage(
                                    room.getRoomName()
                                    ,"Alarm! High temperture! "+room.getTemperature()+" °C."));

                break;
        }
/*
        AbstractDevice device=(AbstractDevice)object;
        if(device.getControlerType().contains(ControlerType.NOTIFICATION_SENDER)){
            switch (action){
                case DEVICE_SEND_NOTIFICATION:
                    NotificationSender sender=(NotificationSender) object;
                    if(sender.getNotificationPermit()){
                        mMessageSender.pushNotification(
                                mMessageEditer.getNotificationMessage(
                                        device.getProductName()
                                        ,sender.createNotification()));
                        Log.i("nnnnnnnnnnotification",sender.createNotification()+"");
                    }
                    break;
            }
        }
*/
    }
}
