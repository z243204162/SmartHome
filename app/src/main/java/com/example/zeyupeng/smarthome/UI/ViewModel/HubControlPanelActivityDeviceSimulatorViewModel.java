package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.LockStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.View.IntentTag;
import com.example.zeyupeng.smarthome.View.NFCCardReaderDialogActivity;

import java.util.List;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityDeviceSimulatorViewModel extends BaseObservable{
    private String viewModelID;
    private String custumNameHC;
    private String productNameHC;
    private String productTypeHC;
    private String descriptionHC;
    private String statusHC;
    private List<String> controlType;
    private String locationID;
    private int imageHC;

    private String[] modeOptionsHC;
    private String modeHC;
    private int modeChosen;

    private int adjustableValueHC;
    private int maxValueHC;
    private String adjustableValueNameHC;

    private String lockStatusHC;
    private String passwordHC;
    private String passwordInputHC;
    private boolean nFCmodifyPermit;





    public String getViewModelID() {
        return viewModelID;
    }

    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;
    }
    @Bindable
    public String getCustumNameHC() {
        return custumNameHC;
    }

    public void setCustumNameHC(String custumNameHC) {
        this.custumNameHC = custumNameHC;
        notifyPropertyChanged(BR.custumNameHC);
    }
    @Bindable
    public String getProductNameHC() {
        return productNameHC;
    }

    public void setProductNameHC(String productNameHC) {
        this.productNameHC = productNameHC;
        notifyPropertyChanged(BR.productNameHC);
    }
    @Bindable
    public String getProductTypeHC() {
        return productTypeHC;
    }

    public void setProductTypeHC(String productTypeHC) {
        this.productTypeHC = productTypeHC;
        notifyPropertyChanged(BR.productTypeHC);
    }
    @Bindable
    public String getDescriptionHC() {
        return descriptionHC;
    }

    public void setDescriptionHC(String descriptionHC) {
        this.descriptionHC = descriptionHC;
        notifyPropertyChanged(BR.descriptionHC);
    }
    @Bindable
    public String getStatusHC() {
        return statusHC;
    }

    public void setStatusHC(String statusHC) {
        this.statusHC = statusHC;
        notifyPropertyChanged(BR.statusHC);
    }
    @Bindable
    public int getImageHC() {
        return imageHC;
    }

    public void setImageHC(int imageHC) {
        this.imageHC = imageHC;
        notifyPropertyChanged(BR.imageHC);
    }
    public List<String> getControlType() {
        return controlType;
    }

    public void setControlType(List<String> controlType) {
        this.controlType = controlType;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
    @Bindable
    public String[] getModeOptionsHC() {
        return modeOptionsHC;
    }

    public void setModeOptionsHC(String[] modeOptionsHC) {
        this.modeOptionsHC = modeOptionsHC;
        notifyPropertyChanged(BR.modeOptionsHC);
    }
    @Bindable
    public String getModeHC() {
        return modeHC;
    }

    public void setModeHC(String modeHC) {
        this.modeHC = modeHC;
        notifyPropertyChanged(BR.modeHC);
    }


    public int getModeChosen() {
        return modeChosen;
    }

    public void setModeChosen(int modeChosen) {
        this.modeChosen = modeChosen;
    }

    @Bindable
    public int getAdjustableValueHC() {
        return adjustableValueHC;
    }

    public void setAdjustableValueHC(int adjustableValueHC) {
        this.adjustableValueHC = adjustableValueHC;
        notifyPropertyChanged(BR.adjustableValueHC);
    }
    @Bindable
    public int getMaxValueHC() {
        return maxValueHC;
    }

    public void setMaxValueHC(int maxValueHC) {
        this.maxValueHC = maxValueHC;
        notifyPropertyChanged(BR.maxValueHC);
    }
    @Bindable
    public String getAdjustableValueNameHC() {
        return adjustableValueNameHC;
    }

    public void setAdjustableValueNameHC(String adjustableValueNameHC) {
        this.adjustableValueNameHC = adjustableValueNameHC;
        notifyPropertyChanged(BR.adjustableValueNameHC);
    }




    @Bindable
    public String getLockStatusHC() {
        return lockStatusHC;
    }

    public void setLockStatusHC(String lockStatusHC) {
        this.lockStatusHC = lockStatusHC;
        notifyPropertyChanged(BR.lockStatusHC);
    }

    public String getPasswordHC() {
        return passwordHC;
    }

    public void setPasswordHC(String passwordHC) {
        this.passwordHC = passwordHC;
    }
    @Bindable
    public String getPasswordInputHC() {
        return passwordInputHC;
    }

    public void setPasswordInputHC(String passwordInputHC) {
        this.passwordInputHC = passwordInputHC;
        notifyPropertyChanged(BR.passwordInputHC);
    }

    public boolean getNFCmodifyPermit() {
        return nFCmodifyPermit;
    }

    public void setNFCmodifyPermit(boolean nFCmodifyPermit) {
        this.nFCmodifyPermit = nFCmodifyPermit;
    }





    public void chooseMode(AdapterView<?> adapterView, View view, int i, long l){
        setModeChosen(i);

    }

    public void confirmMode(View view){

        ModeSwitch modeSwitch=(ModeSwitch)DeviceDataOnHub.getInstance().getDevice(viewModelID);

        modeSwitch.chooseMode(getModeChosen());

        DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice) modeSwitch);
    }
    public void deviceSwitch(View view,boolean b){
        com.example.zeyupeng.smarthome.Model.MyDevices.Switch device = (com.example.zeyupeng.smarthome.Model.MyDevices.Switch) DeviceDataOnHub.getInstance().getDevice(viewModelID);
        if (b){
            if(statusHC.equals(ProductStatus.OFF)){
                device.turnOn();
                DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice)device);

            }
        }else {
            Log.i("switch test","false");
            if(statusHC.equals(ProductStatus.ON)){
                device.turnOff();
                DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice)device);
            }
        }


    }

    public void stopAdjusting(SeekBar seekBar) {
        Log.i("SeekBar","onStopTrackingTouch.............");

        Adjuster adjuster=(Adjuster)DeviceDataOnHub.getInstance().getDevice(viewModelID);
        adjuster.adjust(seekBar.getProgress());
        DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice) adjuster);

    }
    public void inputPassword(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("password",charSequence.toString());
        this.setPasswordInputHC(charSequence.toString());
    }
    public void lockSwitch(View view,boolean lockStatus){
        DoorLock doorLock = (DoorLock) DeviceDataOnHub.getInstance().getDevice(viewModelID);
        if(lockStatus){
            if(this.lockStatusHC.equals(LockStatus.UNLOCKED)){
                doorLock.lock();
                DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice) doorLock);
            }
        }else {
            if (this.lockStatusHC.equals(LockStatus.LOCKED)) {

                if(this.getNFCmodifyPermit()){
                    doorLock.unlock(doorLock.getPassword(),view.getContext());
                    doorLock.setDoorLockModifyPermit(false);
                    DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice) doorLock);
                    this.setPasswordInputHC(null);

                }else{
                    doorLock.unlock(passwordInputHC,view.getContext());
                    if(doorLock.checkPassword(passwordInputHC)){
                        doorLock.setDoorLockModifyPermit(true);
                        DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice) doorLock);
                        this.setPasswordInputHC(null);
                    }else {
                        this.setLockStatusHC(LockStatus.LOCKED);
                    }
                }
            }
        }
    }

    public void startCardReader(View view){
        Intent i = new Intent(view.getContext(), NFCCardReaderDialogActivity.class);
        i.putExtra(IntentTag.CARD_READER.toString(),viewModelID);
        view.getContext().startActivity(i);
    }

    public void modify(HubControlPanelActivityDeviceSimulatorViewModel viewModel){
        this.setCustumNameHC(viewModel.getCustumNameHC());
        this.setProductNameHC(viewModel.getProductNameHC());
        this.setProductTypeHC(viewModel.getProductTypeHC());
        this.setStatusHC(viewModel.getStatusHC());
        this.setDescriptionHC(viewModel.getDescriptionHC());
        this.setImageHC(viewModel.getImageHC());

        this.setModeOptionsHC(viewModel.getModeOptionsHC());
        this.setModeHC(viewModel.getModeHC());

        this.setAdjustableValueHC(viewModel.getAdjustableValueHC());
        this.setAdjustableValueNameHC(viewModel.getAdjustableValueNameHC());
        this.setMaxValueHC(viewModel.getMaxValueHC());

        this.setNFCmodifyPermit(viewModel.getNFCmodifyPermit());
        this.setPasswordHC(viewModel.getPasswordHC());
        this.setLockStatusHC(viewModel.getLockStatusHC());
    }



}
