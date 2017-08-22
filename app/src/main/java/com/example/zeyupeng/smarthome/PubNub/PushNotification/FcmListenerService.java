package com.example.zeyupeng.smarthome.PubNub.PushNotification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.zeyupeng.smarthome.PubNub.Message.MessageComponent;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.View.MainActivity;
import com.example.zeyupeng.smarthome.View.SplashActivity;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by zeyu peng on 2017-06-09.
 */

public class FcmListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data)
    {
        String content = data.getString(MessageComponent.PUSH_NOTIFICATION_CONTENT);
        String title = data.getString(MessageComponent.PUSH_NOTIFICATION_title);
        sendNotification(title,content);
    }

    private void sendNotification(String title,String content) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("msg",content);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.robot)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationBuilder.setDefaults(Notification.DEFAULT_VIBRATE);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


}

