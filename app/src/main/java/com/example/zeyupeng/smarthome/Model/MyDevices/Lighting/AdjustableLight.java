package com.example.zeyupeng.smarthome.Model.MyDevices.Lighting;

import com.amazonaws.models.nosql.AdjustableLightData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.Switch;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.R;

/**
 * Created by zeyu peng on 2017-06-02.
 */

public class AdjustableLight extends AbstractDevice implements Switch, Adjuster {

    private int mAdjustableValue;
    private int mMaxValue;
    private String mAdjustableValueName;

    public AdjustableLight() {
        this.addAdjusterToControlerType();
        this.addSwitchToControlerType();

        this.setAdjustableValueName("Brightness:(%)");
        this.setMaxValue(100);

        this.setDescription(ProductDescription.ADJUSTABLE_LIGHT);
        this.setImage(R.mipmap.adjustable_light);

    }

    public void importData (AdjustableLightData adjustableLightData) {
        this.setProductID(adjustableLightData.getProductID());
        this.setProductName(adjustableLightData.getProductName());
        this.setProductType(adjustableLightData.getProductType());
        this.setStatus(adjustableLightData.getStatus());
        //this.setControlerType(adjustableLightData.getControlerType());
        this.setCustomName(adjustableLightData.getCustomName());
        this.setDescription(adjustableLightData.getDescription());


        this.adjust(adjustableLightData.getAdjustableValue());


    }

    @Override
    public void turnOn() {
        this.setStatus(ProductStatus.ON);
    }

    @Override
    public void turnOff() {
        this.setStatus(ProductStatus.OFF);
    }

    @Override
    public void addSwitchToControlerType() {
        this.getControlerType().add(ControlerType.SWITCH);
    }

    @Override
    public void adjust(int adjustedValue) {
        this.mAdjustableValue =adjustedValue;
    }

    @Override
    public int getCurrentValue() {
        return this.mAdjustableValue;
    }

    @Override
    public void setMaxValue(int maxValue) {
        this.mMaxValue =maxValue;
    }

    @Override
    public int getMaxValue() {
        return this.mMaxValue;
    }

    @Override
    public void setAdjustableValueName(String adjustableValueName) {
        this.mAdjustableValueName=adjustableValueName;
    }

    @Override
    public String getAdjustableValueName() {
        if (this.mAdjustableValueName!=null){
            return this.mAdjustableValueName;
        }else{
            return "adjustable value";
        }

    }

    @Override
    public void addAdjusterToControlerType() {
        this.getControlerType().add(ControlerType.ADJUSTER);
    }


}
