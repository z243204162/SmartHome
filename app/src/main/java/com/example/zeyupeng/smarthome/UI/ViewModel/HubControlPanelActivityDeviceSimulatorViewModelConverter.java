package com.example.zeyupeng.smarthome.UI.ViewModel;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityDeviceSimulatorViewModelConverter {

    private HubControlPanelActivityDeviceSimulatorViewModel mViewModel;
    private AbstractDevice mDevice;

    public HubControlPanelActivityDeviceSimulatorViewModel convertToViewModel(AbstractDevice device){
        this.mViewModel = new HubControlPanelActivityDeviceSimulatorViewModel();
        this.mViewModel.setCustumNameHC(device.getCustomName());
        this.mViewModel.setProductNameHC(device.getProductName());
        this.mViewModel.setViewModelID(device.getProductID());
        this.mViewModel.setProductTypeHC(device.getProductType());
        this.mViewModel.setDescriptionHC(device.getDescription());
        this.mViewModel.setStatusHC(device.getStatus());
        this.mViewModel.setControlType(device.getControlerType());
        this.mViewModel.setLocationID(device.getLocationID());
        this.mViewModel.setImageHC(device.getImage());



        modeSwitchConverter(device);
        doorLockConverter(device);
        adjusterConverter(device);
        return this.mViewModel;
    }

    public void modeSwitchConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.MODE_SWITCH.toString())){
            ModeSwitch modeSwitch=(ModeSwitch) device;
            this.mViewModel.setModeOptionsHC(modeSwitch.getModeOption());
            this.mViewModel.setModeHC(modeSwitch.getMode());
        }
    }

    public void doorLockConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.DOOR_LOCK.toString())){
            DoorLock doorLock=(DoorLock) device;
            this.mViewModel.setLockStatusHC(doorLock.getLockStatus());
            this.mViewModel.setPasswordHC(doorLock.getPassword());
            this.mViewModel.setNFCmodifyPermit(doorLock.getDoorLockModifyPermit());
        }
    }

    public void adjusterConverter(AbstractDevice device){
        if(device.getControlerType().contains(ControlerType.ADJUSTER.toString())){
            Adjuster adjuster=(Adjuster) device;
            this.mViewModel.setAdjustableValueNameHC(adjuster.getAdjustableValueName());
            this.mViewModel.setAdjustableValueHC(adjuster.getCurrentValue());
            this.mViewModel.setMaxValueHC(adjuster.getMaxValue());
        }
    }

}
