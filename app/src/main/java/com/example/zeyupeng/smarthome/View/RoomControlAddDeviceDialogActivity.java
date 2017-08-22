package com.example.zeyupeng.smarthome.View;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amazonaws.models.nosql.RoomData;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.UI.ViewModel.RoomControlAddDeviceDialogViewModel;
import com.example.zeyupeng.smarthome.databinding.ActivityRoomControlAddDeviceDialogBinding;

public class RoomControlAddDeviceDialogActivity extends Activity implements Observer {
    private String mRoomID;
    private RoomControlAddDeviceDialogViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ActivityTitles.ROOM_CONTROL_ADD_DEVICE_DIALOG_ACTIVITY);
        ActivityRoomControlAddDeviceDialogBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_room_control_add_device_dialog);
        mViewModel=new RoomControlAddDeviceDialogViewModel();
        Intent i = getIntent();
        mRoomID=i.getStringExtra(IntentTag.ROOM_CONTROL_PANEL_TO_ADD_DEVICE_ROOMID.toString());
        mViewModel.setRoomID(mRoomID);
        binding.setAddDeviceDialogViewModel(mViewModel);
        DeviceDataOnMobile.getInstance().registerObserver(this);

    }

    @Override
    protected void onDestroy() {
        DeviceDataOnMobile.getInstance().removeObserver(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DeviceDataOnMobile.getInstance().removeObserver(this);
        super.onBackPressed();
    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action){
            case ADD_DEVICE:
                //DeviceDataOnMobile.getInstance().removeObserver(this);
                //finish();
                break;
        }
    }
}
