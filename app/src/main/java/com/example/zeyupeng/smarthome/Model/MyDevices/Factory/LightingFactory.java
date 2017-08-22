package com.example.zeyupeng.smarthome.Model.MyDevices.Factory;

import android.content.Context;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.LightingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.AdjustableLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;


/**
 * Created by zeyu peng on 2017-06-05.
 */

public class LightingFactory implements DeviceFactory {
    @Override
    public AbstractDevice createDevice(String productName) {
        AbstractDevice device = null;
        ProductParameter productParameter = new LightingParameter(productName);

        switch (productName){
            case ProductName.COLOR_LIGHT:
                device = new ColorLight();
                device.initialize(productParameter);
                break;
            case ProductName.ADJUSTABLE_LIGHT:
                device = new AdjustableLight();
                device.initialize(productParameter);

        }
        return device;
    }
}
