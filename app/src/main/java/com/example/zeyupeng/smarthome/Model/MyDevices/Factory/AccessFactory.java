package com.example.zeyupeng.smarthome.Model.MyDevices.Factory;

import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.GarageDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.NFCDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.AccessParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.LightingParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.AdjustableLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;

/**
 * Created by zeyu peng on 2017-08-01.
 */

public class AccessFactory implements DeviceFactory{
    @Override
    public AbstractDevice createDevice(String productName) {
        AbstractDevice device = null;
        ProductParameter productParameter = new AccessParameter(productName);

        switch (productName){
            case ProductName.NFC_DOOR:
                device = new NFCDoor();
                device.initialize(productParameter);

                break;
            case ProductName.GARAGE_DOOR:
                device = new GarageDoor();
                device.initialize(productParameter);

                break;


        }
        return device;
    }
}
