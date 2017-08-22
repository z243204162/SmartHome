package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-12.
 */
@DynamoDBTable(tableName = "Rooms")
public class RoomData {
    private String mRoomID;
    private int mTemperature;
    private int mHumidity;
    private String mRoomName;
    private Map<String,String> mDeviceMap;


    @DynamoDBHashKey(attributeName = "RoomID")
    @DynamoDBIndexHashKey(attributeName = "RoomID", globalSecondaryIndexName = "RoomID")
    public String getRoomID() {
        return mRoomID;
    }

    public void setRoomID(String mRoomID) {
        this.mRoomID = mRoomID;
    }
    @DynamoDBAttribute(attributeName = "Temperature")
    public int getTemperature() {
        return mTemperature;
    }

    public void setTemperature(int mTemperature) {
        this.mTemperature = mTemperature;
    }
    @DynamoDBAttribute(attributeName = "Humidity")
    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }
    @DynamoDBAttribute(attributeName = "RoomName")
    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String mRoomName) {
        this.mRoomName = mRoomName;
    }
    @DynamoDBAttribute(attributeName = "DeviceMap")
    public Map<String,String>  getDeviceMap() {
        return mDeviceMap;
    }

    public void setDeviceMap(Map<String,String>  mDeviceMap) {
        this.mDeviceMap = mDeviceMap;
    }
}
