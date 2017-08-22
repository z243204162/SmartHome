package com.example.zeyupeng.smarthome.Model.MyDevices.Lighting;

import com.amazonaws.models.nosql.ColorLightData;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.Switch;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.Timer;
import com.example.zeyupeng.smarthome.R;


/**
 * Created by zeyu peng on 2017-05-25.
 */

public class ColorLight extends AbstractDevice implements Switch , ModeSwitch{

    private String mMode;
    private static String[] mModeOptions= {
        ColorLightMode.WHITE,
                ColorLightMode.YELLOW,
                ColorLightMode.GREEN,
                ColorLightMode.ORANGE
    };

    public ColorLight() {
        this.addSwitchToControlerType();
        this.addModeSwitchToControlerType();

        this.setImage(R.mipmap.color_light);
        this.setDescription(ProductDescription.COLOR_LIGHT);
    }


    public void importData (ColorLightData colorLightData) {
        this.setProductID(colorLightData.getProductID());
        this.setProductName(colorLightData.getProductName());
        this.setProductType(colorLightData.getProductType());
        this.setStatus(colorLightData.getStatus());
        //this.setControlerType(colorLightData.getControlerType());
        this.setCustomName(colorLightData.getCustomName());
        this.setDescription(colorLightData.getDescription());
        this.setLocationID(colorLightData.getLocationID());


        this.setMode(colorLightData.getMode());

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