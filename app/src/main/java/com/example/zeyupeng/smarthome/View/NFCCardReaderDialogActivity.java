package com.example.zeyupeng.smarthome.View;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;

import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.NFC.CardReader;
import com.example.zeyupeng.smarthome.R;

public class NFCCardReaderDialogActivity extends Activity implements CardReader.AccountCallback{

    public static int READER_FLAGS =
            NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;
    public CardReader mCardReader;
    private String mDeviceID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfccard_reader_dialog);

        mCardReader = new CardReader(this);

        // Disable Android Beam and register our card reader callback
        Intent i = getIntent();
        mDeviceID=i.getStringExtra(IntentTag.CARD_READER.toString());

        enableReaderMode();
    }
    @Override
    public void onPause() {
        super.onPause();
        disableReaderMode();
    }

    @Override
    public void onResume() {
        super.onResume();
        enableReaderMode();
    }

    private void enableReaderMode() {


        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(NFCCardReaderDialogActivity.this);
        if (nfc != null) {
            nfc.enableReaderMode(NFCCardReaderDialogActivity.this, mCardReader, READER_FLAGS, null);
        }
    }

    private void disableReaderMode() {

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(NFCCardReaderDialogActivity.this);
        if (nfc != null) {
            nfc.disableReaderMode(NFCCardReaderDialogActivity.this);
        }
    }

    @Override
    public void onAccountReceived(final String password) {
        // This callback is run on a background thread, but updates to UI elements must be performed
        // on the UI thread.
        Log.i("nfc","call back is called");
        Log.i("nfc",password);

        DoorLock doorLock = (DoorLock) DeviceDataOnHub.getInstance().getDevice(mDeviceID);
        if(doorLock.checkPassword(password)){
            doorLock.unlock(password,NFCCardReaderDialogActivity.this);
            doorLock.setDoorLockModifyPermit(true);
            DeviceDataOnHub.getInstance().modifyDevice((AbstractDevice) doorLock);
            finish();
        }



    }


}
