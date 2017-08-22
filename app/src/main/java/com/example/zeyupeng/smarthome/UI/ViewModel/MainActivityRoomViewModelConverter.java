package com.example.zeyupeng.smarthome.UI.ViewModel;

import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public class MainActivityRoomViewModelConverter {

    private MainActivityRoomViewModel mViewModel;

    public MainActivityRoomViewModel convertToViewModel(AbstractRoom room){
        this.mViewModel =new MainActivityRoomViewModel();

        this.mViewModel.setViewModelID(room.getRoomID());
        this.mViewModel.setRoomNameMain(room.getRoomName());
        this.mViewModel.setRoomTemperatureMain(String.valueOf(room.getTemperature()));
        this.mViewModel.setRoomHumidityMain(String.valueOf(room.getHumidity()));
        return this.mViewModel;
    }
}
