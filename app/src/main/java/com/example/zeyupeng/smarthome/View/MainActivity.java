package com.example.zeyupeng.smarthome.View;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobilehelper.auth.IdentityManager;

import com.amazonaws.models.nosql.PlanData;


import com.example.zeyupeng.smarthome.AWS.loginsystem.SignInHandler;
import com.example.zeyupeng.smarthome.HubInitializer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HubStatusDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;


import com.example.zeyupeng.smarthome.PlansandTimeTasks.DatabaseForPlans;
import com.example.zeyupeng.smarthome.PlansandTimeTasks.PlanDatabaseManager;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh.OnlineStatusCheckingService;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeMonitor.HubToAppMessageReciver;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSubscriber;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh.StatusAnswerMessageReciever;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityPlansAdapter;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityRoomAdapter;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityViewModel;
import com.example.zeyupeng.smarthome.View.CustomView.MyItemDecorator;
import com.example.zeyupeng.smarthome.databinding.ActivityMainBinding;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Observer{

    private IdentityManager identityManager;
    ColorLight device1;
    ColorLight device2;
    ColorLight device3;
    ColorLight device4;
    Room room1;
    Room room2;
    Home myHome;

    private DatabaseForPlans database;
    private SQLiteDatabase database_writer;

    private RecyclerView mRecyclerView_room;
    private RecyclerView mRecyclerView_plan;
    private DrawerLayout mDrawer;
    private SwipeRefreshLayout mRefreshLayout;

    private MessageSubscriber mControlMessageSubscriber;
    private MessageSubscriber mStatusMessageSubscriber;


    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;
    private StreamProgressPoster mProgressPoster;


    private Intent mIntent;

    private Timer mTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoomDataOnMobile.getInstance().registerObserver(this);
        PlanDataOnMobile.getInstance().registerObserver(this);
        HubStatusDataOnMobile.getInstance().registerObserver(this);
        mMessageSender = new MessageSender(ChannelNameGenerator.getQueryStatusChannel());
        mMessageEditer= new MessageEditer();
        //mTimer=new Timer();
        initView();
        initPubNub();


    }

    public void initView(){
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setMainActivityViewModel(MainActivityViewModel.getInstance());
        Log.i("mmmmmain activity","before loading recycler");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);


        mRecyclerView_room = (RecyclerView)findViewById(R.id.recycler_room);
        LinearLayoutManager linearLayoutManager_room = new LinearLayoutManager(this);
        linearLayoutManager_room.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView_room.setLayoutManager(linearLayoutManager_room);
        mRecyclerView_room.addItemDecoration(new MyItemDecorator(40));
        MainActivityRoomAdapter adapter_room=new MainActivityRoomAdapter(this);
        mRecyclerView_room.setAdapter(adapter_room);
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView_room);

        mRecyclerView_plan = (RecyclerView)findViewById(R.id.recycler_plans);
        LinearLayoutManager linearLayoutManager_plan = new LinearLayoutManager(this);
        linearLayoutManager_plan.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView_plan.setLayoutManager(linearLayoutManager_plan);
        mRecyclerView_plan.addItemDecoration(new MyItemDecorator(25));
        MainActivityPlansAdapter adapter_plan=new MainActivityPlansAdapter(this);
        mRecyclerView_plan.setAdapter(adapter_plan);
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView_plan);




        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout_main);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postProgress(MainActivity.this,PackageType.PACKAGE_TYPE_HUB_STATUS,mRefreshLayout);
                sendMessage(PackageType.PACKAGE_TYPE_HUB_STATUS,"", MessageAction.REFRESH_MOBILE_STATUS);
            }
        });




    }

    public void initPubNub(){
        StatusAnswerMessageReciever statusAnswerMessageReciever = new StatusAnswerMessageReciever();
        mStatusMessageSubscriber=
                new MessageSubscriber(ChannelNameGenerator.getAnswerStatusChannel(), statusAnswerMessageReciever);
        this.mStatusMessageSubscriber.subscribe();

        HubToAppMessageReciver hubToAppMessageReciver = new HubToAppMessageReciver();
        this.mControlMessageSubscriber=
                new MessageSubscriber(ChannelNameGenerator.getMonitorChannel(), hubToAppMessageReciver);
        this.mControlMessageSubscriber.subscribe();

        mIntent = new Intent(this, OnlineStatusCheckingService.class);
        startService(mIntent);
    }

    public void sendMessage(String packageType,Object object,String messageAction){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,messageAction));
    }
    public void postProgress(Context context, String packageType, View view){
        mProgressPoster = new StreamProgressPoster(context,packageType,view);
        mProgressPoster.startRefreshing();
    }
/*
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(RoomDataOnMobile.getInstance().getRoomDataMap().size()==0){
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);
            AlertDialog dialog = builder.create();
        }
    }

    */

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            stopService(mIntent);
            finish();
            super.onBackPressed();
            //this.timer.cancel();

        }
    }



    @Override
    protected void onDestroy() {
        //this.timer.cancel();

        stopService(mIntent);
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i =new Intent(this,SettingActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

            //Intent i = new Intent(this,HubRoomsActivity.class);
            //startActivity(i);
            new HubInitializer(this).execute();
        }  else if (id == R.id.nav_manage) {
            Intent i = new Intent(this,SettingActivity.class);
            startActivity(i);
        }  else if (id == R.id.nav_send) {
            AWSMobileClient.defaultMobileClient().getIdentityManager().signOut();
            AWSMobileClient.defaultMobileClient().getIdentityManager().signInOrSignUp(this,new SignInHandler());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action){
            case ADD_ROOM:
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
                break;
            case REMOVE_ROOM:
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
                break;
            case ADD_PLAN:
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
                break;
            case REMOVE_PLAN:
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
                break;
        }
    }
}
