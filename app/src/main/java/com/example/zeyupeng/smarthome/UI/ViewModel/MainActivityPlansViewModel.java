package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.zeyupeng.smarthome.Model.Cloud.UploadTool;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;

import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-21.
 */

public class MainActivityPlansViewModel {
    private String viewModelID;
    private String deviceName;
    private String deviceCustomName;
    private String deviceID;
    private String planAction;
    private String hour;
    private String minute;
    private List<String> repeatDate;
    private MessageSender mMessageSender ;
    private MessageEditer mMessageEditer ;
    private StreamProgressPoster mProgressPoster;

    public MainActivityPlansViewModel() {
        mMessageSender = new MessageSender(ChannelNameGenerator.getControlChannel());
        mMessageEditer = new MessageEditer();
    }

    public String getViewModelID() {
        return viewModelID;
    }

    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getPlanAction() {
        return planAction;
    }

    public void setPlanAction(String planAction) {
        this.planAction = planAction;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public List<String> getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(List<String> repeatDate) {
        this.repeatDate = repeatDate;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCustomName() {
        return deviceCustomName;
    }

    public void setDeviceCustomName(String deviceCustomName) {
        this.deviceCustomName = deviceCustomName;
    }

    public void deletePlan(View view){

        postProgress(view.getContext()
                    ,PackageType.PACKAGE_TYPE_PLAN
                    ,PlanDataOnMobile.getInstance().getPlan(viewModelID).getPlanID());

        sendMessage(PackageType.PACKAGE_TYPE_PLAN
                    ,PlanDataOnMobile.getInstance().getPlan(viewModelID)
                    , MessageAction.REMOVE);
    }

    public void sendMessage(String packageType,Object object,String messageAction){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,messageAction));
    }

    public void postProgress(Context context, String packageType, String messageID){
        mProgressPoster = new StreamProgressPoster(context,packageType,messageID);
        mProgressPoster.startSteaming();

    }
}
