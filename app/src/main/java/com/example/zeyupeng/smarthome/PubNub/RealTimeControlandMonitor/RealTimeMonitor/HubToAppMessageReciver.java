package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeMonitor;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

/**
 * Created by zeyu peng on 2017-07-04.
 */

public class HubToAppMessageReciver extends SubscribeCallback {



    @Override
    public void status(PubNub pubnub, PNStatus status) {

    }

    @Override
    public void message(PubNub pubnub, PNMessageResult message) {
        HubToAppMessageHandler mHubToAppMessageHandler = new HubToAppMessageHandler();
        mHubToAppMessageHandler.handleMassageFromHub(message);
    }

    @Override
    public void presence(PubNub pubnub, PNPresenceEventResult presence) {

    }
}
