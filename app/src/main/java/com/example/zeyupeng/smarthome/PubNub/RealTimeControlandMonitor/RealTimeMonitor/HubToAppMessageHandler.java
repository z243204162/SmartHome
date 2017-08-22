package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeMonitor;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HomeDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObservableSubject;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.GarageDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.NFCDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.Oven;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.RangeHood;
import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.AdjustableLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;
import com.example.zeyupeng.smarthome.Model.MyDevices.Shading.Curtain;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;
import com.example.zeyupeng.smarthome.PubNub.Message.DeviceDataPackage;
import com.example.zeyupeng.smarthome.PubNub.Message.HomeDataPackage;
import com.example.zeyupeng.smarthome.PubNub.Message.HubStatusDataPackage;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.Message.PlanDataPackage;
import com.example.zeyupeng.smarthome.PubNub.Message.PubNubMessage;
import com.example.zeyupeng.smarthome.PubNub.Message.RoomDataPackage;
import com.google.gson.Gson;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-04.
 */

public class HubToAppMessageHandler implements ObservableSubject{
    private Gson mGson;
    private List<Observer> mObserverList;

    public HubToAppMessageHandler() {
        this.mGson=new Gson();
        this.mObserverList=new ArrayList<>();
    }

    public void handleMassageFromHub(PNMessageResult message){

        Log.i("cccccccccccallback","message.getContent()..."+message.getMessage());
        PubNubMessage pubNubMessage =mGson.fromJson(message.getMessage(),PubNubMessage.class);
        Log.i("cccccccccccallback","pubNubMessage.getDataPackage()"+pubNubMessage.getDataPackage()+"pubNubMessage.getDataPackageType()"+pubNubMessage.getDataPackageType());


        switch (pubNubMessage.getDataPackageType()){
            case PackageType.PACKAGE_TYPE_DEVICE:
                DeviceDataPackage deviceDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),DeviceDataPackage.class);
                deviceDataPackageHandler(deviceDataPackage);
                break;
            case PackageType.PACKAGE_TYPE_ROOM:
                RoomDataPackage roomDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),RoomDataPackage.class);
                //Room room =mGson.fromJson(roomDataPackage.getObjectJson(),Room.class);
                roomDataPackageHandler(roomDataPackage);
                break;
            case PackageType.PACKAGE_TYPE_HOME:
                HomeDataPackage homeDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),HomeDataPackage.class);
                homeDataPackageHandler(homeDataPackage);
                break;
            case PackageType.PACKAGE_TYPE_PLAN:
                PlanDataPackage planDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),PlanDataPackage.class);
                planDataPackageHandeler(planDataPackage);
                break;
            /*
            case PackageType.PACKAGE_TYPE_HUB_STATUS:
                HubStatusDataPackage hubStatusDataPackage =
                        mGson.fromJson(pubNubMessage.getDataPackage(),HubStatusDataPackage.class);
                hubStatusDataPackageHandeler(hubStatusDataPackage);
                break;
                */
        }
