package com.example.zeyupeng.smarthome.Model.MyDevices;

import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.Parameters.ProductParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-05-29.
 */


public abstract class AbstractDevice  {
    private String mProductID;
    private String mProductName;
    private String mProductType;
    private String mStatus;
    private String mCustomName;
    private String mDescription;
    private String mLocationID;
    private int mImage;
    private List<String> mControlerType =new ArrayList<>();




    public  void initialize(ProductParameter productParameter){
        this.setProductID(productParameter.createProductID());
        this.setProductName(productParameter.createProductName());
        this.setProductType(productParameter.createProductType());
        this.setDescription(productParameter.createDescription());
        this.setStatus(productParameter.createStatus());
    }



    public String getProductID() {
        return mProductID;
    }

    public String getProductName() {
        return mProductName;
    }

    public String getProductType() {
        return mProductType;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getCustomName() {
        return mCustomName;
    }

    public String getDescription() {
        return mDescription;
    }

    public List<String> getControlerType() {
        return mControlerType;
    }

    public String getLocationID() {
        return mLocationID;
    }

    public int getImage() {
        return mImage;
    }







    protected void setProductID(String mProductID) {
        this.mProductID = mProductID;
    }

    protected void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    protected void setProductType(String mProductType) {
        this.mProductType = mProductType;
    }

    protected void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    protected void setControlerType(List<String> mControlerType) {
        this.mControlerType = mControlerType;
    }

    public void setCustomName(String mCustomName) {
        this.mCustomName = mCustomName;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setLocationID(String locationID) {
        mLocationID = locationID;
    }

    public void setImage(int image) {
        this.mImage = image;
    }

}
