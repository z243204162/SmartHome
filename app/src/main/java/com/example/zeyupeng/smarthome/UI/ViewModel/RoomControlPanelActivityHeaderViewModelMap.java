package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public class RoomControlPanelActivityHeaderViewModelMap implements Observer{

    private static volatile RoomControlPanelActivityHeaderViewModelMap ourInstance;

    public static RoomControlPanelActivityHeaderViewModelMap getInstance() {

        if (ourInstance == null) {
            synchronized (RoomControlPanelActivityHeaderViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new RoomControlPanelActivityHeaderViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,RoomControlPanelActivityHeaderViewModel> mViewModelMap;

    private RoomControlPanelActivityHeaderViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(RoomControlPanelActivityHeaderViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void modifyViewModel (RoomControlPanelActivityHeaderViewModel viewModel){
        this.mViewModelMap.get(viewModel.getViewModelID()).modify(viewModel);
    }

    public void addViewModel (RoomControlPanelActivityHeaderViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public RoomControlPanelActivityHeaderViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, RoomControlPanelActivityHeaderViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        RoomControlPanelActivityHeaderViewModelConverter viewModelConverter =
                new RoomControlPanelActivityHeaderViewModelConverter();

        switch (action){
            case ADD_ROOM:
                Room newRoom =(Room) object;
                RoomControlPanelActivityHeaderViewModel newViewModel = viewModelConverter.convertToViewModel(newRoom);
                this.addViewModel(newViewModel);
                break;
            case MODIFY_ROOM:
                Room room =(Room) object;
                RoomControlPanelActivityHeaderViewModel viewModel = viewModelConverter.convertToViewModel(room);
                this.modifyViewModel(viewModel);
                break;
            case REMOVE_ROOM:
                Room roomToBeDeleted =(Room) object;
                RoomControlPanelActivityHeaderViewModel viewModelToBeDeleted = viewModelConverter.convertToViewModel(roomToBeDeleted);
                this.removeViewModel(viewModelToBeDeleted);
                break;
            case REFRESH_ROOM:
                Room roomToBeRefreshed =(Room) object;
                RoomControlPanelActivityHeaderViewModel viewModelToBeRefreshed = viewModelConverter.convertToViewModel(roomToBeRefreshed);
                this.modifyViewModel(viewModelToBeRefreshed);
                break;
            case SET_HUB_STATUS:
                String hubStatus = (String)object;
                for(Map.Entry<String,RoomControlPanelActivityHeaderViewModel> entry:this.getViewModelMap().entrySet()){
                    entry.getValue().setHubStatusMH(hubStatus);
                }
                break;
        }
    }
}
