package com.example.zeyupeng.smarthome.Model.MyDevices;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-06-01.
 */

public class ProductType {
    public static final String LIGHTING="LIGHTING";
    public static final String ACCESS="ACCESS";
    public static final String COOKING="COOKING";
    public static final String SHADING="SHADING";
    public static final String COOLING_AND_HEATING="COOLING AND HEATING";



    private static volatile ProductType ourInstance;

    public static ProductType getInstance() {
        if (ourInstance == null) {
            synchronized (ProductType.class) {
                if (ourInstance == null) {
                    ourInstance = new ProductType();
                }
            }
        }
        return ourInstance;
    }

    private ProductType() {
        initProductTypeList();
        Log.i("singleton","ProductType constructor");
    }

    private List<String> productTypeList;

    public void initProductTypeList(){
        this.productTypeList = new ArrayList<>();
        this.productTypeList.add(ProductType.LIGHTING);
        this.productTypeList.add(ProductType.ACCESS);
        this.productTypeList.add(ProductType.COOKING);
        this.productTypeList.add(ProductType.SHADING);
        this.productTypeList.add(ProductType.COOLING_AND_HEATING);

    }

    public List<String> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<String> productTypeList) {
        this.productTypeList = productTypeList;
    }
}
