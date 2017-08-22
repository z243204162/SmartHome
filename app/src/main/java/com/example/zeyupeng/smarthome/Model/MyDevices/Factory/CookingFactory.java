package com.example.zeyupeng.smarthome.Model.MyDevices.Factory;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.Oven;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.RangeHood;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.CookingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class CookingFactory implements DeviceFactory{

    @Override
    public AbstractDevice createDevice(String productName) {
        AbstractDevice device = null;
        ProductParameter productParameter = new CookingParameter(productName);

        switch (productName){
            case ProductName.OVEN:
                device = new Oven();
                device.initialize(productParameter);

                break;
            case ProductName.RANGEHOOD:
                device = new RangeHood();
                device.initialize(productParameter);

                break;
        }
        return device;
    }
}
