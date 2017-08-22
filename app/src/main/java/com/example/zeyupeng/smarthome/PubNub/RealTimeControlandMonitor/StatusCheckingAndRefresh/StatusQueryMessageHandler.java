package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh;

import android.os.AsyncTask;
import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HubStatusDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.HubStatusDataPackage;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.Message.PubNubMessage;
import com.google.gson.Gson;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class StatusQueryMessageHandler {
    private Gson mGson;

    public StatusQueryMessageHandler() {
        this.mGson=new Gson();
    }

    public void handleMassageFromAPP(PNMessageResult message) {

        Log.i("cccccccccccallback", "message.getContent()..." + message.getMessage());
        PubNubMessage pubNubMessage = mGson.fromJson(message.getMessage(), PubNubMessage.class);
        Log.i("cccccccccccallback", "pubNubMessage.getDataPackage()" + pubNubMessage.getDataPackage() + "pubNubMessage.getDataPackageType()" + pubNubMessage.getDataPackageType());

        switch (pubNubMessage.getDataPackageType()) {
            case PackageType.PACKAGE_TYPE_HUB_STATUS:
                HubStatusDataPackage hubStatusDataPackage=
                        mGson.fromJson(pubNubMessage.getDataPackage(),HubStatusDataPackage.class);
                hubStatusDataPackageHandeler(hubStatusDataPackage);
                break;
        }
    }
    public void hubStatusDataPackageHandeler(HubStatusDataPackage hubStatusDataPackage){
        switch (hubStatusDataPackage.getActionType()){
            case MessageAction.QUERY_HUB_STATUS:
                HubStatusDataOnHub.getInstance().queryOnlineStatus();
                break;
            case MessageAction.REFRESH_MOBILE_STATUS:
                Log.i("rrrrrrrrrefresh","StatusQueryMessageHandler   MessageAction.REFRESH_MOBILE_STATUS  is called");

                Refresher refresher = new Refresher();
                refresher.execute();


                //HubStatusDataOnHub.getInstance().refreshMoblieStatus();
                break;

        }
    }

    class Refresher extends AsyncTask<Void,Void,Void>{




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            for(Map.Entry<String,AbstractDevice> deviceEntry: DeviceDataOnHub.getInstance().getDeviceDataMap().entrySet()){
                DeviceDataOnHub.getInstance().refreshDevice(deviceEntry.getValue());
            }
            for(Map.Entry<String,AbstractRoom> roomEntry: RoomDataOnHub.getInstance().getRoomDataMap().entrySet()){
                RoomDataOnHub.getInstance().refreshyRoom(roomEntry.getValue());
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("super debug","refresher is called...post exe....");
            HubStatusDataOnHub.getInstance().refreshMoblieStatus();
            super.onPostExecute(aVoid);
        }
    }
}
