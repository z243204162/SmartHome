package com.example.zeyupeng.smarthome.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.ControlerType;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;
import com.example.zeyupeng.smarthome.NFC.CardPassword;
import com.example.zeyupeng.smarthome.R;

public class NFCCardSimulatorDialogActivity extends Activity implements Observer{
    private String mDeviceID;
    private String mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfccard_simulator_dialog);

        DeviceDataOnMobile.getInstance().registerObserver(this);



        Intent i = getIntent();
        mDeviceID=i.getStringExtra(IntentTag.CARD_SIMULATOR.toString());
        mPassword=((DoorLock)DeviceDataOnMobile.getInstance().getDevice(mDeviceID)).getPassword();
        CardPassword.getInstance().setPassword(mPassword);
    }

    @Override
    public void update(ObserverActions action, Object object) {
        if(action.equals(ObserverActions.MODIFY_DEVICE)
                &&((AbstractDevice)object).getControlerType().contains(ControlerType.NFC_DOOR_LOCK)){
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        DeviceDataOnMobile.getInstance().removeObserver(this);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        DeviceDataOnMobile.getInstance().removeObserver(this);
        super.onDestroy();
    }
}
