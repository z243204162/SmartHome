package com.example.zeyupeng.smarthome.Model.MyRooms;

import com.amazonaws.models.nosql.RoomData;

/**
 * Created by zeyu peng on 2017-06-12.
 */

public class Room extends  AbstractRoom {
    public void importData(RoomData roomData){
        this.setRoomID(roomData.getRoomID());
        this.setRoomName(roomData.getRoomName());
        this.setTemperature(roomData.getTemperature());
        this.setHumidity(roomData.getHumidity());
        this.setDeviceMap(roomData.getDeviceMap());
    }
}
