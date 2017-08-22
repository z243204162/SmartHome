package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeControl;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HomeDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
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

import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-30.
 */

public class AppToHubMessageHandler {
    private Gson mGson;

    public AppToHubMessageHandler() {
        this.mGson=new Gson();
    }

    public void handleMassageFromAPP(PNMessageResult message){

        Log.i("dddddddebug","message.getContent()..."+message.getMessage());
        PubNubMessage pubNubMessage =mGson.fromJson(message.getMessage(),PubNubMessage.class);
        Log.i("dddddddebug","pubNubMessage.getDataPackage()"+pubNubMessage.getDataPackage()+"pubNubMessage.getDataPackageType()"+pubNubMessage.getDataPackageType());


        switch (pubNubMessage.getDataPackageType()){
            case PackageType.PACKAGE_TYPE_DEVICE:
                DeviceDataPackage deviceDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),DeviceDataPackage.class);
                deviceDataPackageHandler(deviceDataPackage);
                break;
            case PackageType.PACKAGE_TYPE_ROOM:
                RoomDataPackage roomDataPackage =mGson.fromJson(pubNubMessage.getDataPackage(),RoomDataPackage.class);
                Room room =mGson.fromJson(roomDataPackage.getObjectJson(),Room.class);
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
            case PackageType.PACKAGE_TYPE_HUB_STATUS:
                HubStatusDataPackage hubStatusDataPackage=
                        mGson.fromJson(pubNubMessage.getDataPackage(),HubStatusDataPackage.class);
                hubStatusDataPackageHandeler(hubStatusDataPackage);
                break;
        }


    }



    public void deviceDataPackageHandler(DeviceDataPackage deviceDataPackage){
        //AbstractDevice device;
        Log.i("dddddddebug","+++++"+deviceDataPackage.getProductName()+deviceDataPackage.getObjectJson()+deviceDataPackage.getActionType());
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
                RoomDataOnHub.getInstance().getRoom(device.getLocationID()).addDevice(device);
                DeviceDataOnHub.getInstance().addDevice(device);
                break;
            case MessageAction.MODIFY:
                DeviceDataOnHub.getInstance().modifyDevice(device);
                break;
            case MessageAction.REMOVE:
                Log.i("pppppppplan","AppToHubMessageHandler.........before removal"+device.getLocationID());
                for(Map.Entry<String,String> entry:RoomDataOnHub.getInstance().getRoom(device.getLocationID()).getDeviceMap().entrySet()){
                    Log.i("pppppppplan","AppToHubMessageHandler.........before removal"+entry.getKey());
                }
                RoomDataOnHub.getInstance().getRoom(device.getLocationID()).removeDevice(device);
                for(Map.Entry<String,String> entry:RoomDataOnHub.getInstance().getRoom(device.getLocationID()).getDeviceMap().entrySet()){
                    Log.i("pppppppplan","AppToHubMessageHandler.........before removal"+entry.getKey());
                }
                DeviceDataOnHub.getInstance().removeDevice(device);
                break;
        }
    }





    public void roomDataPackageHandler(RoomDataPackage roomDataPackage){
        Room room = mGson.fromJson(roomDataPackage.getObjectJson(),Room.class);
        switch (roomDataPackage.getActionType()){
            case MessageAction.ADD:
                HomeDataOnHub.getInstance().getHome().addRoom(room);
                RoomDataOnHub.getInstance().addRoom(room);
                break;
            case MessageAction.MODIFY:
                RoomDataOnHub.getInstance().modifyRoom(room);
                break;
            case MessageAction.REMOVE:
                HomeDataOnHub.getInstance().getHome().removeRoom(room);
                RoomDataOnHub.getInstance().removeRoom(room);
                break;
        }
    }

    public void homeDataPackageHandler(HomeDataPackage homeDataPackage){
        Home home = mGson.fromJson(homeDataPackage.getObjectJson(),Home.class);
        switch (homeDataPackage.getActionType()){
            case MessageAction.MODIFY:
                HomeDataOnHub.getInstance().modifyHome(home);
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
                PlanDataOnHub.getInstance().addPlan(plan);
                break;
            case MessageAction.REMOVE:
                for(Map.Entry<String,Plan> entry:PlanDataOnHub.getInstance().getPlanDataMap().entrySet()){
                    Log.i("pppppppplan","AppToHubMessageHandler.........before removal"+entry.getKey());
                }
                PlanDataOnHub.getInstance().removePlan(plan);
                for(Map.Entry<String,Plan> entry:PlanDataOnHub.getInstance().getPlanDataMap().entrySet()){
                    Log.i("pppppppplan","AppToHubMessageHandler.........after removal"+entry.getKey());
                }
                break;
        }
    }

    public void hubStatusDataPackageHandeler(HubStatusDataPackage hubStatusDataPackage){
        switch (hubStatusDataPackage.getActionType()){
            case MessageAction.QUERY_HUB_STATUS:
                HubStatusDataOnMobile.getInstance().queryOnlineStatus();
                break;
        }
    }
}
