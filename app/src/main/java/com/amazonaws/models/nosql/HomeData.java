package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-22.
 */
@DynamoDBTable(tableName = "Homes")
public class HomeData {
    private String mHomeID;
    private Map<String,String> mRoomMap =new HashMap<>();

    @DynamoDBHashKey(attributeName = "HomeID")
    @DynamoDBIndexHashKey(attributeName = "HomeID", globalSecondaryIndexName = "HomeID")
    public String getHomeID() {
        return mHomeID;
    }

    public void setHomeID(String homeID) {
        mHomeID = homeID;
    }
    @DynamoDBAttribute(attributeName = "RoomMap")
    public Map<String,String> getRoomMap() {
        return mRoomMap;
    }

    public void setRoomMap(Map<String,String> roomMap) {
        mRoomMap = roomMap;
    }
}
