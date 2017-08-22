package com.example.zeyupeng.smarthome.Model.MyRooms;

import android.content.Context;

import com.example.zeyupeng.smarthome.IDGenerater;

/**
 * Created by zeyu peng on 2017-06-12.
 */

public class RoomFactory {
    public AbstractRoom createRoom(){
        AbstractRoom room = new Room();
        room.setRoomID(IDGenerater.getInstance().generateID());
        room.setHumidity(50);
        room.setTemperature(22);
        return room;
    }
}
