package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.Oven;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class OvenDataConverter {

    private OvenData mOvenData;

    public OvenData convertToDeviceData(Oven oven){
        this.mOvenData = new OvenData();

        this.mOvenData.setProductID(oven.getProductID());
        this.mOvenData.setProductName(oven.getProductName());
        this.mOvenData.setProductType(oven.getProductType());
        this.mOvenData.setStatus(oven.getStatus());
        this.mOvenData.setCustomName(oven.getCustomName());
        this.mOvenData.setDescription(oven.getDescription());
        this.mOvenData.setLocationID(oven.getLocationID());
        this.mOvenData.setControlerType(oven.getControlerType());

        this.mOvenData.setMode(oven.getMode());

        this.mOvenData.setAdjustableValue(oven.getCurrentValue());

        this.mOvenData.setNotificationPermit(oven.getNotificationPermit());

        return this.mOvenData;
    }
}
