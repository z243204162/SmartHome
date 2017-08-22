package com.example.zeyupeng.smarthome.UI.ViewModel;


import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.View.IntentTag;
import com.example.zeyupeng.smarthome.View.RoomControlAddDeviceDialogActivity;


/**
 * Created by zeyu peng on 2017-06-21.
 */

public class RoomControlPanelActivityHeaderViewModel extends BaseObservable {

    private String hubStatusMH;
    private String viewModelID;
    private String roomNameMH;
    private String roomTemperatureMH;
    private String roomHumidityMH;

    @Bindable
    public String getRoomNameMH() {
        return roomNameMH;
    }

    public void setRoomNameMH(String roomName) {
        this.roomNameMH = roomName;
        notifyPropertyChanged(BR.roomNameMH);

    }
    @Bindable
    public String getRoomTemperatureMH() {
        return roomTemperatureMH;
    }

    public void setRoomTemperatureMH(String roomTemperature) {
        this.roomTemperatureMH = roomTemperature;
        notifyPropertyChanged(BR.roomTemperatureMH);

    }
    @Bindable
    public String getRoomHumidityMH() {
        return roomHumidityMH;
    }

    public void setRoomHumidityMH(String roomHumidity) {
        this.roomHumidityMH = roomHumidity;
        notifyPropertyChanged(BR.roomHumidityMH);

    }
    @Bindable
    public String getHubStatusMH() {
        return hubStatusMH;
    }

    public void setHubStatusMH(String hubStatusMH) {
        this.hubStatusMH = hubStatusMH;
        notifyPropertyChanged(BR.hubStatusMH);
    }
    public String getViewModelID() {
        return viewModelID;
    }

    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;
    }

    public void modify(RoomControlPanelActivityHeaderViewModel viewModel){
        this.setRoomNameMH(viewModel.getRoomNameMH());
        this.setRoomTemperatureMH(viewModel.getRoomTemperatureMH());
        this.setRoomHumidityMH(viewModel.getRoomHumidityMH());

    }

    public void addDevice(View view){
        Intent intent = new Intent(view.getContext(), RoomControlAddDeviceDialogActivity.class);
        intent.putExtra(IntentTag.ROOM_CONTROL_PANEL_TO_ADD_DEVICE_ROOMID.toString(),viewModelID);
        view.getContext().startActivity(intent);
    }
}
