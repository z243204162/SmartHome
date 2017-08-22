package com.example.zeyupeng.smarthome.PubNub.Message;

/**
 * Created by zeyu peng on 2017-06-29.
 */

public class DeviceDataPackage extends AbstractDataPackage {

    private String productName;



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



}
