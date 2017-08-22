package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.Access.GarageDoor;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class GarageDoorDataConverter {
    private GarageDoorData mGarageDoorData;

    public GarageDoorData convertToDeviceData(GarageDoor garageDoor){
        this.mGarageDoorData = new GarageDoorData();

        this.mGarageDoorData.setProductID(garageDoor.getProductID());
        this.mGarageDoorData.setProductName(garageDoor.getProductName());
        this.mGarageDoorData.setProductType(garageDoor.getProductType());
        this.mGarageDoorData.setStatus(garageDoor.getStatus());
        this.mGarageDoorData.setCustomName(garageDoor.getCustomName());
        this.mGarageDoorData.setDescription(garageDoor.getDescription());
        this.mGarageDoorData.setLocationID(garageDoor.getLocationID());
        this.mGarageDoorData.setControlerType(garageDoor.getControlerType());

        this.mGarageDoorData.setLockStatus(garageDoor.getLockStatus());
        this.mGarageDoorData.setPassword(garageDoor.getPassword());

        this.mGarageDoorData.setNotificationPermit(garageDoor.getNotificationPermit());

        return this.mGarageDoorData;
    }
}
