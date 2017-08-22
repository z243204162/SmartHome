package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;


import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.View.HubControlPanelActivity;
import com.example.zeyupeng.smarthome.View.IntentTag;


/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubRoomsActivityRoomViewModel extends BaseObservable {
    private String viewModelID;
    private String roomNameHub;
    private String roomTemperatureHub;
    private String roomHumidityHub;

    @Bindable
    public String getRoomNameHub() {
        return roomNameHub;
    }

    public void setRoomNameHub(String roomName) {
        this.roomNameHub = roomName;
        notifyPropertyChanged(BR.roomNameHub);
    }
    @Bindable
    public String getRoomTemperatureHub() {
        return roomTemperatureHub;
    }

    public void setRoomTemperatureHub(String roomTemperature) {
        this.roomTemperatureHub = roomTemperature;
        notifyPropertyChanged(BR.roomTemperatureHub);
    }
    @Bindable
    public String getRoomHumidityHub() {
        return roomHumidityHub;
    }

    public void setRoomHumidityHub(String roomHumidity) {
        this.roomHumidityHub = roomHumidity;
        notifyPropertyChanged(BR.roomHumidityHub);
    }

    public String getViewModelID() {
        return viewModelID;
    }

    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;

    }

    public void onClick (View view){
        Intent i =new Intent(view.getContext(),HubControlPanelActivity.class);
        Log.i("uuuuuuuuu",this.viewModelID);
        i.putExtra(IntentTag.HUB_TO_HUB_CONTROL_PANEL_ROOMID.toString(),this.viewModelID);
        view.getContext().startActivity(i);
    }

    public void modify(HubRoomsActivityRoomViewModel viewModel){
        this.setRoomNameHub(viewModel.getRoomNameHub());
        this.setRoomTemperatureHub(viewModel.getRoomTemperatureHub());
        this.setRoomHumidityHub(viewModel.getRoomHumidityHub());
    }
}
