package com.example.zeyupeng.smarthome.PubNub.PushNotification;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.zeyupeng.smarthome.PubNub.Constants;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.View.MainActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.enums.PNPushType;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.push.PNPushAddChannelResult;

import java.util.Arrays;

/**
 * Created by zeyu peng on 2017-06-09.
 */

public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegIntentService";
    String token = null;
    //String senderID = "281070131450";
    private PubNub mPubnub;

    public RegistrationIntentService(){
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(Constants.SubscribeKey);
        pnConfiguration.setPublishKey(Constants.PublishKey);
        //pnConfiguration.setUuid("push123456");
        pnConfiguration.setSecure(false);
        mPubnub = new PubNub(pnConfiguration);


        try{

            InstanceID instanceID = InstanceID.getInstance(this);
            token = instanceID.getToken(Constants.senderID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i("aaaaaaaaaaaaa","..............."+token+".............");
            if (token!=null){
                enablePushOnChannel(token, ChannelNameGenerator.getNotificationChannel());
            }else {
                Log.i("aaaaaaaaaaaaa","bu gou shi jian na dao token");
            }
            Intent i = new Intent(RegistrationIntentService.this, MainActivity.class);
            i.putExtra("token",token);
        }catch (Exception e){
            Log.i("aaaaaaaaaaaaa","ma le ge bi");
        }

    }
    private void enablePushOnChannel(String regId,String channelName) {
        //adding regId to pubnub channel
        mPubnub.addPushNotificationsOnChannels()
                .pushType(PNPushType.GCM)
                .channels(Arrays.asList(channelName))
                .deviceId(regId)
                .async(new PNCallback<PNPushAddChannelResult>() {
                    @Override
                    public void onResponse(PNPushAddChannelResult result, PNStatus status) {
                        if (status.isError()) {
                            //Logger.d("Error on push notification" + status.getErrorData());
                            Log.i("aaaaaaaaaaaaaaaaaa","Error on push notification" + status.getErrorData());
                        } else {
                            Log.i("aaaaaaaaaaaaaaaaaa","Push notification added "+status.getAffectedChannels()+"+++++++++++++"+status.getUuid());
                            //Logger.d("Push notification added ");
                        }
                    }
                });
                /*
                .async(new PNCallback<PNPushAddChannelResult>() {
                    @Override
                    public void onResponse(PNPushAddChannelResult result, PNStatus status) {
                        if (status.isError()) {
                            Log.i("aaaaaaaaaaaaaaaaaa","Error on push notification" + status.getErrorData());
                            //Logger.d("Error on push notification" + status.getErrorData());
                        } else {
                            Log.i("aaaaaaaaaaaaaaaaaa","Push notification added ");
                            //Logger.d("Push notification added ");
                        }
                    }
                });
                */

    }

    public PubNub getmPubnub() {
        return mPubnub;
    }
}
