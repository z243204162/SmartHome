package com.example.zeyupeng.smarthome.UI.ViewModel;

import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubRoomsActivityRoomViewModelConverter {
    private HubRoomsActivityRoomViewModel mViewModel;

    public HubRoomsActivityRoomViewModel convertToViewModel(AbstractRoom room){
        this.mViewModel =new HubRoomsActivityRoomViewModel();

        this.mViewModel.setViewModelID(room.getRoomID());
        this.mViewModel.setRoomNameHub(room.getRoomName());
        this.mViewModel.setRoomTemperatureHub(String.valueOf(room.getTemperature()));
        this.mViewModel.setRoomHumidityHub(String.valueOf(room.getHumidity()));
        return this.mViewModel;
    }
}
