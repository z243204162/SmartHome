package com.example.zeyupeng.smarthome.PubNub.Message;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyHome.AbstractHome;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.PubNub.PushNotification.NotificationPayload;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-29.
 */

public class MessageEditer {


    private Gson mGson;


    public MessageEditer() {
        this.mGson=new Gson();
    }


    public String getDeviceDataPackage(Object object,String actionType){
        AbstractDevice abstractDevice =(AbstractDevice)object;
        DeviceDataPackage dataPackage = new DeviceDataPackage();
        dataPackage.setProductName(abstractDevice.getProductName());
        dataPackage.setObjectJson(mGson.toJson(abstractDevice));
        dataPackage.setActionType(actionType);
        return mGson.toJson(dataPackage);
    }

    public String getRoomDataPackage(Object object,String actionType){
        AbstractRoom abstractRoom = (AbstractRoom) object;
        RoomDataPackage dataPackage = new RoomDataPackage();
        dataPackage.setObjectJson(mGson.toJson(abstractRoom));
        dataPackage.setActionType(actionType);
        return mGson.toJson(dataPackage);
    }

    public String getHomeDataPackage(Object object,String actionType){
        AbstractHome abstractHome = (AbstractHome) object;
        HomeDataPackage dataPackage = new HomeDataPackage();
        dataPackage.setObjectJson(mGson.toJson(abstractHome));
        dataPackage.setActionType(actionType);
        return mGson.toJson(dataPackage);
    }

    public String getPlanDataPackage(Object object,String actionType){
        Plan plan = (Plan) object;
        PlanDataPackage dataPackage = new PlanDataPackage();
        dataPackage.setObjectJson(mGson.toJson(plan));
        dataPackage.setActionType(actionType);
        return mGson.toJson(dataPackage);
    }

    public String getHubStatusDataPackage(Object object,String actionType){
        String hubStatus = (String) object;
        HubStatusDataPackage dataPackage = new HubStatusDataPackage();
        dataPackage.setObjectJson(hubStatus);
        dataPackage.setActionType(actionType);
        return mGson.toJson(dataPackage);
    }

    public Map<String, String> getMessage(String PACKAGE_TYPE,Object object,String actionType){
        Map<String,String> message = new HashMap<>();

        switch (PACKAGE_TYPE){
            case PackageType.PACKAGE_TYPE_DEVICE:
                message.put(MessageComponent.KEY_DATA_PACKAGE_TYPE, PackageType.PACKAGE_TYPE_DEVICE);
                message.put(MessageComponent.KEY_DATA_PACKAGE, getDeviceDataPackage(object, actionType));
                return message;
            case PackageType.PACKAGE_TYPE_ROOM:
                message.put(MessageComponent.KEY_DATA_PACKAGE_TYPE,PackageType.PACKAGE_TYPE_ROOM);
                message.put(MessageComponent.KEY_DATA_PACKAGE,getRoomDataPackage(object,actionType));
                return message;
            case PackageType.PACKAGE_TYPE_HOME:
                message.put(MessageComponent.KEY_DATA_PACKAGE_TYPE,PackageType.PACKAGE_TYPE_HOME);
                message.put(MessageComponent.KEY_DATA_PACKAGE,getHomeDataPackage(object,actionType));
                return message;
            case PackageType.PACKAGE_TYPE_PLAN:
                message.put(MessageComponent.KEY_DATA_PACKAGE_TYPE,PackageType.PACKAGE_TYPE_PLAN);
                message.put(MessageComponent.KEY_DATA_PACKAGE,getPlanDataPackage(object,actionType));
                return message;
            case PackageType.PACKAGE_TYPE_HUB_STATUS:
                message.put(MessageComponent.KEY_DATA_PACKAGE_TYPE,PackageType.PACKAGE_TYPE_HUB_STATUS);
                message.put(MessageComponent.KEY_DATA_PACKAGE,getHubStatusDataPackage(object,actionType));
                return message;
            default:
                return null;
        }

    }

    public Map<String,Object> getNotificationMessage(String title,String content){
        Map<String,Object> message = new HashMap<>();
        NotificationPayload payload=new NotificationPayload();
        payload.getData().setContent(content);
        payload.getData().setTitle("A message from "+title+":");
        message.put(MessageComponent.PUSH_NOTIFICATION,payload);
        return message;
    }

}
