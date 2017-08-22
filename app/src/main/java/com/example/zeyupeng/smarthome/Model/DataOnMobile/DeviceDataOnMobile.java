package com.example.zeyupeng.smarthome.Model.DataOnMobile;

import android.util.Log;

import com.amazonaws.models.nosql.ColorLightData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.View.IntentTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-23.
 */

public class DeviceDataOnMobile implements ObservableSubject {
    private static volatile DeviceDataOnMobile ourInstance;
    private Map<String,AbstractDevice> mDeviceDataMap;
    private List<Observer> mObserverList;

    public static DeviceDataOnMobile getInstance() {
        if (ourInstance == null) {
            synchronized (DeviceDataOnMobile.class) {
                if (ourInstance == null) {
                    ourInstance = new DeviceDataOnMobile();
                }
            }
        }
        return ourInstance;
    }

    private DeviceDataOnMobile() {
        this.mDeviceDataMap = new HashMap<>();
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","devicedata constructor");
    }

    public void addDevice (AbstractDevice device){
        this.mDeviceDataMap.put(device.getProductID(),device);
        this.notifyObservers(ObserverActions.ADD_DEVICE,device);
    }
    public void modifyDevice (AbstractDevice device){
        this.mDeviceDataMap.put(device.getProductID(),device);
        this.notifyObservers(ObserverActions.MODIFY_DEVICE,device);
    }

    public void refreshDevice (AbstractDevice device){
        this.mDeviceDataMap.put(device.getProductID(),device);
        this.notifyObservers(ObserverActions.REFRESH_DEVICE,device);
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

        for(Observer observer1:this.mObserverList){
            int i=1;
            Log.i("streamposter","DeviceDataOnMobile register Observer....."+i+"........"+observer1);
            i++;
        }

    }

    @Override
    public void removeObserver(Observer observer) {
        Log.i("streamposter","DeviceDataOnMobile before remove observer......"+observer+"....now the list size is ..."+mObserverList.size());
        int i =this.mObserverList.indexOf(observer);
        if(i>=0){
            this.mObserverList.remove(i);
        }
        for(Observer observer1:this.mObserverList){
            int k=1;
            Log.i("streamposter","DeviceDataOnMobile remain Observer....."+k+"........"+observer1);
            k++;
        }

    }

    @Override
    public void notifyObservers(ObserverActions action,Object object) {
        for(int i=0;i<this.mObserverList.size();i++){
            Observer observer = (Observer) this.mObserverList.get(i);
            observer.update(action,object);
            int k=1;
            Log.i("streamposter","DeviceDataOnMobile notifyObservers....."+k+"........"+observer+".....to......"+ action);
            k++;
        }
    }
}
