package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

/**
 * Created by zeyu peng on 2017-06-12.
 */

public class RoomDataConverter {
    private RoomData mRoomData;
    public RoomData converToRoomData(Room room){
        this.mRoomData=new RoomData();

        this.mRoomData.setRoomID(room.getRoomID());
        this.mRoomData.setHumidity(room.getHumidity());
        this.mRoomData.setTemperature(room.getTemperature());
        this.mRoomData.setRoomName(room.getRoomName());
        this.mRoomData.setDeviceMap(room.getDeviceMap());

        return this.mRoomData;
    }

}
