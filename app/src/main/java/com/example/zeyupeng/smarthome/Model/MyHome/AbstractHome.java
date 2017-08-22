package com.example.zeyupeng.smarthome.Model.MyHome;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-22.
 */

public abstract class AbstractHome {
    private String mHomeID;
    private Map<String,String> mRoomMap = new HashMap<>();


    public String getHomeID() {
        return mHomeID;
    }

    public void setHomeID(String homeID) {
        mHomeID = homeID;
    }

    public void addRoom (AbstractRoom room){
        this.mRoomMap.put(room.getRoomID(),room.getRoomName());
    }

    public void removeRoom (AbstractRoom room){
        this.mRoomMap.remove(room.getRoomID());
    }


    public Map<String, String> getRoomMap() {
        return mRoomMap;
    }

    public void setRoomMap(Map<String, String> roomMap) {
        mRoomMap = roomMap;
    }
}
