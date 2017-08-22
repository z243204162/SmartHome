package com.example.zeyupeng.smarthome.Model.DataOnMobile;

import android.util.Log;


import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-23.
 */

public class RoomDataOnMobile implements ObservableSubject{
    private static volatile RoomDataOnMobile ourInstance;



    private Map<String,AbstractRoom> mRoomDataMap;

    private List<Observer> mObserverList;

    public static RoomDataOnMobile getInstance() {
        if (ourInstance == null) {
            synchronized (RoomDataOnMobile.class) {
                if (ourInstance == null) {
                    ourInstance = new RoomDataOnMobile();
                }
            }
        }
        return ourInstance;
    }

    private RoomDataOnMobile() {
        this.mRoomDataMap = new HashMap<>();
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","roomdata constructor");
    }

    public void addRoom(AbstractRoom room){
        this.mRoomDataMap.put(room.getRoomID(),room);
        notifyObservers(ObserverActions.ADD_ROOM,room);
    }
    public void modifyRoom(AbstractRoom room){
        this.mRoomDataMap.put(room.getRoomID(),room);
        notifyObservers(ObserverActions.MODIFY_ROOM,room);
    }

    public void refreshRoom(AbstractRoom room){
        this.mRoomDataMap.put(room.getRoomID(),room);
        notifyObservers(ObserverActions.REFRESH_ROOM,room);
    }
    public void removeRoom(AbstractRoom room){
        this.mRoomDataMap.remove(room.getRoomID());
        notifyObservers(ObserverActions.REMOVE_ROOM,room);
    }

    public AbstractRoom getRoom(String roomID){
        return this.mRoomDataMap.get(roomID);
    }

    public Map<String, AbstractRoom> getRoomDataMap() {
        return mRoomDataMap;
    }


    @Override
    public void registerObserver(Observer observer) {
        this.mObserverList.add(observer);
        for(Observer observer1:mObserverList){
            Log.i("progresssss","registered observer"+mObserverList.size()+".....size..............."+observer1);
        }
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
            Log.i("progresssss","observer"+mObserverList.size()+".....size..............."+observer);
            observer.update(action,object);
        }

    }
}
