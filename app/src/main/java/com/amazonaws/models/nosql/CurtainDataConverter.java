package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;
import com.example.zeyupeng.smarthome.Model.MyDevices.Shading.Curtain;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class CurtainDataConverter {

    private CurtainData mCurtainData;

    public CurtainData convertToDeviceData(Curtain curtain){
        this.mCurtainData = new CurtainData();

        this.mCurtainData.setProductID(curtain.getProductID());
        this.mCurtainData.setProductName(curtain.getProductName());
        this.mCurtainData.setProductType(curtain.getProductType());
        this.mCurtainData.setStatus(curtain.getStatus());
        this.mCurtainData.setCustomName(curtain.getCustomName());
        this.mCurtainData.setDescription(curtain.getDescription());
        this.mCurtainData.setLocationID(curtain.getLocationID());
        this.mCurtainData.setControlerType(curtain.getControlerType());

        this.mCurtainData.setMode(curtain.getMode());

        this.mCurtainData.setAdjustableValue(curtain.getCurrentValue());


        return this.mCurtainData;
    }
}
