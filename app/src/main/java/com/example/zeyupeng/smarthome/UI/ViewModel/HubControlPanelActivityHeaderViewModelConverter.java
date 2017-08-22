package com.example.zeyupeng.smarthome.UI.ViewModel;

import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityHeaderViewModelConverter {
    private HubControlPanelActivityHeaderViewModel mViewModel;

    public HubControlPanelActivityHeaderViewModel convertToViewModel(AbstractRoom room){
        this.mViewModel =new HubControlPanelActivityHeaderViewModel();

        this.mViewModel.setViewModelID(room.getRoomID());
        this.mViewModel.setRoomNameHH(room.getRoomName());
        this.mViewModel.setRoomTemperatureHH(room.getTemperature());
        this.mViewModel.setRoomHumidityHH(room.getHumidity());
        return this.mViewModel;
    }
}
