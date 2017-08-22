package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class ACDataConverter {

    private ACData mACData;

    public ACData convertToDeviceData(AC ac){
        this.mACData = new ACData();

        this.mACData.setProductID(ac.getProductID());
        this.mACData.setProductName(ac.getProductName());
        this.mACData.setProductType(ac.getProductType());
        this.mACData.setStatus(ac.getStatus());
        this.mACData.setCustomName(ac.getCustomName());
        this.mACData.setDescription(ac.getDescription());
        this.mACData.setLocationID(ac.getLocationID());
        this.mACData.setControlerType(ac.getControlerType());

        this.mACData.setMode(ac.getMode());

        this.mACData.setAdjustableValue(ac.getCurrentValue());


        return this.mACData;
    }
}
