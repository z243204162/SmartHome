package com.example.zeyupeng.smarthome.Model.MyHome;

import com.example.zeyupeng.smarthome.IDGenerater;

/**
 * Created by zeyu peng on 2017-06-23.
 */

public class HomeFactory {
    public AbstractHome createHome(){
        IDGenerater IDGenerater =new IDGenerater();
        AbstractHome home = new Home();

        home.setHomeID(IDGenerater.generateHomeID());
        return home;
    }
}
