package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

/**
 * Created by zeyu peng on 2017-07-31.
 */
@DynamoDBTable(tableName = "DeviceData-NFCDoor")
public class NFCDoorData {
    private String mProductID;
    private String mProductName;
    private String mProductType;
    private String mStatus;
    private String mCustomName;
    private String mDescription;
    private String mLocationID;
    private List<String> mControlerType;

    private String mLockStatus;
    private String mPassword;

    private boolean mNotificationPermit;

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
    @DynamoDBAttribute(attributeName = "LockStatus")
    public String getLockStatus() {
        return mLockStatus;
    }
    @DynamoDBAttribute(attributeName = "Password")
    public String getPassword() {
        return mPassword;
    }
    @DynamoDBAttribute(attributeName = "NotificationPermit")
    public boolean getNotificationPermit() {
        return mNotificationPermit;
    }





    public void setProductID(String productID) {
        mProductID = productID;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public void setProductType(String productType) {
        mProductType = productType;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public void setCustomName(String customName) {
        mCustomName = customName;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setLocationID(String locationID) {
        mLocationID = locationID;
    }

    public void setControlerType(List<String> controlerType) {
        mControlerType = controlerType;
    }

    public void setLockStatus(String lockStatus) {
        mLockStatus = lockStatus;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
    public void setNotificationPermit(boolean notificationPermit) {
        mNotificationPermit = notificationPermit;
    }
}
