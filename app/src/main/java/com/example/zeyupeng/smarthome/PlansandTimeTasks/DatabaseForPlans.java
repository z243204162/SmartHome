package com.example.zeyupeng.smarthome.PlansandTimeTasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zeyu peng on 2017-07-05.
 */

public class DatabaseForPlans extends SQLiteOpenHelper {
    public final static String TABLE_NAME = "plans";
    public final static String PLAN_ID = "id";
    public final static String PRODUCT_ID = "product_id";
    public final static String ACTION = "action";
    public final static String HOUR = "hour";
    public final static String MINUTE = "minute";
    public final static String MON = "mon";
    public final static String TUE = "tue";
    public final static String WED = "wed";
    public final static String THU = "thu";
    public final static String FRI = "fri";
    public final static String SAT = "sat";
    public final static String SUN = "sun";


    public DatabaseForPlans(Context context) {
        super(context, "plans", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"("
                +PLAN_ID+" text primary key,"
                +PRODUCT_ID+" text,"
                +ACTION+" text,"
                +HOUR+" integer,"
                +MINUTE+" integer,"
                +MON+" text,"
                +TUE+" text,"
                +WED+" text,"
                +THU+" text,"
                +FRI+" text,"
                +SAT+" text,"
                +SUN+" text" +")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
