package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.Access.NFCDoor;

/**
 * Created by zeyu peng on 2017-07-31.
 */

public class NFCDoorDataConverter {
    private NFCDoorData mNFCDoorData;

    public NFCDoorData convertToDeviceData(NFCDoor nfcDoor){
        this.mNFCDoorData = new NFCDoorData();

        this.mNFCDoorData.setProductID(nfcDoor.getProductID());
        this.mNFCDoorData.setProductName(nfcDoor.getProductName());
        this.mNFCDoorData.setProductType(nfcDoor.getProductType());
        this.mNFCDoorData.setStatus(nfcDoor.getStatus());
        this.mNFCDoorData.setCustomName(nfcDoor.getCustomName());
        this.mNFCDoorData.setDescription(nfcDoor.getDescription());
        this.mNFCDoorData.setLocationID(nfcDoor.getLocationID());
        this.mNFCDoorData.setControlerType(nfcDoor.getControlerType());

        this.mNFCDoorData.setLockStatus(nfcDoor.getLockStatus());
        this.mNFCDoorData.setPassword(nfcDoor.getPassword());

        this.mNFCDoorData.setNotificationPermit(nfcDoor.getNotificationPermit());

        return this.mNFCDoorData;
    }
}
