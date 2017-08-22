package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.AdjustableLight;

/**
 * Created by zeyu peng on 2017-06-10.
 */

public class AdjustableLightDataConverter {

    private AdjustableLightData mAdjustableLightData;

    public AdjustableLightData convertToDeviceData(AdjustableLight adjustableLight){
        this.mAdjustableLightData = new AdjustableLightData();
        this.mAdjustableLightData.setProductID(adjustableLight.getProductID());
        this.mAdjustableLightData.setProductName(adjustableLight.getProductName());
        this.mAdjustableLightData.setProductType(adjustableLight.getProductType());
        this.mAdjustableLightData.setStatus(adjustableLight.getStatus());
        this.mAdjustableLightData.setCustomName(adjustableLight.getCustomName());
        this.mAdjustableLightData.setDescription(adjustableLight.getDescription());
        this.mAdjustableLightData.setLocationID(adjustableLight.getLocationID());
        this.mAdjustableLightData.setControlerType(adjustableLight.getControlerType());

        this.mAdjustableLightData.setAdjustableValue(adjustableLight.getCurrentValue());


        return this.mAdjustableLightData;
    }
}
