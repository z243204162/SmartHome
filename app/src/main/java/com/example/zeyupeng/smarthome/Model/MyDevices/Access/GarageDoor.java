package com.example.zeyupeng.smarthome.Model.MyDevices.Access;

import android.content.Context;
import android.widget.Toast;

import com.amazonaws.models.nosql.GarageDoorData;
import com.amazonaws.models.nosql.NFCDoorData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.R;

/**
 * Created by zeyu peng on 2017-06-02.
 */

public class GarageDoor extends AbstractDevice implements DoorLock,NotificationSender{
    private String lockStatus;
    private String password;
    private boolean modifyPermit;

    private boolean notificationPermit;

    public GarageDoor() {
        this.addNotificationSenderToControlerType();
        this.addDoorLockToControlType();

        this.setDescription(ProductDescription.GARAGE_DOOR);
        this.initPassword("000000");

        this.setImage(R.mipmap.garage_door);
    }

    public void importData(GarageDoorData garageDoorData){
        this.setProductID(garageDoorData.getProductID());
        this.setProductName(garageDoorData.getProductName());
        this.setProductType(garageDoorData.getProductType());
        this.setStatus(garageDoorData.getStatus());
        //this.setControlerType(colorLightData.getControlerType());
        this.setCustomName(garageDoorData.getCustomName());
        this.setDescription(garageDoorData.getDescription());
        this.setLocationID(garageDoorData.getLocationID());

        this.setLockStatus(garageDoorData.getLockStatus());
        this.setPassword(garageDoorData.getPassword());

        this.setNotificationPermit(garageDoorData.getNotificationPermit());
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
                    return "GARAGE DOOR is changed, now it is locked";
                case LockStatus.UNLOCKED:
                    return "GARAGE DOOR is changed, now it is unlocked";
                default:
                    return "GARAGE DOOR is changed";
            }
        }else {
            return "GARAGE DOOR is changed";
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



}
