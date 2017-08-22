package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

/**
 * Created by zeyu peng on 2017-06-10.
 */
@DynamoDBTable(tableName = "DeviceData-AdjustableLight")
public class AdjustableLightData {

    private String mProductID;
    private String mProductName;
    private String mProductType;
    private String mStatus;
    private String mCustomName;
    private String mDescription;
    private String mLocationID;
    private List<String> mControlerType;

    private int mAdjustableValue;

    @DynamoDBHashKey(attributeName = "ProductID")
    @DynamoDBIndexHashKey(attributeName = "ProductID", globalSecondaryIndexName = "ProductID")
    public String getProductID() {
        return mProductID;
    }
    @DynamoDBAttribute(attributeName = "ProductName")
    public String getProductName() {
        return mProductName;
    }
    @DynamoDBAttribute(attributeName = "ProductType")
    public String getProductType() {
        return mProductType;
    }
    @DynamoDBAttribute(attributeName = "Status")
    public String getStatus() {
        return mStatus;
    }
    @DynamoDBAttribute(attributeName = "CustomName")
    public String getCustomName() {
        return mCustomName;
    }
    @DynamoDBAttribute(attributeName = "Description")
    public String getDescription() {
        return mDescription;
    }
    @DynamoDBAttribute(attributeName = "LocationID")
    public String getLocationID() {
        return mLocationID;
    }
    @DynamoDBAttribute(attributeName = "ControlerType")
    public List<String> getControlerType() {
        return mControlerType;
    }
    @DynamoDBAttribute(attributeName = "Brightness")
    public int getAdjustableValue() {
        return mAdjustableValue;
    }

    public void setProductID(String productID) {
        this.mProductID = productID;
    }

    public void setProductName(String productName) {
        this.mProductName = productName;
    }

    public void setProductType(String productType) {
        this.mProductType = productType;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public void setCustomName(String customName) {
        this.mCustomName = customName;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }


    public void setLocationID(String locationID) {
        mLocationID = locationID;
    }
    public void setControlerType(List<String> controlerType) {
        this.mControlerType = controlerType;
    }

    public void setAdjustableValue(int adjustableValue) {
        this.mAdjustableValue = adjustableValue;
    }
}
