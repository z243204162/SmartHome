package com.amazonaws.models.nosql;

import com.amazonaws.mobile.AWSMobileClient;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-21.
 */

public class PlanDataConverter {
    private PlanData mPlanData;
    public PlanData converToPlanData(Map<String,Plan> planMap){
        mPlanData = new PlanData();
        this.mPlanData.setUserID(AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID());
        this.mPlanData.setPlanMap(planMap);
        return this.mPlanData;
    }
}
