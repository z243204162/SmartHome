package com.example.zeyupeng.smarthome.PubNub.Message;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public abstract class AbstractDataPackage {
    private String actionType;
    private String objectJson;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getObjectJson() {
        return objectJson;
    }

    public void setObjectJson(String objectJson) {
        this.objectJson = objectJson;
    }
}
