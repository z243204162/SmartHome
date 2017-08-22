package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.PubNub.Message.HubStatusDataPackage;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.Message.PubNubMessage;
import com.google.gson.Gson;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class StatusAnswerMessageHandler {
    private Gson mGson;

    public StatusAnswerMessageHandler() {
        this.mGson=new Gson();
    }

    public void handleMassageFromHub(PNMessageResult message) {

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
                HubStatusDataOnMobile.getInstance().setQueryReceived(true);
                break;
            case MessageAction.REFRESH_MOBILE_STATUS:
                HubStatusDataOnMobile.getInstance().mobileStatusRefreshed();
                break;
        }
    }
}
