package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;
import com.example.zeyupeng.smarthome.Model.MyRooms.RoomFactory;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class MainActivityAddRoomViewModel {
    private EditText mEditText;
    private Room mRoom;
    private MessageSender mMessageSender ;
    private MessageEditer mMessageEditer ;
    private StreamProgressPoster mProgressPoster;


    public MainActivityAddRoomViewModel() {
        mMessageSender = new MessageSender(ChannelNameGenerator.getControlChannel());
        mMessageEditer = new MessageEditer();
    }

    public void editRoomName(View view){
        EditText editText = (EditText)view;
        this.mEditText=editText;
        Log.i("adddddddddroom","..........");
    }

    public void addRoom(View view){

        createRoom();
        postProgress(view.getContext(),PackageType.PACKAGE_TYPE_ROOM,mRoom.getRoomID());

        sendMessage(PackageType.PACKAGE_TYPE_ROOM,mRoom,MessageAction.ADD);

    }



    public void createRoom(){
        RoomFactory roomFactory = new RoomFactory();
        mRoom =(Room) roomFactory.createRoom();
        try{
            mRoom.setRoomName(this.mEditText.getText().toString());
        }catch (Exception e){
            mRoom.setRoomName("New Room");
        }
    }

    public void sendMessage(String packageType,Object object,String messageAction){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,messageAction));
    }

    public void postProgress(Context context,String packageType,String messageID){
        mProgressPoster = new StreamProgressPoster(context,packageType,messageID);
        mProgressPoster.startSteaming();
        //RoomDataOnMobile.getInstance().registerObserver(mProgressPoster);

    }

    public void cancel(View view){
        ((Activity)view.getContext()).finish();
    }
}
