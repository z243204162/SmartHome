package com.example.zeyupeng.smarthome.PlansandTimeTasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Switch;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.example.zeyupeng.smarthome.Model.MyPlans.PlanActions.TURN_OFF;
import static com.example.zeyupeng.smarthome.Model.MyPlans.PlanActions.TURN_ON;


/**
 * Created by zeyu peng on 2017-05-26.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private Plan mDisposablePlan;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("alarmreceived","on received");
        scanPlans();

    }

    public void scanPlans() {
        //PlanDatabaseManager databaseManager = new PlanDatabaseManager(this.mContext);
        //PlanData planData = databaseManager.exportToPlanData();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
        Date curDate = new Date();
        String hour = hourFormat.format(curDate);
        String min = minFormat.format(curDate);
        String day = dayFormat.format(curDate);

        for (Map.Entry<String, Plan> entry : PlanDataOnHub.getInstance().getPlanDataMap().entrySet()) {
            if(entry.getValue().getRepeatDate().size()==0){
                if(entry.getValue().getHour()==Integer.parseInt(hour)&&entry.getValue().getMinute()==Integer.parseInt(min)){
                    Log.i("alarmreceived",".................."+day);
                    executePlan(entry.getValue());
                    mDisposablePlan =entry.getValue();
                }

            }
            if(entry.getValue().getRepeatDate().contains(day)){
                if(entry.getValue().getHour()==Integer.parseInt(hour)&&entry.getValue().getMinute()==Integer.parseInt(min)){
                    Log.i("alarmreceived",".................."+day);
                    executePlan(entry.getValue());
                }
            }
        }
        if(mDisposablePlan !=null){
            PlanDataOnHub.getInstance().removePlan(mDisposablePlan);
        }
    }

    public void executePlan (Plan plan){
        try {
            Switch device= (Switch)DeviceDataOnHub.getInstance().getDevice(plan.getDeviceID());
            switch (plan.getPlanAction()){
                case TURN_ON:
                    device.turnOn();
                    DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice)device);
                    break;
                case TURN_OFF:
                    device.turnOff();
                    DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice)device);
                    break;
            }
        }catch (Exception e) {
            MessageEditer messageEditer = new MessageEditer();
            MessageSender messageSender=new MessageSender(ChannelNameGenerator.getNotificationChannel());
            messageSender.pushNotification(messageEditer.getNotificationMessage(" Plan Manager","a plan should have been conduct is fail"));

        }


    }
}
