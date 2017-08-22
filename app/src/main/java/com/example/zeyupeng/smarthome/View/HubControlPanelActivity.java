package com.example.zeyupeng.smarthome.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amazonaws.mobile.AWSMobileClient;
import com.example.zeyupeng.smarthome.AWS.loginsystem.SignInHandler;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductType;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSubscriber;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityDeviceSimulatorAdapter;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityHeaderViewModel;
import com.example.zeyupeng.smarthome.UI.ViewModel.HubControlPanelActivityHeaderViewModelMap;
import com.example.zeyupeng.smarthome.View.CustomView.MyItemDecorator;
import com.example.zeyupeng.smarthome.databinding.ActivityHubControlPanelBinding;


public class HubControlPanelActivity extends AppCompatActivity  implements Observer{
    private RecyclerView mLightingRecyclerView;
    private RecyclerView mAccessRecyclerView;
    private RecyclerView mCookingRecyclerView;
    private RecyclerView mACRecyclerView;
    private RecyclerView mShadingRecyclerView;
    private String mRoomID;
    private MessageSubscriber mMessageSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoomDataOnHub.getInstance().registerObserver(this);
        DeviceDataOnHub.getInstance().registerObserver(this);
        initView();

    }
    public void initView(){
        setTitle(ActivityTitles.HUB_CONTROL_PANEL_ACTIVITY);
        Intent i = getIntent();
        mRoomID=i.getStringExtra(IntentTag.HUB_TO_HUB_CONTROL_PANEL_ROOMID.toString());

        try{
            ActivityHubControlPanelBinding binding = DataBindingUtil
                    .setContentView(this, R.layout.activity_hub_control_panel);
            HubControlPanelActivityHeaderViewModel headerViewModel = HubControlPanelActivityHeaderViewModelMap
                    .getInstance()
                    .getViewModel(mRoomID);
            binding.setHubControlPanelActivityHeaderViewModel(headerViewModel);
        }catch (Exception e){
            Toast.makeText(this,"fail to load room data, adapter can not find the view model", Toast.LENGTH_SHORT).show();
        }

        initRecyclerView(mLightingRecyclerView,R.id.recycler_Lighting_hub_control_panel,ProductType.LIGHTING);
        initRecyclerView(mAccessRecyclerView,R.id.recycler_Access_hub_control_panel,ProductType.ACCESS);
        initRecyclerView(mCookingRecyclerView,R.id.recycler_Cooking_hub_control_panel,ProductType.COOKING);
        initRecyclerView(mACRecyclerView,R.id.recycler_ACandHeating_hub_control_panel,ProductType.COOLING_AND_HEATING);
        initRecyclerView(mShadingRecyclerView,R.id.recycler_Shading_hub_control_panel,ProductType.SHADING);


    }

    public void initRecyclerView(RecyclerView recyclerView,int viewID,String productType){
        recyclerView = (RecyclerView)findViewById(viewID);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyItemDecorator(30));
        HubControlPanelActivityDeviceSimulatorAdapter adapter=new HubControlPanelActivityDeviceSimulatorAdapter(this,
                mRoomID, productType);
        recyclerView.setAdapter(adapter);
        //new LinearSnapHelper().attachToRecyclerView(recyclerView);

    }


    @Override
    public void update(ObserverActions action, Object object) {

            switch (action) {
                case ADD_DEVICE:
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initView();
                        }
                    });
                    break;
                case REMOVE_DEVICE:
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initView();
                        }
                    });
                    break;
                case REMOVE_ROOM:
                    finish();
                    break;

            }
    }

    @Override
    protected void onDestroy() {
        RoomDataOnHub.getInstance().removeObserver(this);
        DeviceDataOnHub.getInstance().removeObserver(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        RoomDataOnHub.getInstance().removeObserver(this);
        DeviceDataOnHub.getInstance().removeObserver(this);
        super.onBackPressed();
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
