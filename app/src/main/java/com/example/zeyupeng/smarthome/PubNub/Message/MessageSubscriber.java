package com.example.zeyupeng.smarthome.PubNub.Message;

import com.example.zeyupeng.smarthome.PubNub.Constants;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;

import java.util.Arrays;

/**
 * Created by zeyu peng on 2017-07-04.
 */

public class MessageSubscriber {
    private PubNub mPubnub;
    private String mChannelName;
    private PNConfiguration pnConfiguration;
    private SubscribeCallback mSubscribeCallback;

    public MessageSubscriber(String channelName,SubscribeCallback subscribeCallback) {
        mSubscribeCallback=subscribeCallback;
        mChannelName = channelName;
        this.initPubNubChannel();
    }


    public void initPubNubChannel(){
        pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(Constants.SubscribeKey);
        pnConfiguration.setPublishKey(Constants.PublishKey);
        pnConfiguration.setSecure(true);
        mPubnub = new PubNub(pnConfiguration);

    }

    public void subscribe (){
        mPubnub.addListener(this.mSubscribeCallback);
        mPubnub.subscribe().channels(Arrays.asList(this.mChannelName)).withPresence().execute();
    }
    public void unSubcribe(){
        this.mPubnub.unsubscribe().channels(Arrays.asList(this.mChannelName)).execute();

    }
}
