package com.example.zeyupeng.smarthome.Model.MyDevices.Factory;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.CoolingHeatingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ShadingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;
import com.example.zeyupeng.smarthome.Model.MyDevices.Shading.Curtain;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class ShadingFactory implements DeviceFactory {
    @Override
    public AbstractDevice createDevice(String productName) {
        AbstractDevice device = null;
        ProductParameter productParameter = new ShadingParameter(productName);

        switch (productName){
            case ProductName.CURTAIN:
                device = new Curtain();
                device.initialize(productParameter);
                break;
        }
        return device;
    }
}