/*
        DeviceDataPackage deviceDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),DeviceDataPackage.class);

        Log.i("cccccccccccallback","deviceDataPackage.getProductName()"+ deviceDataPackage.getProductName());
        ColorLight colorLight =mGson.fromJson(deviceDataPackage.getObjectJson(),ColorLight.class);

        Log.i("cccccccccccallback",colorLight.getMode());
        */
    }

    public void deviceDataPackageHandler(DeviceDataPackage deviceDataPackage){
        //Log.i("hhhhhhhhhhhh",deviceDataPackage.getProductName());
        switch (deviceDataPackage.getProductName()){
            case ProductName.COLOR_LIGHT:
                ColorLight colorLight =mGson.fromJson(deviceDataPackage.getObjectJson(),ColorLight.class);
                handleDevice(deviceDataPackage.getActionType(),colorLight);
                break;
            case ProductName.ADJUSTABLE_LIGHT:
                AdjustableLight adjustableLight =mGson.fromJson(deviceDataPackage.getObjectJson(),AdjustableLight.class);
                handleDevice(deviceDataPackage.getActionType(),adjustableLight);
                break;
            case ProductName.NFC_DOOR:
                NFCDoor nfcDoor =mGson.fromJson(deviceDataPackage.getObjectJson(),NFCDoor.class);
                handleDevice(deviceDataPackage.getActionType(),nfcDoor);
                break;
            case ProductName.GARAGE_DOOR:
                GarageDoor garageDoor =mGson.fromJson(deviceDataPackage.getObjectJson(),GarageDoor.class);
                handleDevice(deviceDataPackage.getActionType(),garageDoor);
                break;
            case ProductName.OVEN:
                Oven oven =mGson.fromJson(deviceDataPackage.getObjectJson(),Oven.class);
                handleDevice(deviceDataPackage.getActionType(),oven);
                break;
            case ProductName.RANGEHOOD:
                RangeHood rangeHood =mGson.fromJson(deviceDataPackage.getObjectJson(),RangeHood.class);
                handleDevice(deviceDataPackage.getActionType(),rangeHood);
                break;
            case ProductName.AC:
                AC ac =mGson.fromJson(deviceDataPackage.getObjectJson(),AC.class);
                handleDevice(deviceDataPackage.getActionType(),ac);
                break;
            case ProductName.CURTAIN:
                Curtain curtain =mGson.fromJson(deviceDataPackage.getObjectJson(),Curtain.class);
                handleDevice(deviceDataPackage.getActionType(),curtain);
                break;
        }

    }

    public void handleDevice(String messageActionType,AbstractDevice device){
        switch (messageActionType){
            case MessageAction.ADD:
                RoomDataOnMobile.getInstance().getRoom(device.getLocationID()).addDevice(device);
                DeviceDataOnMobile.getInstance().addDevice(device);

                //DeviceDataOnHub.getInstance().addDevice(device);
                break;
            case MessageAction.MODIFY:
                DeviceDataOnMobile.getInstance().modifyDevice(device);


                //DeviceDataOnHub.getInstance().modifyDevice(device);
                break;
            case MessageAction.REMOVE:
                RoomDataOnMobile.getInstance().getRoom(device.getLocationID()).removeDevice(device);
                DeviceDataOnMobile.getInstance().removeDevice(device);

                //RoomDataOnMobile.getInstance().getRoom(device.getLocationID()).removeDevice(device);
                //DeviceDataOnMobile.getInstance().removeDevice(device);
                break;
            case MessageAction.REFRESH:
                DeviceDataOnMobile.getInstance().refreshDevice(device);

                //DeviceDataOnMobile.getInstance().refreshDevice(device);
                break;
        }
    }

    public void roomDataPackageHandler(RoomDataPackage roomDataPackage){

        Room room = mGson.fromJson(roomDataPackage.getObjectJson(),Room.class);
        Log.i("rrrrrrrrrefresh","HubToAppMessageHandler roomDataPackageHandler is called.......");
        switch (roomDataPackage.getActionType()){
            case MessageAction.ADD:
                HomeDataOnMobile.getInstance().getHome().addRoom(room);
                RoomDataOnMobile.getInstance().addRoom(room);
                break;
            case MessageAction.MODIFY:
                Log.i("rrrrrrrrrefresh","HubToAppMessageHandler roomDataPackageHandler MODIFY_ROOM is called.......");
                //DeviceDataOnHub.getInstance().removeDevice(colorLight.getProductID());
                RoomDataOnMobile.getInstance().modifyRoom(room);
                break;
            case MessageAction.REMOVE:
                HomeDataOnMobile.getInstance().getHome().removeRoom(room);
                RoomDataOnMobile.getInstance().removeRoom(room);
                break;
            case MessageAction.REFRESH:
                Log.i("rrrrrrrrrefresh","HubToAppMessageHandler roomDataPackageHandler MODIFY_ROOM is called.......");
                //DeviceDataOnHub.getInstance().removeDevice(colorLight.getProductID());
                RoomDataOnMobile.getInstance().modifyRoom(room);
                break;
        }
    }

    public void homeDataPackageHandler(HomeDataPackage homeDataPackage){
        Home home = mGson.fromJson(homeDataPackage.getObjectJson(),Home.class);
        switch (homeDataPackage.getActionType()){
            case MessageAction.MODIFY:
                HomeDataOnMobile.getInstance().modifyHome(home);
                break;
            case MessageAction.REMOVE:
                //DeviceDataOnHub.getInstance().removeDevice(colorLight.getProductID());
                break;
        }
    }

    public void planDataPackageHandeler(PlanDataPackage planDataPackage){
        Plan plan = mGson.fromJson(planDataPackage.getObjectJson(),Plan.class);
        switch (planDataPackage.getActionType()){
            case MessageAction.ADD:
                PlanDataOnMobile.getInstance().addPlan(plan);
                break;
            case MessageAction.REMOVE:
                for(Map.Entry<String,Plan> entry:PlanDataOnMobile.getInstance().getPlanDataMap().entrySet()){
                    Log.i("pppppppplan","HubToAppMessageHandler.........before removal"+entry.getKey());
                }
                PlanDataOnMobile.getInstance().removePlan(plan);
                for(Map.Entry<String,Plan> entry:PlanDataOnMobile.getInstance().getPlanDataMap().entrySet()){
                    Log.i("pppppppplan","HubToAppMessageHandler.........after removal"+entry.getKey());
                }
                break;
        }
    }
/*
    public void hubStatusDataPackageHandeler(HubStatusDataPackage hubStatusDataPackage){
        switch (hubStatusDataPackage.getActionType()){
            case MessageAction.QUERY_HUB_STATUS:
                HubStatusDataOnMobile.getInstance().setOnlineStatus(hubStatusDataPackage.getObjectJson());
                notifyObservers(ObserverActions.HUB_STATUS_QUERY_RECEIVED,"");
                break;
            case MessageAction.CHANGE_HUB_STATUS:
                HubStatusDataOnMobile.getInstance().setOnlineStatus(hubStatusDataPackage.getObjectJson());
                break;
        }
    }
*/
    @Override
    public void registerObserver(Observer observer) {
        this.mObserverList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i =this.mObserverList.indexOf(observer);
        if(i>=0){
            this.mObserverList.remove(i);
        }
    }

    @Override
    public void notifyObservers(ObserverActions action, Object object) {
        for(int i=0;i<this.mObserverList.size();i++){
            Observer observer = (Observer) this.mObserverList.get(i);
            observer.update(action,object);
        }
    }
}
