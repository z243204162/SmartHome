package com.example.zeyupeng.smarthome.Model.MyDevices;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-06-01.
 */

public class ProductName {
    public static final String COLOR_LIGHT="COLOR LIGHT";
    public static final String ADJUSTABLE_LIGHT="ADJUSTABLE LIGHT";
    public static final String NFC_DOOR="NFC DOOR";
    public static final String GARAGE_DOOR="GARAGE DOOR";
    public static final String OVEN="OVEN";
    public static final String RANGEHOOD="RANGEHOOD";
    public static final String AC="AC";
    public static final String CURTAIN="CURTAIN";



    private static volatile ProductName ourInstance;

    public static ProductName getInstance() {
        if (ourInstance == null) {
            synchronized (ProductName.class) {
                if (ourInstance == null) {
                    ourInstance = new ProductName();
                }
            }
        }
        return ourInstance;
    }

    private ProductName() {
        initlightingProductList();
        initAccessProductList();
        initCookingProductList();
        initCoolingandHeatingProductList();
        initShadingProductList();
        Log.i("singleton","ProductName constructor");
    }





    private List<String> lightingProductList;
    private List<String> accessProductList;
    private List<String> cookingProductList;
    private List<String> shadingProductList;
    private List<String> coolingAndHeatingProductList;

    public void initlightingProductList(){
        this.lightingProductList = new ArrayList<>();
        this.lightingProductList.add(ProductName.COLOR_LIGHT);
        this.lightingProductList.add(ProductName.ADJUSTABLE_LIGHT);

    }
    public void initAccessProductList(){
        this.accessProductList = new ArrayList<>();
        this.accessProductList.add(ProductName.NFC_DOOR);
        this.accessProductList.add(ProductName.GARAGE_DOOR);
    }
    public void initShadingProductList(){
        this.shadingProductList = new ArrayList<>();
        this.shadingProductList.add(ProductName.CURTAIN);
    }


    public void initCookingProductList(){
        this.cookingProductList = new ArrayList<>();
        this.cookingProductList.add(ProductName.OVEN);
        this.cookingProductList.add(ProductName.RANGEHOOD);
    }
    public void initCoolingandHeatingProductList(){
        this.coolingAndHeatingProductList = new ArrayList<>();
        this.coolingAndHeatingProductList.add(ProductName.AC);
    }

    public List<String> getLightingProductList() {
        return lightingProductList;
    }
    public List<String> getAccessProductList() {
        return accessProductList;
    }
    public List<String> getCookingProductList() {
        return cookingProductList;
    }
    public List<String> getShadingProductList() {
        return shadingProductList;
    }
    public List<String> getCoolingAndHeatingProductList() {
        return coolingAndHeatingProductList;
    }

}
