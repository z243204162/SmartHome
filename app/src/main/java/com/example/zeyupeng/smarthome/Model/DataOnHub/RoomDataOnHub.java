package com.example.zeyupeng.smarthome.Model.DataOnHub;

import android.util.Log;


import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObservableSubject;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-28.
 */

public class RoomDataOnHub implements ObservableSubject {
    private static volatile RoomDataOnHub ourInstance;
    private Map<String,AbstractRoom> mRoomDataMap;

    private List<Observer> mObserverList;

    public static RoomDataOnHub getInstance() {
        if (ourInstance == null) {
            synchronized (RoomDataOnHub.class) {
                if (ourInstance == null) {
                    ourInstance = new RoomDataOnHub();
                }
            }
        }
        return ourInstance;
    }

    private RoomDataOnHub() {
        this.mRoomDataMap = new HashMap<>();
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","roomdata constructor");
    }

    public void addRoom (AbstractRoom room){
        this.mRoomDataMap.put(room.getRoomID(),room);
        Log.i("hhhhhhub","room"+room.getRoomName());
        notifyObservers(ObserverActions.ADD_ROOM,room);
    }

    public void refreshyRoom (AbstractRoom room){
        Log.i("rrrrrrrrrefresh","RoomDataOnHub is called......."+room.getRoomName());
        Log.i("hhhhhhub","room"+room.getRoomName());
        notifyObservers(ObserverActions.REFRESH_ROOM,room);
    }

    public void modifyRoom (AbstractRoom room){
        this.mRoomDataMap.put(room.getRoomID(),room);
        notifyObservers(ObserverActions.MODIFY_ROOM,room);
        notifyObservers(ObserverActions.ROOM_SEND_NOTIFICATION,room);
    }

    public void removeRoom(AbstractRoom room){
        this.mRoomDataMap.remove(room.getRoomID());
        notifyObservers(ObserverActions.REMOVE_ROOM,room);
    }

    public AbstractRoom getRoom(String roomID){
        return this.mRoomDataMap.get(roomID);
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

    public Map<String, AbstractRoom> getRoomDataMap() {
        return mRoomDataMap;
    }
}
