package com.example.zeyupeng.smarthome.Model.DataOnHub;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.MyHome.AbstractHome;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

/**
 * Created by zeyu peng on 2017-06-28.
 */

public class HomeDataOnHub {
    private static volatile HomeDataOnHub ourInstance;

    public static HomeDataOnHub getInstance() {
        if (ourInstance == null) {
            synchronized (HomeDataOnHub.class) {
                if (ourInstance == null) {
                    ourInstance = new HomeDataOnHub();
                }
            }
        }
        return ourInstance;
    }



    private AbstractHome mHome;

    private HomeDataOnHub() {
        this.mHome = new Home();
        Log.i("singleton","homedata constructor");
    }

    public AbstractHome getHome() {
        return mHome;
    }

    public void modifyHome(AbstractHome home) {
        Log.i("hhhhhhub","home"+home.getHomeID());
        mHome = home;
    }


}
