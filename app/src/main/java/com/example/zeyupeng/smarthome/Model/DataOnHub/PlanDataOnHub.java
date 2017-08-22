package com.example.zeyupeng.smarthome.Model.DataOnHub;

import android.util.Log;

import com.amazonaws.models.nosql.PlanData;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObservableSubject;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-09.
 */

public class PlanDataOnHub implements ObservableSubject {
    private static volatile PlanDataOnHub ourInstance;
    private Map<String,Plan> mPlanDataMap;

    private List<Observer> mObserverList;

    public static PlanDataOnHub getInstance() {
        if (ourInstance == null) {
            synchronized (PlanDataOnHub.class) {
                if (ourInstance == null) {
                    ourInstance = new PlanDataOnHub();
                }
            }
        }
        return ourInstance;
    }

    private PlanDataOnHub() {
        this.mPlanDataMap = new HashMap<>();
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","plandata constructor");
    }


    public void addPlan (Plan plan){
        this.mPlanDataMap.put(plan.getPlanID(),plan);
        Log.i("hhhhhhub","plan"+plan.getPlanID());
        notifyObservers(ObserverActions.ADD_PLAN,plan);
    }

    public void modifyPlan (Plan plan){
        this.mPlanDataMap.put(plan.getPlanID(),plan);
        Log.i("hhhhhhub","plan"+plan.getPlanID());
        notifyObservers(ObserverActions.MODIFY_PLAN,plan);
    }

    public void removePlan(Plan plan){
        this.mPlanDataMap.remove(plan.getPlanID());
        notifyObservers(ObserverActions.REMOVE_PLAN,plan);
    }

    public Plan getPlan(String planID){
        return this.mPlanDataMap.get(planID);
    }

    public Map<String, Plan> getPlanDataMap() {
        return mPlanDataMap;
    }

    public void setPlanDataMap(Map<String, Plan> planDataMap) {
        mPlanDataMap = planDataMap;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.mObserverList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i =this.mObserverList.indexOf(observer);
        if(i>=0){
            this.mObserverList.remove(i);
        }
    }

    @Override
    public void notifyObservers(ObserverActions action,Object object) {
        for(int i=0;i<this.mObserverList.size();i++){
            Observer observer = (Observer) this.mObserverList.get(i);
            observer.update(action,object);
        }
    }


}
