package com.amazonaws.models.nosql;

import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.RangeHood;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class RangeHoodDataConverter {
    private RangeHoodData mRangeHoodData;

    public RangeHoodData convertToDeviceData(RangeHood rangeHood){
        this.mRangeHoodData = new RangeHoodData();

        this.mRangeHoodData.setProductID(rangeHood.getProductID());
        this.mRangeHoodData.setProductName(rangeHood.getProductName());
        this.mRangeHoodData.setProductType(rangeHood.getProductType());
        this.mRangeHoodData.setStatus(rangeHood.getStatus());
        this.mRangeHoodData.setCustomName(rangeHood.getCustomName());
        this.mRangeHoodData.setDescription(rangeHood.getDescription());
        this.mRangeHoodData.setLocationID(rangeHood.getLocationID());
        this.mRangeHoodData.setControlerType(rangeHood.getControlerType());

        this.mRangeHoodData.setMode(rangeHood.getMode());

        return this.mRangeHoodData;
    }
}
