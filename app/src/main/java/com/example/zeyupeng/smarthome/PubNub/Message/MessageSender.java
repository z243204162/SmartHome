package com.example.zeyupeng.smarthome.PubNub.Message;

import android.content.Context;
import android.util.Log;

import com.example.zeyupeng.smarthome.PubNub.Constants;
import com.example.zeyupeng.smarthome.PubNub.PushNotification.NotificationPayload;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-29.
 */

public class MessageSender {
    private PubNub mPubnub;
    private String mChannelName;


    public MessageSender(String channelName) {
        mChannelName = channelName;
        this.initPubNubChannel();
    }


    public void initPubNubChannel(){
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(Constants.SubscribeKey);
        pnConfiguration.setPublishKey(Constants.PublishKey);
        pnConfiguration.setSecure(true);

        mPubnub = new PubNub(pnConfiguration);


    }


    public void sendRealTimeMessage(final Map<String, String> message){
        //Log.i("super debug",message+"");
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPubnub.publish()
                        .channel(mChannelName)
                        .message(message)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                /// handle sendRealTimeMessage result.
                                if (status.isError()) {
                                    //Logger.d("Error on push notification" + status.getErrorData());
                                    Log.i("aaaaaaaaaaaaaaaaaa","Error on push notification" + status.getErrorData());
                                } else {
                                    Log.i("aaaaaaaaaaaaaaaaaa","Push to chanel "+status.getAffectedChannels()+mPubnub.getSubscribedChannels());
                                    //Logger.d("Push notification added ");
                                }
                            }
                        });
            }
        }).start();


    }

    public void pushNotification(final Map<String,Object> message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPubnub.publish()
                        .channel(mChannelName)
                        .message(message)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                /// handle sendRealTimeMessage result.
                                if (status.isError()) {
                                    //Logger.d("Error on push notification" + status.getErrorData());
                                    Log.i("aaaaaaaaaaaaaaaaaa","Error on push notification" + status.getErrorData());
                                } else {
                                    Log.i("aaaaaaaaaaaaaaaaaa","Push to chanel "+status.getAffectedChannels()+mPubnub.getSubscribedChannels());
                                    //Logger.d("Push notification added ");
                                }
                            }
                        });
            }
        }).start();
    }


}
