package com.example.zeyupeng.smarthome;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.amazonaws.mobile.AWSMobileClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zeyu peng on 2017-06-10.
 */

public class IDGenerater {
    private static volatile IDGenerater ourInstance;
    private static int countNumber;

    public static IDGenerater getInstance() {
        if (ourInstance == null) {
            synchronized (IDGenerater.class) {
                if (ourInstance == null) {
                    ourInstance = new IDGenerater();
                }
            }
        }
        return ourInstance;
    }



    public String generateID(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date();
        String ID= format.format(curDate)
                +AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID()
                +String.valueOf(countNumber);
        countNumber++;
        Log.i("iiiiiiiiiiddd",ID);
        return ID;
    }

    public String generatePlanID(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date();
        String ID= format.format(curDate)
                +String.valueOf(countNumber);
        countNumber++;
        return ID;
    }

    public String generateHomeID(){
        String HomeID = AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID();
        return HomeID;
    }

}
