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

public class HubControlPanelActivityHeaderViewModelMap implements Observer {

    private static volatile HubControlPanelActivityHeaderViewModelMap ourInstance;

    public static HubControlPanelActivityHeaderViewModelMap getInstance() {

        if (ourInstance == null) {
            synchronized (HubControlPanelActivityHeaderViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new HubControlPanelActivityHeaderViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,HubControlPanelActivityHeaderViewModel> mViewModelMap;

    private HubControlPanelActivityHeaderViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(HubControlPanelActivityHeaderViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void addViewModel (HubControlPanelActivityHeaderViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public void modifyViewModel(HubControlPanelActivityHeaderViewModel viewModel){
        this.mViewModelMap.get(viewModel.getViewModelID()).modify(viewModel);
    }

    public HubControlPanelActivityHeaderViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, HubControlPanelActivityHeaderViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        HubControlPanelActivityHeaderViewModelConverter viewModelConverter =
                new HubControlPanelActivityHeaderViewModelConverter();
        Room room =(Room) object;
        HubControlPanelActivityHeaderViewModel viewModel = viewModelConverter.convertToViewModel(room);
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