package com.example.zeyupeng.smarthome.Model.MyDevices.Access;

import android.content.Context;
import android.widget.Toast;

import com.amazonaws.models.nosql.NFCDoorData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.NFCDoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.Switch;
import com.example.zeyupeng.smarthome.R;

/**
 * Created by zeyu peng on 2017-06-02.
 */

public class NFCDoor extends AbstractDevice implements NotificationSender,DoorLock,NFCDoorLock{

    private String lockStatus;
    private String password;
    private boolean modifyPermit;

    private boolean notificationPermit;

    public NFCDoor() {
        this.addNotificationSenderToControlerType();
        this.addDoorLockToControlType();
        this.addNFCDoorLockToControlType();

        this.setDescription(ProductDescription.NFC_DOOR);
        this.initPassword("000000");

        this.setImage(R.mipmap.nfc_door);
    }

    public void importData(NFCDoorData nfcDoorData){
        this.setProductID(nfcDoorData.getProductID());
        this.setProductName(nfcDoorData.getProductName());
        this.setProductType(nfcDoorData.getProductType());
        this.setStatus(nfcDoorData.getStatus());
        //this.setControlerType(colorLightData.getControlerType());
        this.setCustomName(nfcDoorData.getCustomName());
        this.setDescription(nfcDoorData.getDescription());
        this.setLocationID(nfcDoorData.getLocationID());

        this.setLockStatus(nfcDoorData.getLockStatus());
        this.setPassword(nfcDoorData.getPassword());

        this.setNotificationPermit(nfcDoorData.getNotificationPermit());
    }

    @Override
    public String getLockStatus() {
        if(lockStatus!=null){
            return lockStatus;
        } else {
            return LockStatus.UNLOCKED;
        }

    }


    private void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    @Override
    public boolean getDoorLockModifyPermit() {
        return this.modifyPermit;
    }
    @Override
    public void setDoorLockModifyPermit(boolean modifyPermit) {
        this.modifyPermit = modifyPermit;
    }
    @Override
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void addDoorLockToControlType() {
        this.getControlerType().add(ControlerType.DOOR_LOCK);
    }

    @Override
    public void initPassword(String password) {
        this.setPassword(password);
    }

    @Override
    public void resetPassword(String oldpassword, String newPassword, Context context) {
        if(this.checkPassword(oldpassword)){
            this.setPassword(newPassword);
            Toast.makeText(context,"password has been changed!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"password is not correct!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean checkPassword(String password) {
        if(password!=null&&password.equals(this.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void lock() {
        this.setLockStatus(LockStatus.LOCKED);
    }

    @Override
    public void unlock(String password,Context context) {
        if(this.checkPassword(password)){
            this.setLockStatus(LockStatus.UNLOCKED);
        }else {
            Toast.makeText(context,"password is not correct!",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public String createNotification() {
        if(this.getLockStatus()!=null){
            switch (this.getLockStatus()){
                case LockStatus.LOCKED:
                    return "NFC DOOR is changed, now it is locked!";
                case LockStatus.UNLOCKED:
                    return "NFC DOOR is changed, now it is unlocked!";
                default:
                    return "NFC DOOR is changed.";
            }
        }else {
            return "(NFC DOOR) is changed!";
        }
    }

    @Override
    public void addNotificationSenderToControlerType() {
        this.getControlerType().add(ControlerType.NOTIFICATION_SENDER);
    }

    @Override
    public boolean getNotificationPermit() {
        return this.notificationPermit;
    }

    @Override
    public void setNotificationPermit(boolean notificationPermit) {
        this.notificationPermit = notificationPermit;
    }

    @Override
    public void addNFCDoorLockToControlType() {
        this.getControlerType().add(ControlerType.NFC_DOOR_LOCK);
    }


}
