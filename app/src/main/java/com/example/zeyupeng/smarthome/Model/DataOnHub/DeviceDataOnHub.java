package com.example.zeyupeng.smarthome.Model.DataOnHub;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObservableSubject;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-29.
 */

public class DeviceDataOnHub implements ObservableSubject{
    private static volatile DeviceDataOnHub ourInstance;
    private Map<String,AbstractDevice> mDeviceDataMap;
    private List<Observer> mObserverList;

    public static DeviceDataOnHub getInstance() {
        if (ourInstance == null) {
            synchronized (DeviceDataOnHub.class) {
                if (ourInstance == null) {
                    ourInstance = new DeviceDataOnHub();
                }
            }
        }
        return ourInstance;
    }

    private DeviceDataOnHub() {
        this.mDeviceDataMap = new HashMap<>();
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","devicedata constructor");
    }

    public void refreshDevice (AbstractDevice device){
        Log.i("hhhhhhub","device"+device.getProductName());
        this.notifyObservers(ObserverActions.REFRESH_DEVICE,device);
    }
    public void modifyDevice (AbstractDevice device){
        this.mDeviceDataMap.put(device.getProductID(),device);
        this.notifyObservers(ObserverActions.MODIFY_DEVICE,device);
        this.notifyObservers(ObserverActions.DEVICE_SEND_NOTIFICATION,device);
    }

    public void addDevice (AbstractDevice device){
        this.mDeviceDataMap.put(device.getProductID(),device);
        Log.i("hhhhhhub","device"+device.getProductName());
        this.notifyObservers(ObserverActions.ADD_DEVICE,device);
    }


    public void removeDevice(AbstractDevice device){
        this.mDeviceDataMap.remove(device.getProductID());
        this.notifyObservers(ObserverActions.REMOVE_DEVICE,device);
    }

    public AbstractDevice getDevice(String DeviceID){
        return this.mDeviceDataMap.get(DeviceID);
    }

    public Map<String, AbstractDevice> getDeviceDataMap() {
        return this.mDeviceDataMap;
    }

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
    public void notifyObservers(ObserverActions action,Object object) {
        for(int i=0;i<this.mObserverList.size();i++){
            Observer observer = (Observer) this.mObserverList.get(i);
            observer.update(action,object);
        }
    }
}
