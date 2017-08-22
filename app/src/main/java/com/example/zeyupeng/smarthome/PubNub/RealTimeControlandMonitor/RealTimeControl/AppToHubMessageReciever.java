package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeControl;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

/**
 * Created by zeyu peng on 2017-06-21.
 */

public class AppToHubMessageReciever extends SubscribeCallback {

    @Override
    public void status(PubNub pubnub, PNStatus status) {

    }

    @Override
    public void message(PubNub pubnub, PNMessageResult message) {
        AppToHubMessageHandler appToHubMessageHandler =new AppToHubMessageHandler();
        appToHubMessageHandler.handleMassageFromAPP(message);
    }

    @Override
    public void presence(PubNub pubnub, PNPresenceEventResult presence) {

    }
}
