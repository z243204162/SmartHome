package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;


import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;
import com.example.zeyupeng.smarthome.View.IntentTag;
import com.example.zeyupeng.smarthome.View.RoomControlPanelActivity;

/**
 * Created by zeyu peng on 2017-06-19.
 */

public class MainActivityRoomViewModel extends BaseObservable{
    private String viewModelID;
    private String roomNameMain;
    private String roomTemperatureMain;
    private String roomHumidityMain;
    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;
    private StreamProgressPoster mProgressPoster;

    public MainActivityRoomViewModel() {
        mMessageSender = new MessageSender(ChannelNameGenerator.getControlChannel());
        mMessageEditer = new MessageEditer();
    }

    @Bindable
    public String getRoomNameMain() {
        return roomNameMain;
    }

    public void setRoomNameMain(String roomName) {
        this.roomNameMain = roomName;
        notifyPropertyChanged(BR.roomNameMain);
    }
    @Bindable
    public String getRoomTemperatureMain() {
        return roomTemperatureMain;
    }

    public void setRoomTemperatureMain(String roomTemperature) {
        this.roomTemperatureMain = roomTemperature;
        notifyPropertyChanged(BR.roomTemperatureMain);
    }
    @Bindable
    public String getRoomHumidityMain() {
        return roomHumidityMain;
    }

    public void setRoomHumidityMain(String roomHumidity) {
        this.roomHumidityMain = roomHumidity;
        notifyPropertyChanged(BR.roomHumidityMain);
    }

    public String getViewModelID() {
        return viewModelID;
    }

    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;
    }

    public void onClick (View view){

        Intent i =new Intent(view.getContext(),RoomControlPanelActivity.class);
        Log.i("uuuuuuuuu",this.viewModelID);
        i.putExtra(IntentTag.MAIN_TO_ROOM_CONTROL_PANEL_ROOMID.toString(),this.viewModelID);
        view.getContext().startActivity(i);

    }

    public void modify(MainActivityRoomViewModel viewModel){
        this.setRoomNameMain(viewModel.getRoomNameMain());
        this.setRoomTemperatureMain(viewModel.getRoomTemperatureMain());
        this.setRoomHumidityMain(viewModel.getRoomHumidityMain());
    }

    public void deleteRoom(View view){

        postProgress(view.getContext()
                ,PackageType.PACKAGE_TYPE_ROOM
                ,RoomDataOnMobile.getInstance().getRoom(viewModelID).getRoomID());
        sendMessage(PackageType.PACKAGE_TYPE_ROOM
                ,RoomDataOnMobile.getInstance().getRoom(viewModelID)
                ,MessageAction.REMOVE);
    }

    public void sendMessage(String packageType,Object object,String messageAction){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,messageAction));
    }

    public void postProgress(Context context, String packageType, String messageID){
        mProgressPoster = new StreamProgressPoster(context,packageType,messageID);

        mProgressPoster.startSteaming();
        //RoomDataOnMobile.getInstance().registerObserver(mProgressPoster);

    }
}
