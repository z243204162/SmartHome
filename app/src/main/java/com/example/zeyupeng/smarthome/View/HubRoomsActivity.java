package com.example.zeyupeng.smarthome.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import com.amazonaws.mobile.AWSMobileClient;
import com.example.zeyupeng.smarthome.AWS.loginsystem.SignInHandler;
import com.example.zeyupeng.smarthome.Model.Cloud.DownloadTool;
import com.example.zeyupeng.smarthome.Model.Cloud.UploadService;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HubStatusDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.PubNub.PushNotification.PushManager;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeControl.AppToHubMessageFeedbackHandler;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.RealTimeControl.AppToHubMessageReciever;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSubscriber;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh.StatusQueryFeedbackHandler;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StatusCheckingAndRefresh.StatusQueryMessageReceiver;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubRoomsActivityRoomAdapter;
import com.example.zeyupeng.smarthome.View.CustomView.MyItemDecorator;
import com.example.zeyupeng.smarthome.databinding.ActivityHubRoomsBinding;

public class HubRoomsActivity extends AppCompatActivity implements Observer {
    private RecyclerView mRecyclerView;
    private MessageSubscriber mControlMessageSubscriber;
    private MessageSubscriber mStatusMessageSubscriber;
    private AppToHubMessageFeedbackHandler mAppToHubMessageFeedbackHandler;
    private StatusQueryFeedbackHandler mStatusQueryFeedbackHandler;
    private PushManager mPushManager;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        RoomDataOnHub.getInstance().registerObserver(this);
        initView();
        initPubNub();

        startUploadService();




    }
    public void initPubNub(){
        mPushManager = new PushManager();

        DeviceDataOnHub.getInstance().registerObserver(mPushManager);
        RoomDataOnHub.getInstance().registerObserver(mPushManager);

        MessageSender statusMessageSender = new MessageSender(ChannelNameGenerator.getAnswerStatusChannel());
        this.mStatusQueryFeedbackHandler= new StatusQueryFeedbackHandler(statusMessageSender);
        HubStatusDataOnHub.getInstance().registerObserver(this.mStatusQueryFeedbackHandler);

        StatusQueryMessageReceiver statusQueryMessageReceiver = new StatusQueryMessageReceiver();
        mStatusMessageSubscriber=
                new MessageSubscriber(ChannelNameGenerator.getQueryStatusChannel(), statusQueryMessageReceiver);
        this.mStatusMessageSubscriber.subscribe();


        MessageSender monitorMessageSender = new MessageSender(ChannelNameGenerator.getMonitorChannel());
        this.mAppToHubMessageFeedbackHandler = new AppToHubMessageFeedbackHandler(monitorMessageSender);
        DeviceDataOnHub.getInstance().registerObserver(this.mAppToHubMessageFeedbackHandler);
        RoomDataOnHub.getInstance().registerObserver(this.mAppToHubMessageFeedbackHandler);
        PlanDataOnHub.getInstance().registerObserver(this.mAppToHubMessageFeedbackHandler);

        AppToHubMessageReciever appToHubMessageReciever = new AppToHubMessageReciever();
        this.mControlMessageSubscriber=
                new MessageSubscriber(ChannelNameGenerator.getControlChannel(), appToHubMessageReciever);
        this.mControlMessageSubscriber.subscribe();
    }

    public void initView(){
        setTitle(ActivityTitles.HUB_ROOMS_ACTIVITY);
        ActivityHubRoomsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_hub_rooms);


        mRecyclerView = (RecyclerView)findViewById(R.id.hub_recycler_room);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyItemDecorator(50));
        HubRoomsActivityRoomAdapter adapter=new HubRoomsActivityRoomAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    public void startUploadService(){
        mIntent = new Intent(this, UploadService.class);
        startService(mIntent);
    }

    @Override
    public void onBackPressed() {
        //HubStatusDataOnHub.getInstance().setOnlineStatus(StatusOfHub.OFFLINE);
        HubStatusDataOnHub.getInstance().removeObserver(this.mStatusQueryFeedbackHandler);

        RoomDataOnHub.getInstance().removeObserver(this);

        DeviceDataOnHub.getInstance().removeObserver(mPushManager);
        RoomDataOnHub.getInstance().removeObserver(mPushManager);

        DeviceDataOnHub.getInstance().removeObserver(this.mAppToHubMessageFeedbackHandler);
        RoomDataOnHub.getInstance().removeObserver(this.mAppToHubMessageFeedbackHandler);
        PlanDataOnHub.getInstance().removeObserver(this.mAppToHubMessageFeedbackHandler);
        this.mControlMessageSubscriber.unSubcribe();
        this.mStatusMessageSubscriber.unSubcribe();
        stopService(mIntent);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {

        //HubStatusDataOnHub.getInstance().setOnlineStatus(StatusOfHub.OFFLINE);
        HubStatusDataOnHub.getInstance().removeObserver(this.mStatusQueryFeedbackHandler);

        RoomDataOnHub.getInstance().removeObserver(this);

        DeviceDataOnHub.getInstance().removeObserver(mPushManager);
        RoomDataOnHub.getInstance().removeObserver(mPushManager);

        DeviceDataOnHub.getInstance().removeObserver(this.mAppToHubMessageFeedbackHandler);
        RoomDataOnHub.getInstance().removeObserver(this.mAppToHubMessageFeedbackHandler);
        PlanDataOnHub.getInstance().removeObserver(this.mAppToHubMessageFeedbackHandler);
        this.mControlMessageSubscriber.unSubcribe();
        this.mStatusMessageSubscriber.unSubcribe();

        stopService(mIntent);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        //HubStatusDataOnHub.getInstance().setOnlineStatus(StatusOfHub.OFFLINE);
        super.onPause();
    }

    @Override
    protected void onResume() {
        //HubStatusDataOnHub.getInstance().setOnlineStatus(StatusOfHub.ONLINE);
        super.onResume();
    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action) {
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_toolbar_control_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.room_panel_settings) {
            Intent i =new Intent(this,SettingActivity.class);
            startActivity(i);
            return true;
        }else if(id == R.id.room_panel_back){
            finish();
        }else if(id == R.id.room_panel_logout){
            AWSMobileClient.defaultMobileClient().getIdentityManager().signOut();
            AWSMobileClient.defaultMobileClient().getIdentityManager().signInOrSignUp(this,new SignInHandler());
        }

        return super.onOptionsItemSelected(item);
    }

}
