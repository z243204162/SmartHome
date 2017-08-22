package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnHub.HubStatusDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.AbstractFeedbackHandler;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public class StatusQueryFeedbackHandler extends AbstractFeedbackHandler {
    public StatusQueryFeedbackHandler(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action) {
            case QUERY_HUB_STATUS:
                sendMessage(PackageType.PACKAGE_TYPE_HUB_STATUS, object, MessageAction.QUERY_HUB_STATUS);
                Log.i("243204162","query feedback handler ...QUERY_HUB_STATUS.getInstance().getOnlineStatus()..."+ HubStatusDataOnHub.getInstance().getOnlineStatus());
                break;
            case REFRESH_MOBLIE_STATUS:
                Log.i("rrrrrrrrrefresh","StatusQueryFeedbackHandler is called .....REFRESH_MOBLIE_STATUS:");
                sendMessage(PackageType.PACKAGE_TYPE_HUB_STATUS, object, MessageAction.REFRESH_MOBILE_STATUS);
                break;



        }
    }
}
