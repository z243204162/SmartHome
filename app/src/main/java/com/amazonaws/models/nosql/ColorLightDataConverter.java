package com.amazonaws.models.nosql;


import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;

/**
 * Created by zeyu peng on 2017-06-08.
 */

public class ColorLightDataConverter  {

    private ColorLightData mColorLightData;

    public ColorLightData convertToDeviceData(ColorLight colorLight){
        this.mColorLightData = new ColorLightData();

        this.mColorLightData.setProductID(colorLight.getProductID());
        this.mColorLightData.setProductName(colorLight.getProductName());
        this.mColorLightData.setProductType(colorLight.getProductType());
        this.mColorLightData.setStatus(colorLight.getStatus());
        this.mColorLightData.setCustomName(colorLight.getCustomName());
        this.mColorLightData.setDescription(colorLight.getDescription());
        this.mColorLightData.setLocationID(colorLight.getLocationID());
        this.mColorLightData.setControlerType(colorLight.getControlerType());

        this.mColorLightData.setMode(colorLight.getMode());

        return this.mColorLightData;
    }

}
