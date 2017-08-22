package com.example.zeyupeng.smarthome.Model.MyPlans;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-07-07.
 */
@DynamoDBDocument
public class Plan {
    private String planID;
    private String deviceID;
    private String planAction;
    private int hour;
    private int minute;
    private List<String> repeatDate=new ArrayList<>();

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }


    public List<String> getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(List<String> repeatDate) {
        this.repeatDate = repeatDate;
    }



}
