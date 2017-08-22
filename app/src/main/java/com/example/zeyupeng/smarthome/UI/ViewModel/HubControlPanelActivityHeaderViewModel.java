package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;


/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityHeaderViewModel extends BaseObservable {
    private String viewModelID;
    private String roomNameHH;
    private int roomTemperatureHH;
    private int roomHumidityHH;

    @Bindable
    public String getRoomNameHH() {
        return roomNameHH;
    }

    public void setRoomNameHH(String roomName) {
        this.roomNameHH = roomName;
        notifyPropertyChanged(BR.roomNameHH);

    }
    @Bindable
    public int getRoomTemperatureHH() {
        return roomTemperatureHH;
    }

    public void setRoomTemperatureHH(int roomTemperature) {
        this.roomTemperatureHH = roomTemperature;
        notifyPropertyChanged(BR.roomTemperatureHH);

    }
    @Bindable
    public int getRoomHumidityHH() {
        return roomHumidityHH;
    }

    public void setRoomHumidityHH(int roomHumidity) {
        this.roomHumidityHH = roomHumidity;
        notifyPropertyChanged(BR.roomHumidityHH);

    }

    public String getViewModelID() {
        return viewModelID;
    }

    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;
    }

    public void modify(HubControlPanelActivityHeaderViewModel viewModel){
        this.setRoomNameHH(viewModel.getRoomNameHH());
        this.setRoomTemperatureHH(viewModel.getRoomTemperatureHH());
        this.setRoomHumidityHH(viewModel.getRoomHumidityHH());
    }

    public void increaseTemperature(View view){
        Room room = (Room) RoomDataOnHub.getInstance().getRoom(viewModelID);
        room.setTemperature(room.getTemperature()+1);
        RoomDataOnHub.getInstance().modifyRoom(room);
    }
    public void decreaseTemperature(View view){
        Room room = (Room) RoomDataOnHub.getInstance().getRoom(viewModelID);
        room.setTemperature(room.getTemperature()-1);
        RoomDataOnHub.getInstance().modifyRoom(room);
    }

    public void decreaseHumidity(View view){
        Room room = (Room) RoomDataOnHub.getInstance().getRoom(viewModelID);
        room.setHumidity(room.getHumidity()-1);
        RoomDataOnHub.getInstance().modifyRoom(room);
    }
    public void increaseHumidity(View view){
        Room room = (Room) RoomDataOnHub.getInstance().getRoom(viewModelID);
        room.setHumidity(room.getHumidity()+1);
        RoomDataOnHub.getInstance().modifyRoom(room);
    }

    public void changeTemperature(SeekBar seekBar){
        Room room = (Room) RoomDataOnHub.getInstance().getRoom(viewModelID);
        room.setTemperature(seekBar.getProgress());
        RoomDataOnHub.getInstance().modifyRoom(room);
    }

    public void changeHumidity(SeekBar seekBar){
        Room room = (Room) RoomDataOnHub.getInstance().getRoom(viewModelID);
        room.setHumidity(seekBar.getProgress());
        RoomDataOnHub.getInstance().modifyRoom(room);
    }

}
