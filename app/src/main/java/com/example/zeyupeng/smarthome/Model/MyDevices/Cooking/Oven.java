package com.example.zeyupeng.smarthome.Model.MyDevices.Cooking;




import com.amazonaws.models.nosql.OvenData;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductDescription;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.R;

import java.util.Timer;

/**
 * Created by zeyu peng on 2017-05-25.
 */

public class Oven extends AbstractDevice
        implements com.example.zeyupeng.smarthome.Model.MyDevices.Switch
        ,ModeSwitch
        ,Adjuster
        ,com.example.zeyupeng.smarthome.Model.MyDevices.Timer
        ,NotificationSender{



    private String mMode;
    private static String[] mModeOptions= {
            OvenMode.BAKE,
            OvenMode.GRILL,
            OvenMode.CLEAN
    };


    private int mAdjustableValue;
    private int mMaxValue;
    private String mAdjustableValueName;

    private boolean notificationPermit;

    public Oven() {
        this.addNotificationSenderToControlerType();
        this.addSwitchToControlerType();
        this.addTimerToControlerType();
        this.addAdjusterToControlerType();
        this.addModeSwitchToControlerType();

        this.setAdjustableValueName("Temperature:(°F)");
        this.setMaxValue(600);

        this.setDescription(ProductDescription.OVEN);
        this.setImage(R.mipmap.oven);
    }

    public void importData (OvenData ovenData) {

        this.setProductID(ovenData.getProductID());
        this.setProductName(ovenData.getProductName());
        this.setProductType(ovenData.getProductType());
        this.setStatus(ovenData.getStatus());
        this.setCustomName(ovenData.getCustomName());
        this.setDescription(ovenData.getDescription());
        this.setLocationID(ovenData.getLocationID());

        this.setMode(ovenData.getMode());

        this.adjust(ovenData.getAdjustableValue());

        this.setNotificationPermit(ovenData.getNotificationPermit());

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


    @Override
    public String createNotification() {
        switch (this.getStatus()){
            case ProductStatus.ON:
                return "Oven is ready! Now is "+this.getCurrentValue()+"°C";
            case ProductStatus.OFF:
                return "Oven has been turned off";
            default:
                return "Oven is changed";
        }
    }

    @Override
    public void addNotificationSenderToControlerType() {
        this.getControlerType().add(ControlerType.NOTIFICATION_SENDER);
    }

    @Override
    public boolean getNotificationPermit() {
        return this.notificationPermit;
    }

    @Override
    public void setNotificationPermit(boolean notificationPermit) {
        this.notificationPermit = notificationPermit;
    }
}
