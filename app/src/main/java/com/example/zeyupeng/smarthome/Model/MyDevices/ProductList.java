package com.example.zeyupeng.smarthome.Model.MyDevices;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-14.
 */

public class ProductList {
    private static volatile ProductList ourInstance;

    public static ProductList getInstance() {
        if (ourInstance == null) {
            synchronized (ProductList.class) {
                if (ourInstance == null) {
                    ourInstance = new ProductList();
                }
            }
        }
        return ourInstance;
    }

    private ProductList() {
        initProductListMap();
        Log.i("singleton","productList constructor");
    }





    private Map<String,List<String>> productListMap;

    public void initProductListMap(){
        this.productListMap=new HashMap<>();
        this.productListMap.put(ProductType.LIGHTING,ProductName.getInstance().getLightingProductList());
        this.productListMap.put(ProductType.ACCESS,ProductName.getInstance().getAccessProductList());
        this.productListMap.put(ProductType.COOLING_AND_HEATING,ProductName.getInstance().getCoolingAndHeatingProductList());
        this.productListMap.put(ProductType.SHADING,ProductName.getInstance().getShadingProductList());
        this.productListMap.put(ProductType.COOKING,ProductName.getInstance().getCookingProductList());
    }

    public Map<String, List<String>> getProductListMap() {
        return productListMap;
    }

    public void setProductListMap(Map<String, List<String>> productListMap) {
        this.productListMap = productListMap;
    }



}
