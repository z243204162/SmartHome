package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeControl;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.Cloud.UploadTool;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.AbstractFeedbackHandler;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;

/**
 * Created by zeyu peng on 2017-06-30.
 */

public class AppToHubMessageFeedbackHandler extends AbstractFeedbackHandler {
    public AppToHubMessageFeedbackHandler(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action){
            case ADD_DEVICE:
                uploadData();
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,object, MessageAction.ADD);
                break;
            case MODIFY_DEVICE:
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,object, MessageAction.MODIFY);
                break;
            case REMOVE_DEVICE:
                uploadData();
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,object, MessageAction.REMOVE);
                break;
            case REFRESH_DEVICE:
                AbstractDevice device = (AbstractDevice) object;
                Log.i("super debug"
                        ,"ProductName......."+device.getProductName()
                                +"CustomName......."+device.getCustomName()
                                +"ProductID......."+device.getProductID());
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,object, MessageAction.REFRESH);
                break;

            case REMOVE_ROOM:
                uploadData();
                sendMessage(PackageType.PACKAGE_TYPE_ROOM,object,MessageAction.REMOVE);
                break;
            case ADD_ROOM:
                uploadData();
                sendMessage(PackageType.PACKAGE_TYPE_ROOM,object,MessageAction.ADD);
                break;
            case MODIFY_ROOM:
                Log.i("rrrrrrrrrefresh","AppToHubMessageFeedbackHandler MODIFY_ROOM is called.......");
                sendMessage(PackageType.PACKAGE_TYPE_ROOM,object,MessageAction.MODIFY);
                break;
            case REFRESH_ROOM:
                Room room = (Room) object;
                Log.i("super debug"
                        ,"RoomName......."+room.getRoomName()
                                +"RoomID......."+room.getRoomID());
                sendMessage(PackageType.PACKAGE_TYPE_ROOM,object, MessageAction.REFRESH);
                break;

            case ADD_PLAN:
                uploadData();
                sendMessage(PackageType.PACKAGE_TYPE_PLAN,object,MessageAction.ADD);
                break;
            case REMOVE_PLAN:
                uploadData();
                sendMessage(PackageType.PACKAGE_TYPE_PLAN,object,MessageAction.REMOVE);
                break;


        }
    }

    public void uploadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                UploadTool uploadTool = new UploadTool();
                uploadTool.uploadData();
            }
        }).start();
    }
/*
    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;

    public AppToHubMessageFeedbackHandler(MessageSender messageSender) {
        this.mMessageSender=messageSender;
        this.mMessageEditer=new MessageEditer();
    }


    @Override
    public void update(ObserverActions action, Object object) {
        switch (action){
            case MODIFY_DEVICE:
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,object,MessageAction.MODIFY);
                break;
            case QUERY_HUB_STATUS:
                sendMessage(PackageType.PACKAGE_TYPE_HUB_STATUS,object,MessageAction.QUERY_HUB_STATUS);
                break;
            case SET_HUB_STATUS:
                sendMessage(PackageType.PACKAGE_TYPE_HUB_STATUS,object,MessageAction.CHANGE_HUB_STATUS);
                break;


        }
    }

    public void sendMessage(String packageType, Object object,String actionType){
        mMessageSender.publish(mMessageEditer.getContent(packageType, object,actionType));
    }
    */
}
