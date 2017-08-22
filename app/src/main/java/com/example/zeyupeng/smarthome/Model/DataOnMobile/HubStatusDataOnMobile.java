package com.example.zeyupeng.smarthome.Model.DataOnMobile;

import android.util.Log;

import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh.RefreshToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zeyu peng on 2017-07-11.
 */

public class HubStatusDataOnMobile implements ObservableSubject{

    private static volatile HubStatusDataOnMobile ourInstance;
    private List<Observer> mObserverList;

    public boolean isQueryReceived() {
        return mQueryReceived;
    }

    public void setQueryReceived(boolean queryReceived) {
        mQueryReceived = queryReceived;
    }

    private boolean mQueryReceived;

    public static HubStatusDataOnMobile getInstance() {
        if (ourInstance == null) {
            synchronized (HubStatusDataOnMobile.class) {
                if (ourInstance == null) {
                    ourInstance = new HubStatusDataOnMobile();
                }
            }
        }
        return ourInstance;
    }



    private String onlineStatus;

    private HubStatusDataOnMobile() {
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","HUB STATUS constructor");
    }


    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
        notifyObservers(ObserverActions.SET_HUB_STATUS,onlineStatus);
    }
    public void queryOnlineStatus(){
        notifyObservers(ObserverActions.QUERY_HUB_STATUS,onlineStatus);
    }

    public void mobileStatusRefreshed(){
        notifyObservers(ObserverActions.REFRESH_MOBLIE_STATUS, RefreshToken.REFRESH_TOKEN);
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
