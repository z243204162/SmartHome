package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityDeviceSimulatorViewModelMap implements Observer {
    private static volatile HubControlPanelActivityDeviceSimulatorViewModelMap ourInstance;

    public static HubControlPanelActivityDeviceSimulatorViewModelMap getInstance() {
        if (ourInstance == null) {
            synchronized (HubControlPanelActivityDeviceSimulatorViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new HubControlPanelActivityDeviceSimulatorViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,HubControlPanelActivityDeviceSimulatorViewModel> mViewModelMap;

    private HubControlPanelActivityDeviceSimulatorViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(HubControlPanelActivityDeviceSimulatorViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void addViewModel (HubControlPanelActivityDeviceSimulatorViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public void modifyViewModel(HubControlPanelActivityDeviceSimulatorViewModel viewModel){
        this.mViewModelMap.get(viewModel.getViewModelID()).modify(viewModel);
    }

    public HubControlPanelActivityDeviceSimulatorViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, HubControlPanelActivityDeviceSimulatorViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        HubControlPanelActivityDeviceSimulatorViewModelConverter viewModelConverter =
                new HubControlPanelActivityDeviceSimulatorViewModelConverter();
        AbstractDevice device =(AbstractDevice) object;
        HubControlPanelActivityDeviceSimulatorViewModel viewModel = viewModelConverter.convertToViewModel(device);
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
        }
    }

}