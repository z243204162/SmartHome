package com.example.zeyupeng.smarthome.Model.MyDevices;


/**
 * Created by zeyu peng on 2017-05-25.
 */

public interface ModeSwitch {

    String[] getModeOption ();

    void chooseMode (int choice);

    void setMode(String mode);
    String getMode();


    void addModeSwitchToControlerType();




}
