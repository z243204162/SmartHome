package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-10.
 */

public class MainActivityPlansViewModelMap implements Observer {
    private static volatile MainActivityPlansViewModelMap ourInstance;

    public static MainActivityPlansViewModelMap getInstance() {
        if (ourInstance == null) {
            synchronized (MainActivityPlansViewModelMap.class) {
                if (ourInstance == null) {
                    ourInstance = new MainActivityPlansViewModelMap();
                }
            }
        }
        return ourInstance;
    }

    private Map<String,MainActivityPlansViewModel> mViewModelMap;

    private MainActivityPlansViewModelMap() {
        this.mViewModelMap = new HashMap<>();
    }

    public void removeViewModel(MainActivityPlansViewModel viewModel){
        this.mViewModelMap.remove(viewModel.getViewModelID());
    }

    public void addViewModel (MainActivityPlansViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }

    public void modifyViewModel(MainActivityPlansViewModel viewModel){
        this.mViewModelMap.put(viewModel.getViewModelID(),viewModel);
    }
    public MainActivityPlansViewModel getViewModel(String viewModelID){
        return this.mViewModelMap.get(viewModelID);
    }


    public Map<String, MainActivityPlansViewModel> getViewModelMap() {
        return mViewModelMap;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        MainActivityPlansViewModelConverter converter = new MainActivityPlansViewModelConverter();
        Plan plan = (Plan) object;
        MainActivityPlansViewModel viewModel=converter.converToViewModel(plan);
        switch (action){
            case ADD_PLAN:
                this.addViewModel(viewModel);
                break;
            case MODIFY_PLAN:
                this.modifyViewModel(viewModel);
                break;
            case REMOVE_PLAN:
                this.removeViewModel(viewModel);
                break;
        }
    }
}
