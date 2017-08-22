package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubRoomsActivityRoomViewModelMap  implements Observer {
    private static volatile HubRoomsActivityRoomViewModelMap ourInstance;

    public static HubRoomsActivityRoomViewModelMap getInstance() {
        if (ourInstance == null) {
            synchronized (HubRoomsActivityRoomViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new HubRoomsActivityRoomViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,HubRoomsActivityRoomViewModel> mViewModelMap;

    private HubRoomsActivityRoomViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(HubRoomsActivityRoomViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void addViewModel (HubRoomsActivityRoomViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public void modifyViewModel(HubRoomsActivityRoomViewModel viewModel){
        this.mViewModelMap.get(viewModel.getViewModelID()).modify(viewModel);
    }

    public HubRoomsActivityRoomViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, HubRoomsActivityRoomViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        HubRoomsActivityRoomViewModelConverter viewModelConverter =
                new HubRoomsActivityRoomViewModelConverter();
        Room room =(Room) object;
        HubRoomsActivityRoomViewModel viewModel = viewModelConverter.convertToViewModel(room);
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
        }
    }
}
