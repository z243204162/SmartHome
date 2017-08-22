package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh.RefreshToken;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by zeyu peng on 2017-07-26.
 */

public class StreamProgressPoster implements  Observer{

    private ProgressDialog mProgressDialog;
    private Context mContext;
    private Object mObject;
    private String mPackageType;
    private String mMessageID;
    private Timer mStreamTimer;
    private Timer mRefreshTimer;
    private TimerTask mRefreshTimerTask;
    private TimerTask mStreamTimerTask;
    private boolean mTimerTaskToken;

    private SwipeRefreshLayout mRefreshLayout;

    public StreamProgressPoster(Context context, String packageType, String messageID) {
        mContext = context;
        mPackageType=packageType;
        mMessageID=messageID;
        mProgressDialog=new ProgressDialog(context);
        //mStreamTimer =new Timer();
        mTimerTaskToken=true;
        registerPoster(packageType);
    }

    public StreamProgressPoster(Context context, String packageType, View view) {
        mContext = context;
        mPackageType=packageType;
        mRefreshLayout=(SwipeRefreshLayout)view;
        mProgressDialog=new ProgressDialog(context);
        //mRefreshTimer =new Timer();
        mTimerTaskToken=true;
        registerPoster(packageType);

    }

    public void startRefreshing(){

        Log.i("SwipeRefreshLayout","startRefreshing called");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(mTimerTaskToken){
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("SwipeRefreshLayout","timer started");
                            mRefreshLayout.setRefreshing(false);
                            Toast.makeText(mContext,"Connection Timeout, please try again later",Toast.LENGTH_SHORT).show();
                            mTimerTaskToken=true;
                        }
                    });
                }
            }
        },10*1000);



    }
    public void startSteaming(){

        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Waiting for feedback from Hub...");
        mProgressDialog.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("streamposter","timer task is running ......... the token is...."+mTimerTaskToken);
                        if(mTimerTaskToken) {

                            Toast.makeText(mContext, "Connection Timeout, please try again later", Toast.LENGTH_SHORT).show();
                            try {
                                mProgressDialog.dismiss();
                            } catch (Exception e) {
                            }
                            mTimerTaskToken=true;
                        }
                    }
                });
            }
        },10*1000);

    }

    public void registerPoster(String packageType){
        Log.i("streamposter","register called......."+this+"........"+packageType);
        switch (packageType){
            case PackageType.PACKAGE_TYPE_ROOM:
                RoomDataOnMobile.getInstance().registerObserver(this);
                break;
            case PackageType.PACKAGE_TYPE_DEVICE:
                DeviceDataOnMobile.getInstance().registerObserver(this);
                break;
            case PackageType.PACKAGE_TYPE_PLAN:
                PlanDataOnMobile.getInstance().registerObserver(this);
                break;
            case PackageType.PACKAGE_TYPE_HUB_STATUS:
                HubStatusDataOnMobile.getInstance().registerObserver(this);
                break;
        }
    }


    @Override
    public void update(ObserverActions action, Object object) {

        mObject=object;
        Log.i("streamposter","update called..............."+this);
        DeviceDataOnMobile.getInstance().removeObserver(this);

        switch (mPackageType){

            case PackageType.PACKAGE_TYPE_ROOM:
                Log.i("streamposter","update called .......package type........"+mPackageType);


                if(((AbstractRoom)mObject).getRoomID().equals(mMessageID)){
                    mTimerTaskToken=false;
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();
                            Toast.makeText(
                                    mContext
                                    ,((AbstractRoom)mObject).getRoomName()+" has been successfully changed"
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                break;


            case PackageType.PACKAGE_TYPE_DEVICE:
                Log.i("streamposter","update called .......package type........"+mPackageType);


                Log.i("streamposter","update called .......mTimerTaskToken........."+mTimerTaskToken);
                Log.i("streamposter","update called .......is id correct?........."+((AbstractDevice)mObject).getProductID().equals(mMessageID));
                if(((AbstractDevice)mObject).getProductID().equals(mMessageID)){
                    mTimerTaskToken=false;
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();
                            Toast.makeText(
                                    mContext
                                    ,"Device "+((AbstractDevice)mObject).getCustomName()+" has been successfully changed"
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                break;



            case PackageType.PACKAGE_TYPE_PLAN:


                if(((Plan)mObject).getPlanID().equals(mMessageID)){
                    mTimerTaskToken=false;
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();
                            Toast.makeText(mContext,"Plan has been successfully changed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;

            case PackageType.PACKAGE_TYPE_HUB_STATUS:
                if(object!=null){
                    if(((String)object).equals(RefreshToken.REFRESH_TOKEN)){
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTimerTaskToken=false;
                                Log.i("SwipeRefreshLayout","refreshed");
                                mRefreshLayout.setRefreshing(false);
                                Toast.makeText(mContext,"Successfully refreshed",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                break;
        }

    }
}
