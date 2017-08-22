package com.example.zeyupeng.smarthome.UI.ViewModel;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;

/**
 * Created by zeyu peng on 2017-07-10.
 */

public class MainActivityPlansViewModelConverter {
    private MainActivityPlansViewModel mViewModel;

    public MainActivityPlansViewModel converToViewModel(Plan plan){
        mViewModel = new MainActivityPlansViewModel();

        mViewModel.setViewModelID(plan.getPlanID());
        mViewModel.setDeviceID(plan.getDeviceID());
        try{
            mViewModel.setDeviceName(DeviceDataOnMobile.getInstance().getDevice(plan.getDeviceID()).getProductName());
            mViewModel.setDeviceCustomName(DeviceDataOnMobile.getInstance().getDevice(plan.getDeviceID()).getCustomName());
        }catch (Exception e){
            mViewModel.setDeviceName("Device does not exist");
            mViewModel.setDeviceCustomName("Device does not exist");
        }
        mViewModel.setPlanAction(plan.getPlanAction());
        mViewModel.setHour(String.valueOf(plan.getHour()));
        mViewModel.setMinute(String.valueOf(plan.getMinute()));
        mViewModel.setRepeatDate(plan.getRepeatDate());

        return this.mViewModel;
    }
}
