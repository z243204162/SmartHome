package com.example.zeyupeng.smarthome.Model.MyDevices;

/**
 * Created by zeyu peng on 2017-05-25.
 */

public interface Adjuster {

    void adjust (int adjustedValue);
    int getCurrentValue();

    void setMaxValue(int maxValue);
    int getMaxValue();

    void setAdjustableValueName(String adjustableValueName);
    String getAdjustableValueName();

    void addAdjusterToControlerType();


}
