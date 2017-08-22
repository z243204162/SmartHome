package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

/**
 * Created by zeyu peng on 2017-08-09.
 */
@DynamoDBTable(tableName = "DeviceData-RangeHood")
public class RangeHoodData {
    private String mProductID;
    private String mProductName;
    private String mProductType;
    private String mStatus;
    private String mCustomName;
    private String mDescription;
    private String mLocationID;
    private List<String> mControlerType;

    private String mMode;


    @DynamoDBHashKey(attributeName = "ProductID")
    @DynamoDBIndexHashKey(attributeName = "ProductID", globalSecondaryIndexName = "ProductID")
    public String getProductID() {
        return mProductID;
    }

    public void setProductID(String mProductID) {
        this.mProductID = mProductID;
    }


    @DynamoDBAttribute(attributeName = "ProductName")
    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    @DynamoDBAttribute(attributeName = "ProductType")
    public String getProductType() {
        return mProductType;
    }

    public void setProductType(String mProductType) {
        this.mProductType = mProductType;
    }

    @DynamoDBAttribute(attributeName = "Status")
    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    @DynamoDBAttribute(attributeName = "CustomName")
    public String getCustomName() {
        return mCustomName;
    }

    public void setCustomName(String mCustomName) {
        this.mCustomName = mCustomName;
    }

    @DynamoDBAttribute(attributeName = "Description")
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @DynamoDBAttribute(attributeName = "LocationID")
    public String getLocationID() {
        return mLocationID;
    }

    public void setLocationID(String locationID) {
        mLocationID = locationID;
    }

    @DynamoDBAttribute(attributeName = "ControlerType")
    public List<String> getControlerType() {
        return mControlerType;
    }

    public void setControlerType(List<String> mControlerType) {
        this.mControlerType = mControlerType;
    }



    @DynamoDBAttribute(attributeName = "Mode")
    public String getMode() {
        return mMode;
    }

    public void setMode(String mMode) {
        this.mMode = mMode;
    }
}
