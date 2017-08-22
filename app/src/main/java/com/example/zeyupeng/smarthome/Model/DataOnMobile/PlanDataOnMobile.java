package com.example.zeyupeng.smarthome.Model.DataOnMobile;

import android.util.Log;

import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-09.
 */

public class PlanDataOnMobile implements ObservableSubject {
    private static volatile PlanDataOnMobile ourInstance;
    private Map<String,Plan> mPlanDataMap;

    private List<Observer> mObserverList;

    public static PlanDataOnMobile getInstance() {
        if (ourInstance == null) {
            synchronized (PlanDataOnMobile.class) {
                if (ourInstance == null) {
                    ourInstance = new PlanDataOnMobile();
                }
            }
        }
        return ourInstance;
    }

    private PlanDataOnMobile() {
        this.mPlanDataMap = new HashMap<>();
        this.mObserverList = new ArrayList<>();
        Log.i("singleton","plandata constructor");
    }


    public void addPlan (Plan plan){
        this.mPlanDataMap.put(plan.getPlanID(),plan);
        Log.i("plan mobile data","plan"+plan.getPlanID());
        notifyObservers(ObserverActions.ADD_PLAN,plan);
    }

    public void modifyPlan (Plan plan){
        this.mPlanDataMap.put(plan.getPlanID(),plan);
        Log.i("plan mobile data","plan"+plan.getPlanID());
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
