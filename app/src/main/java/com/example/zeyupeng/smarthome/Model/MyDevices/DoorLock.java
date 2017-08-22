package com.example.zeyupeng.smarthome.Model.MyDevices;

import android.content.Context;

/**
 * Created by zeyu peng on 2017-07-31.
 */

public interface DoorLock {
    void addDoorLockToControlType();
    void initPassword(String password);
    void resetPassword(String oldPassword, String newPassword, Context context);
    boolean checkPassword(String password);
    void lock();
    void unlock(String password,Context context);

    String getLockStatus();



    boolean getDoorLockModifyPermit();

    void setDoorLockModifyPermit(boolean modifyPermit);

    String getPassword();
}
