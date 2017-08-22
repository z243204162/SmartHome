package com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;

/**
 * Created by zeyu peng on 2017-07-13.
 */

public abstract class AbstractFeedbackHandler implements Observer{
    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;

    public AbstractFeedbackHandler(MessageSender messageSender) {
        this.mMessageSender=messageSender;
        this.mMessageEditer=new MessageEditer();
    }
    public void sendMessage(String packageType, Object object,String actionType){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType, object,actionType));
    }

}
