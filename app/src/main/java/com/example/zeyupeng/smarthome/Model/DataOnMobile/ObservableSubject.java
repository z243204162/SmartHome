package com.example.zeyupeng.smarthome.Model.DataOnMobile;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public interface ObservableSubject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(ObserverActions action,Object object);
}
