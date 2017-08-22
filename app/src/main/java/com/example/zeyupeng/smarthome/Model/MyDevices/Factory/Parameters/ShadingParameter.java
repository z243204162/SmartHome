package com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters;

import com.example.zeyupeng.smarthome.IDGenerater;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductType;

/**
 * Created by zeyu peng on 2017-08-09.
 */

public class ShadingParameter implements ProductParameter {

    private String mProductName;

    public ShadingParameter(String productName) {
        mProductName = productName;
    }

    @Override
    public String createProductID() {
        return IDGenerater.getInstance().generateID();
    }

    @Override
    public String createProductName() {
        return mProductName;
    }

    @Override
    public String createProductType() {
        return ProductType.SHADING;
    }

    @Override
    public String createDescription() {
        return null;
    }

    @Override
    public String createStatus() {
        return ProductStatus.DEFAULT;
    }
}
