package com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating;

import com.amazonaws.models.nosql.ACData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.Switch;
import com.example.zeyupeng.smarthome.Model.MyDevices.Timer;
import com.example.zeyupeng.smarthome.R;

/**
 * Created by zeyu peng on 2017-06-02.
 */

public class AC extends AbstractDevice implements Switch, ModeSwitch, Adjuster, Timer{
    private String mMode;
    private static String[] mModeOptions= {
            ACMode.COOLING,
            ACMode.HEATING,
            ACMode.VENTILATING

    };


    private int mAdjustableValue;
    private int mMaxValue;
    private String mAdjustableValueName;

   

    public AC() {
        this.addSwitchToControlerType();
        this.addTimerToControlerType();
        this.addAdjusterToControlerType();
        this.addModeSwitchToControlerType();

        this.setAdjustableValueName("Temperature:(°C)");
        this.setMaxValue(30);

        this.setDescription(ProductDescription.AC);
        this.setImage(R.mipmap.ac);
    }

    public void importData (ACData acData) {

        this.setProductID(acData.getProductID());
        this.setProductName(acData.getProductName());
        this.setProductType(acData.getProductType());
        this.setStatus(acData.getStatus());
        this.setCustomName(acData.getCustomName());
        this.setDescription(acData.getDescription());
        this.setLocationID(acData.getLocationID());

        this.setMode(acData.getMode());

        this.adjust(acData.getAdjustableValue());

    }

    @Override
    public void addTimerToControlerType() {
        this.getControlerType().add(ControlerType.TIMER);
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
