package com.example.zeyupeng.smarthome.Model.MyPlans;

import android.content.Context;

import com.example.zeyupeng.smarthome.IDGenerater;

/**
 * Created by zeyu peng on 2017-07-07.
 */

public class PlanFactory {
    public Plan createPlan(){
        Plan plan = new Plan();
        plan.setPlanID(IDGenerater.getInstance().generatePlanID());

        return plan;
    }
}
