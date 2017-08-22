package com.example.zeyupeng.smarthome.Model.DataOnMobile;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.MyHome.AbstractHome;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-06-23.
 */

public class HomeDataOnMobile {
    private static volatile HomeDataOnMobile ourInstance;

    public static HomeDataOnMobile getInstance() {
        if (ourInstance == null) {
            synchronized (HomeDataOnMobile.class) {
                if (ourInstance == null) {
                    ourInstance = new HomeDataOnMobile();
                }
            }
        }
        return ourInstance;
    }

    private HomeDataOnMobile() {
        this.mHome = new Home();
        Log.i("singleton","homedata constructor");
    }


    private AbstractHome mHome;

    public AbstractHome getHome() {
        return mHome;
    }

    public void modifyHome(AbstractHome home) {
        mHome = home;
    }



}
