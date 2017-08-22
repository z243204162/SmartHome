package com.example.zeyupeng.smarthome.Model.DataOnHub;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObservableSubject;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class HubStatusDataOnHub implements ObservableSubject {

    private static volatile HubStatusDataOnHub ourInstance;
    private List<Observer> mObserverList;

    public static HubStatusDataOnHub getInstance() {
        if (ourInstance == null) {
            synchronized (HubStatusDataOnHub.class) {
                if (ourInstance == null) {
                    ourInstance = new HubStatusDataOnHub();
                }
            }
        }
        return ourInstance;
    }



    private String onlineStatus;

    private HubStatusDataOnHub() {
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","HUB STATUS constructor");
    }


    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
        notifyObservers(ObserverActions.SET_HUB_STATUS,this.onlineStatus);
    }
    public void queryOnlineStatus(){
        notifyObservers(ObserverActions.QUERY_HUB_STATUS,onlineStatus);
    }

    public void refreshMoblieStatus(){
        notifyObservers(ObserverActions.REFRESH_MOBLIE_STATUS,onlineStatus);
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
    public void notifyObservers(ObserverActions action, Object object) {
        for(int i=0;i<this.mObserverList.size();i++){
            Observer observer = (Observer) this.mObserverList.get(i);
            observer.update(action,object);
        }
    }
}
