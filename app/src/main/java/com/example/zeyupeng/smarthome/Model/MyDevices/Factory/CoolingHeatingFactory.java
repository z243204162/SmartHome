package com.example.zeyupeng.smarthome.Model.MyDevices.Factory;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.Oven;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.RangeHood;
import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.CookingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.CoolingHeatingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class CoolingHeatingFactory implements DeviceFactory {
    @Override
    public AbstractDevice createDevice(String productName) {
        AbstractDevice device = null;
        ProductParameter productParameter = new CoolingHeatingParameter(productName);

        switch (productName){
            case ProductName.AC:
                device = new AC();
                device.initialize(productParameter);
                break;
        }
        return device;
    }
}
