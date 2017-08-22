package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public class RoomControlPanelActivityDeviceControlerViewModelConverter {
    private RoomControlPanelActivityDeviceControlerViewModel mViewModel;
    private AbstractDevice mDevice;

    public RoomControlPanelActivityDeviceControlerViewModel convertToViewModel(AbstractDevice device){
        this.mViewModel = new RoomControlPanelActivityDeviceControlerViewModel();
        this.mViewModel.setCustumNameRC(device.getCustomName());
        this.mViewModel.setProductNameRC(device.getProductName());
        this.mViewModel.setViewModelID(device.getProductID());
        this.mViewModel.setProductTypeRC(device.getProductType());
        this.mViewModel.setDescriptionRC(device.getDescription());
        this.mViewModel.setStatusRC(device.getStatus());
        this.mViewModel.setLocationID(device.getLocationID());
        this.mViewModel.setControlTypeRC(device.getControlerType());
        this.mViewModel.setImageRC(device.getImage());

        Log.i("iiiiimage",device.getProductName()+".........."+device.getImage());



        modeSwitchConverter(device);
        doorLockConverter(device);
        adjusterConverter(device);
        notificationSenderConverter(device);


        return this.mViewModel;
    }

    public void modeSwitchConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.MODE_SWITCH.toString())){
            ModeSwitch modeSwitch=(ModeSwitch) device;
            this.mViewModel.setModeOptionsRC(modeSwitch.getModeOption());
            this.mViewModel.setModeRC(modeSwitch.getMode());
        }
    }

    public void doorLockConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.DOOR_LOCK.toString())){
            DoorLock doorLock=(DoorLock) device;
            this.mViewModel.setLockStatusRC(doorLock.getLockStatus());
            this.mViewModel.setPasswordRC(doorLock.getPassword());
            this.mViewModel.setNFCModifyPermit(doorLock.getDoorLockModifyPermit());
        }
    }

    public void adjusterConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.ADJUSTER.toString())){
            Adjuster adjuster=(Adjuster) device;
            this.mViewModel.setAdjustableValueNameRC(adjuster.getAdjustableValueName());
            this.mViewModel.setAdjustableValueRC(adjuster.getCurrentValue());
            this.mViewModel.setMaxValueRC(adjuster.getMaxValue());
        }
    }

    public void notificationSenderConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.NOTIFICATION_SENDER.toString())){
            NotificationSender notificationSender=(NotificationSender) device;
            this.mViewModel.setNotificationPermitRC(notificationSender.getNotificationPermit());
        }
    }

}
