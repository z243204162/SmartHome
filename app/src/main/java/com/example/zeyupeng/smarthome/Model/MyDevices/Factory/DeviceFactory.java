package com.example.zeyupeng.smarthome.Model.MyDevices.Factory;

import android.content.Context;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;

/**
 * Created by zeyu peng on 2017-06-05.
 */

public interface DeviceFactory {
    AbstractDevice createDevice(String productName);
}
