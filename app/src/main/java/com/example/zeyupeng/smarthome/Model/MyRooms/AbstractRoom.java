package com.example.zeyupeng.smarthome.Model.MyRooms;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-12.
 */

public abstract class AbstractRoom {
    private String mRoomID;
    private int mTemperature;
    private int mHumidity;
    private String mRoomName;
    private Map<String,String> mDeviceMap = new HashMap<>();

    public String getRoomID() {
        return mRoomID;
    }

    public void setRoomID(String mRoomID) {
        this.mRoomID = mRoomID;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public void setTemperature(int mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String mRoomName) {
        this.mRoomName = mRoomName;
    }

    public void addDevice (AbstractDevice device){
        this.mDeviceMap.put(device.getProductID(),device.getProductName());
    }

    public void removeDevice(AbstractDevice device){
        this.mDeviceMap.remove(device.getProductID());
    }

    public Map<String,String>  getDeviceMap() {
        return mDeviceMap;
    }

    public void setDeviceMap(Map<String,String>  mDeviceMap) {
        this.mDeviceMap = mDeviceMap;
    }
}
