package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-07.
 */
@DynamoDBTable(tableName = "Plans")
public class PlanData {
    private String userID;
    private Map<String,Plan> planMap = new HashMap<>();

    @DynamoDBHashKey(attributeName = "UserID")
    @DynamoDBIndexHashKey(attributeName = "UserID", globalSecondaryIndexName = "UserID")
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    @DynamoDBAttribute(attributeName = "PlanMap")
    public Map<String,Plan> getPlanMap() {
        return planMap;
    }

    public void setPlanMap(Map<String,Plan> planMap) {
        this.planMap = planMap;
    }
}
