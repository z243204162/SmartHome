package com.example.zeyupeng.smarthome.Model.MyDevices.Shading;

import com.amazonaws.models.nosql.ACData;
import com.amazonaws.models.nosql.CurtainData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.ACMode;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.R;

/**
 * Created by zeyu peng on 2017-06-02.
 */

public class Curtain extends AbstractDevice implements Adjuster,ModeSwitch{

    private String mMode;
    private static String[] mModeOptions= {
            CurtainMode.AUTOMATIC,
            CurtainMode.MANUAL

    };


    private int mAdjustableValue;
    private int mMaxValue;
    private String mAdjustableValueName;


    public Curtain() {
        this.addAdjusterToControlerType();
        this.addModeSwitchToControlerType();

        this.setAdjustableValueName("OPEN:(%)");
        this.setMaxValue(100);

        this.setDescription(ProductDescription.CURTAIN);
        this.setImage(R.mipmap.curtain);
    }

    public void importData (CurtainData curtainData) {

        this.setProductID(curtainData.getProductID());
        this.setProductName(curtainData.getProductName());
        this.setProductType(curtainData.getProductType());
        this.setStatus(curtainData.getStatus());
        this.setCustomName(curtainData.getCustomName());
        this.setDescription(curtainData.getDescription());
        this.setLocationID(curtainData.getLocationID());

        this.setMode(curtainData.getMode());

        this.adjust(curtainData.getAdjustableValue());

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
