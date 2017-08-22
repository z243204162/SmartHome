package com.example.zeyupeng.smarthome.Model.MyDevices.Cooking;

import com.amazonaws.models.nosql.ColorLightData;
import com.amazonaws.models.nosql.RangeHoodData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLightMode;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.Switch;
import com.example.zeyupeng.smarthome.R;

/**
 * Created by zeyu peng on 2017-06-02.
 */

public class RangeHood extends AbstractDevice implements Switch, ModeSwitch{

    private String mMode;
    private static String[] mModeOptions= {
            RangHoodMode.LOW,
            RangHoodMode.MEDIUM,
            RangHoodMode.HIGH
    };

    public RangeHood() {
        this.addSwitchToControlerType();
        this.addModeSwitchToControlerType();

        this.setDescription(ProductDescription.RANGE_HOOD);
        this.setImage(R.mipmap.rangehood);
    }


    public void importData (RangeHoodData rangeHoodData) {
        this.setProductID(rangeHoodData.getProductID());
        this.setProductName(rangeHoodData.getProductName());
        this.setProductType(rangeHoodData.getProductType());
        this.setStatus(rangeHoodData.getStatus());
        //this.setControlerType(colorLightData.getControlerType());
        this.setCustomName(rangeHoodData.getCustomName());
        this.setDescription(rangeHoodData.getDescription());
        this.setLocationID(rangeHoodData.getLocationID());


        this.setMode(rangeHoodData.getMode());

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
    public String[] getModeOption() {
        if (this.mModeOptions!=null){
            return this.mModeOptions;
        }else{
            return null;
        }

    }
    @Override
    public void chooseMode(int choice) {
        if (this.mModeOptions!=null){
            this.mMode=mModeOptions[choice];
        }else{
            this.mMode="Default";
        }
    }
    @Override
    public String getMode() {
        if(this.mMode!=null){
            return this.mMode;
        }else{
            return "Default";
        }

    }
    @Override
    public void setMode(String Mode) {
        this.mMode = Mode;
    }



    @Override
    public void addModeSwitchToControlerType() {
        this.getControlerType().add(ControlerType.MODE_SWITCH);
    }
}
