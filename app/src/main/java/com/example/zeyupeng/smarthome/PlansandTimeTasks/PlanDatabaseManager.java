package com.example.zeyupeng.smarthome.PlansandTimeTasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amazonaws.models.nosql.PlanData;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyPlans.PlanDate;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-07.
 */

public class PlanDatabaseManager implements Observer{
    private DatabaseForPlans mDatabase;
    private SQLiteDatabase mDBOprator;








    public PlanDatabaseManager(Context context) {
        this.mDatabase = new DatabaseForPlans(context);
        this.mDBOprator=this.mDatabase.getReadableDatabase();
    }

    public void importToDatabase(PlanData planData){

        for (Map.Entry<String, Plan> entry: planData.getPlanMap().entrySet()){
            insertPlan(entry.getValue());
        }
    }

    public void importToDatabase(Plan plan){
            insertPlan(plan);
    }

    public PlanData exportToPlanData(){
        PlanData planData = new PlanData();
        Cursor cursor = this.mDBOprator.query(DatabaseForPlans.TABLE_NAME, null, null, null, null, null, null);
        for(int i=0;i<cursor.getCount();i++){
            Plan plan = new Plan();
            cursor.moveToPosition(i);
            plan.setPlanID(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.PLAN_ID)));
            plan.setDeviceID(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.PRODUCT_ID)));
            plan.setPlanAction(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.ACTION)));
            plan.setHour(cursor.getInt(cursor.getColumnIndex(DatabaseForPlans.HOUR)));
            plan.setMinute(cursor.getInt(cursor.getColumnIndex(DatabaseForPlans.MINUTE)));

            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.MON))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.MON)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.MON);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.TUE))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.TUE)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.TUE);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.WED))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.WED)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.WED);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.THU))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.THU)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.THU);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.FRI))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.FRI)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.FRI);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.SAT))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.SAT)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.SAT);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseForPlans.SUN))!=null&&
                    cursor.getString(cursor.getColumnIndex(DatabaseForPlans.SUN)).equals(PlanDate.TRUE)){
                plan.getRepeatDate().add(PlanDate.SUN);
            }
            planData.getPlanMap().put(plan.getPlanID(),plan);
        }

        return planData;
    }

    public void insertPlan(Plan plan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseForPlans.PLAN_ID,plan.getPlanID());
        contentValues.put(DatabaseForPlans.PRODUCT_ID,plan.getDeviceID());
        contentValues.put(DatabaseForPlans.ACTION,plan.getPlanAction());
        contentValues.put(DatabaseForPlans.HOUR,plan.getHour());
        contentValues.put(DatabaseForPlans.MINUTE,plan.getMinute());
        for(String date:plan.getRepeatDate()){
            switch (date){
                case PlanDate.MON:
                    contentValues.put(DatabaseForPlans.MON,PlanDate.TRUE);
                    break;
                case PlanDate.TUE:
                    contentValues.put(DatabaseForPlans.TUE,PlanDate.TRUE);
                    break;
                case PlanDate.WED:
                    contentValues.put(DatabaseForPlans.WED,PlanDate.TRUE);
                    break;
                case PlanDate.THU:
                    contentValues.put(DatabaseForPlans.THU,PlanDate.TRUE);
                    break;
                case PlanDate.FRI:
                    contentValues.put(DatabaseForPlans.FRI,PlanDate.TRUE);
                    break;
                case PlanDate.SAT:
                    contentValues.put(DatabaseForPlans.SAT,PlanDate.TRUE);
                    break;
                case PlanDate.SUN:
                    contentValues.put(DatabaseForPlans.SUN,PlanDate.TRUE);
                    break;
            }
        }
        this.mDBOprator.replace(DatabaseForPlans.TABLE_NAME,null,contentValues);
    }

    public void removePlan(Plan plan){
        this.mDBOprator.delete(DatabaseForPlans.TABLE_NAME,DatabaseForPlans.PLAN_ID+"="+plan.getPlanID(),null);
    }

    @Override
    public void update(ObserverActions action, Object object) {
        Plan plan = (Plan) object;
        switch (action){
            case ADD_PLAN:
                this.importToDatabase(plan);
                break;
            case MODIFY_PLAN:
                this.importToDatabase(plan);
                break;
            case REMOVE_PLAN:
                this.removePlan(plan);
                break;
        }
    }
}
/*
ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseForPlans.PLAN_ID,entry.getValue().getPlanID());
            contentValues.put(DatabaseForPlans.PRODUCT_ID,entry.getValue().getDeviceID());
            contentValues.put(DatabaseForPlans.ACTION,entry.getValue().getPlanAction());
            contentValues.put(DatabaseForPlans.HOUR,entry.getValue().getHour());
            contentValues.put(DatabaseForPlans.MINUTE,entry.getValue().getMinute());
            for(String date:entry.getValue().getRepeatDate()){
                switch (date){
                    case PlanDate.MON:
                        contentValues.put(DatabaseForPlans.MON,PlanDate.TRUE);
                        break;
                    case PlanDate.TUE:
                        contentValues.put(DatabaseForPlans.TUE,PlanDate.TRUE);
                        break;
                    case PlanDate.WED:
                        contentValues.put(DatabaseForPlans.WED,PlanDate.TRUE);
                        break;
                    case PlanDate.THU:
                        contentValues.put(DatabaseForPlans.THU,PlanDate.TRUE);
                        break;
                    case PlanDate.FRI:
                        contentValues.put(DatabaseForPlans.FRI,PlanDate.TRUE);
                        break;
                    case PlanDate.SAT:
                        contentValues.put(DatabaseForPlans.SAT,PlanDate.TRUE);
                        break;
                    case PlanDate.SUN:
                        contentValues.put(DatabaseForPlans.SUN,PlanDate.TRUE);
                        break;
                }
            }
            this.mDBOprator.replace(DatabaseForPlans.TABLE_NAME,null,contentValues);
 */