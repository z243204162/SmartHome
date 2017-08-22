package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-23.
 */

public class MainActivityRoomViewModelMap implements Observer {
    private static volatile MainActivityRoomViewModelMap ourInstance;

    public static MainActivityRoomViewModelMap getInstance() {
        if (ourInstance == null) {
            synchronized (MainActivityRoomViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new MainActivityRoomViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,MainActivityRoomViewModel> mViewModelMap;

    private MainActivityRoomViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(MainActivityRoomViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void addViewModel (MainActivityRoomViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public void modifyViewModel(MainActivityRoomViewModel viewModel){
        this.mViewModelMap.get(viewModel.getViewModelID()).modify(viewModel);
    }

    public MainActivityRoomViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, MainActivityRoomViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        MainActivityRoomViewModelConverter viewModelConverter = new MainActivityRoomViewModelConverter();
        Room room =(Room) object;
        MainActivityRoomViewModel viewModel = viewModelConverter.convertToViewModel(room);
        switch (action){
            case ADD_ROOM:
                this.addViewModel(viewModel);
                break;
            case MODIFY_ROOM:
                this.modifyViewModel(viewModel);
                break;
            case REMOVE_ROOM:
                this.removeViewModel(viewModel);
                break;
            case REFRESH_ROOM:
                this.modifyViewModel(viewModel);
                break;
        }
    }
}
