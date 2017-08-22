package com.example.zeyupeng.smarthome;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.ColorLightData;
import com.amazonaws.models.nosql.HomeData;
import com.amazonaws.models.nosql.PlanData;
import com.amazonaws.models.nosql.RoomData;
import com.example.zeyupeng.smarthome.Model.Cloud.DownloadTool;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HomeDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HomeDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;
import com.example.zeyupeng.smarthome.PlansandTimeTasks.AlarmReceiver;
import com.example.zeyupeng.smarthome.PlansandTimeTasks.PlanDatabaseManager;
import com.example.zeyupeng.smarthome.PubNub.PushNotification.RegistrationIntentService;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityDeviceSimulatorViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityHeaderViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubRoomsActivityRoomViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityPlansViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityRoomViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityViewModel;
import com.example.zeyupeng.smarthome.UI.ViewModel.RoomControlPanelActivityDeviceControlerViewModelMap;
import com.example.zeyupeng.smarthome.UI.ViewModel.RoomControlPanelActivityHeaderViewModelMap;
import com.example.zeyupeng.smarthome.View.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zeyu peng on 2017-07-10.
 */

public class Initializer  extends AsyncTask<Void,Void,Void> {
    private Activity mActivity;
    private PlanData planData;

    public Initializer(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("asyyyyyyyntask","start onpreexe");
        registerAlarmService();
        initSettings();
        registerObservers();
        startPubNubPNService();
        Log.i("asyyyyyyyntask","end onpreexe");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.i("asyyyyyyyntask","start doinbackg");
        //initData();
        new DownloadTool(DownloadTool.DOWNLOAD_MOBILE_DATA).downloadData();
        publishProgress();
        Log.i("asyyyyyyyntask","end doinbackg");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("asyyyyyyyntask","on post exe");
        Intent intent =new Intent(this.mActivity, MainActivity.class);
        this.mActivity.startActivity(intent);
        this.mActivity.finish();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        /*
        if (planData!=null){
            for(Map.Entry<String, Plan> entryForPlan:planData.getPlanMap().entrySet()){
                PlanDataOnMobile.getInstance().addPlan(entryForPlan.getValue());
                PlanDataOnHub.getInstance().addPlan(entryForPlan.getValue());
                Log.i("asyyyyyyyntask","onProgressUpdate"+entryForPlan.getValue().getPlanID());
            }
            Log.i("asyyyyyyyntask","onProgressUpdate");

        }

*/

    }

    public void initSettings(){
        SharedPreferences userInfo = this.mActivity.getSharedPreferences(SettingKeys.SMART_HOME_SETTING, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = userInfo.edit();
        if (!userInfo.contains(SettingKeys.INITIALIZED)) {
            Log.i("settingggggggg","init setting     uninitialized");
            editor.putBoolean(SettingKeys.INITIALIZED, true);//must set this to false when signing out!!!!!!!!!!!!!!
            editor.putInt(SettingKeys.ONLINE_DETECTOR_TIME_INTERVAL,15);
            editor.putInt(SettingKeys.UPLOADOR_TIME_INTERVAL,5);
            editor.commit();
        }else {
            Log.i("settingggggggg","init setting     initialized");
        }
    }


    public void startPubNubPNService(){
        Intent intentService = new Intent(this.mActivity, RegistrationIntentService.class);
        this.mActivity.startService(intentService);
    }

    public void registerAlarmService(){
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");
        Date curDate = new Date();
        String hour = hourFormat.format(curDate);
        String min = minFormat.format(curDate);

        Calendar mCalendar= Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        Log.i("calendar",mCalendar.getTime()+"........"+Integer.parseInt(hour)+":"+Integer.parseInt(min)+"........."+mCalendar.getTimeInMillis());

        mCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        //设置在几分提醒  设置的为25分
        mCalendar.set(Calendar.MINUTE, Integer.parseInt(min));
        //下面这两个看字面意思也知道
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);


        Intent intent = new Intent(this.mActivity, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this.mActivity, 0, intent, 0);


        AlarmManager am = (AlarmManager)this.mActivity.getSystemService(ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60), pi);
    }

    public void registerObservers(){
        DeviceDataOnHub.getInstance().registerObserver(HubControlPanelActivityDeviceSimulatorViewModelMap.getInstance());
        RoomDataOnHub.getInstance().registerObserver(HubControlPanelActivityHeaderViewModelMap.getInstance());
        RoomDataOnHub.getInstance().registerObserver(HubRoomsActivityRoomViewModelMap.getInstance());


        RoomDataOnMobile.getInstance().registerObserver(MainActivityRoomViewModelMap.getInstance());
        RoomDataOnMobile.getInstance().registerObserver(RoomControlPanelActivityHeaderViewModelMap.getInstance());
        DeviceDataOnMobile.getInstance().registerObserver(RoomControlPanelActivityDeviceControlerViewModelMap
                .getInstance());


        PlanDataOnMobile.getInstance().registerObserver(MainActivityPlansViewModelMap.getInstance());


        HubStatusDataOnMobile.getInstance().registerObserver(RoomControlPanelActivityHeaderViewModelMap.getInstance());
        HubStatusDataOnMobile.getInstance().registerObserver(MainActivityViewModel.getInstance());
    }


}
