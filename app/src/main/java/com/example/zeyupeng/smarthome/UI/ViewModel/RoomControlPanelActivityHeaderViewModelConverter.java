package com.example.zeyupeng.smarthome.UI.ViewModel;

import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public class RoomControlPanelActivityHeaderViewModelConverter {
    private RoomControlPanelActivityHeaderViewModel mViewModel;

    public RoomControlPanelActivityHeaderViewModel convertToViewModel(AbstractRoom room){
        this.mViewModel =new RoomControlPanelActivityHeaderViewModel();

        this.mViewModel.setViewModelID(room.getRoomID());
        this.mViewModel.setRoomNameMH(room.getRoomName());
        this.mViewModel.setRoomTemperatureMH(String.valueOf(room.getTemperature()));
        this.mViewModel.setRoomHumidityMH(String.valueOf(room.getHumidity()));
        return this.mViewModel;
    }
}
