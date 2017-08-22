package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public class RoomControlPanelActivityDeviceControlerViewModelMap implements Observer{
    private static volatile RoomControlPanelActivityDeviceControlerViewModelMap ourInstance;

    public static RoomControlPanelActivityDeviceControlerViewModelMap getInstance() {
        if (ourInstance == null) {
            synchronized (RoomControlPanelActivityDeviceControlerViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new RoomControlPanelActivityDeviceControlerViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,RoomControlPanelActivityDeviceControlerViewModel> mViewModelMap;

    private RoomControlPanelActivityDeviceControlerViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(RoomControlPanelActivityDeviceControlerViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void addViewModel (RoomControlPanelActivityDeviceControlerViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public void modifyViewModel(RoomControlPanelActivityDeviceControlerViewModel viewModel){
        this.mViewModelMap.get(viewModel.getViewModelID()).modify(viewModel);
    }

    public RoomControlPanelActivityDeviceControlerViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, RoomControlPanelActivityDeviceControlerViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        RoomControlPanelActivityDeviceControlerViewModelConverter viewModelConverter =
                new RoomControlPanelActivityDeviceControlerViewModelConverter();
        AbstractDevice device =(AbstractDevice) object;
        RoomControlPanelActivityDeviceControlerViewModel viewModel = viewModelConverter.convertToViewModel(device);
        switch (action){
            case ADD_DEVICE:
                this.addViewModel(viewModel);
                break;
            case MODIFY_DEVICE:
                this.modifyViewModel(viewModel);
                break;
            case REMOVE_DEVICE:
                this.removeViewModel(viewModel);
                break;
            case REFRESH_DEVICE:
                this.modifyViewModel(viewModel);
                break;

        }
    }

}
