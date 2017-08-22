package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;
import com.example.zeyupeng.smarthome.View.MainAddRoomDialogActivity;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-12.
 */

public class MainActivityViewModel extends BaseObservable implements Observer{
    private MessageSender mMessageSender ;
    private MessageEditer mMessageEditer ;
    private StreamProgressPoster mProgressPoster;

    private String hubStatusMain;

    private static volatile MainActivityViewModel ourInstance;


    public static MainActivityViewModel getInstance() {
        if (ourInstance == null) {
            synchronized (MainActivityViewModel.class) {
                if (ourInstance == null) {
                    ourInstance = new MainActivityViewModel();
                }
            }
        }
        return ourInstance;
    }

    public MainActivityViewModel() {
        mMessageSender = new MessageSender(ChannelNameGenerator.getQueryStatusChannel());
        mMessageEditer = new MessageEditer();
    }

    @Bindable
    public String getHubStatusMain() {
        return hubStatusMain;
    }

    public void setHubStatusMain(String hubStatusMain) {
        this.hubStatusMain = hubStatusMain;
        notifyPropertyChanged(BR.hubStatusMain);
    }


    public void addRoom (View view){

        Intent i =new Intent(view.getContext(), MainAddRoomDialogActivity.class);
        view.getContext().startActivity(i);


    }



    public void refreshData(){
        Log.i("sssddddddddd","yes");
        //postProgress(view.getContext(), PackageType.PACKAGE_TYPE_HUB_STATUS,null);
        //sendMessage(PackageType.PACKAGE_TYPE_HUB_STATUS,"", MessageAction.REFRESH_MOBILE_STATUS);
    }

    public void sendMessage(String packageType,Object object,String messageAction){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,messageAction));
    }

    public void postProgress(Context context,String packageType,String messageID){
        mProgressPoster = new StreamProgressPoster(context,packageType,messageID);

        mProgressPoster.startSteaming();
        //RoomDataOnMobile.getInstance().registerObserver(mProgressPoster);

    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action){
            case SET_HUB_STATUS:
                String hubStatus = (String)object;
                this.setHubStatusMain(hubStatus);
                break;
        }
    }
}
