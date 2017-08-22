package com.example.zeyupeng.smarthome;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.zeyupeng.smarthome.Model.Cloud.DownloadTool;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HomeDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityDeviceSimulatorViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityHeaderViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubRoomsActivityRoomViewModelMap;
import com.example.zeyupeng.smarthome.View.HubRoomsActivity;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-08-17.
 */

public class HubInitializer extends AsyncTask<Void,Void,Void> {

    private Context mContext;
    private ProgressDialog mDialog;
    public HubInitializer(Context context) {
        mContext = context;
        mDialog=new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        mDialog.setCancelable(false);
        mDialog.setMessage("Initializing Hub Simulator...");
        mDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HomeDataOnHub.getInstance().getHome().getRoomMap().clear();
                RoomDataOnHub.getInstance().getRoomDataMap().clear();
                DeviceDataOnHub.getInstance().getDeviceDataMap().clear();
                PlanDataOnHub.getInstance().getPlanDataMap().clear();

                HubRoomsActivityRoomViewModelMap.getInstance().getViewModelMap().clear();
                HubControlPanelActivityDeviceSimulatorViewModelMap.getInstance().getViewModelMap().clear();
                HubControlPanelActivityHeaderViewModelMap.getInstance().getViewModelMap().clear();
            }
        }).start();

        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        new DownloadTool(DownloadTool.DOWNLOAD_HUB_DATA).downloadData();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mDialog.dismiss();
        Intent i = new Intent(mContext, HubRoomsActivity.class);
        mContext.startActivity(i);
        super.onPostExecute(aVoid);
    }
}
