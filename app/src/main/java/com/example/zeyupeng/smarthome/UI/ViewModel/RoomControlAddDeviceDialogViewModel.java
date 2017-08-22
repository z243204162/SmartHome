package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.AccessFactory;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.CookingFactory;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.CoolingHeatingFactory;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.DeviceFactory;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.LightingFactory;
import com.example.zeyupeng.smarthome.Model.MyDevices.Factory.ShadingFactory;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductList;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductType;
import com.example.zeyupeng.smarthome.PubNub.Message.ChannelNameGenerator;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;

/**
 * Created by zeyu peng on 2017-07-14.
 */

public class RoomControlAddDeviceDialogViewModel extends BaseObservable {
    private EditText mEditText;
    private String mCustomNameAD;
    private String mDeviceNameAD;
    private String mProductType;



    private String mRoomID;
    private View mView;
    private AbstractDevice mDevice;


    private DeviceFactory mDeviceFactory;

    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;

    private StreamProgressPoster mProgressPoster;

    public RoomControlAddDeviceDialogViewModel() {

        mMessageSender = new MessageSender(ChannelNameGenerator.getControlChannel());
        mMessageEditer = new MessageEditer();
    }

    @Bindable
    public String getCustomNameAD() {
        return mCustomNameAD;
    }

    public void setCustomNameAD(String customNameAD) {
        this.mCustomNameAD = customNameAD;
        notifyPropertyChanged(BR.customNameAD);
    }
    @Bindable
    public String getDeviceNameAD() {
        return mDeviceNameAD;
    }

    public void setDeviceNameAD(String deviceNameAD) {
        this.mDeviceNameAD = deviceNameAD;
        notifyPropertyChanged(BR.deviceNameAD);
    }

    public String getRoomID() {
        return mRoomID;
    }

    public void setRoomID(String roomID) {
        mRoomID = roomID;
    }

    public void chooseDevice(View view){
        mView =view;
        OptionsPickerView pickerView=
                new OptionsPickerView.Builder(view.getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int Options1, int options2, int options3, View v) {
                mProductType = ProductType.getInstance().getProductTypeList().get(Options1);

                switch (mProductType){
                    case ProductType.LIGHTING:
                        mDeviceFactory = new LightingFactory();
                        break;
                    case ProductType.ACCESS:
                        mDeviceFactory = new AccessFactory();
                        break;
                    case ProductType.COOKING:
                        mDeviceFactory = new CookingFactory();
                        break;
                    case ProductType.COOLING_AND_HEATING:
                        mDeviceFactory = new CoolingHeatingFactory();
                        break;
                    case ProductType.SHADING:
                        mDeviceFactory = new ShadingFactory();
                        break;
                }


                OptionsPickerView pickerView=
                        new OptionsPickerView.Builder(mView.getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                setDeviceNameAD(ProductList.getInstance().getProductListMap().get(mProductType).get(options1));
                                mDevice=mDeviceFactory.createDevice(mDeviceNameAD);
                                try {
                                    setCustomNameAD(mEditText.getText().toString());
                                }catch(Exception e){
                                    Toast.makeText(mView.getContext()
                                            ,"the custom name of device is null"
                                            ,Toast.LENGTH_SHORT).show();
                                }
                                Log.i("addddddd device",""+mDevice.getProductID());
                            }
                        }).build();
                pickerView.setPicker(
                        ProductList.getInstance().getProductListMap().get(mProductType));
                pickerView.show();

            }
        }).build();
        pickerView.setPicker(ProductType.getInstance().getProductTypeList());
        pickerView.show();
    }

    public void editRoomName(View view){
        EditText editText = (EditText)view;
        this.mEditText=editText;
        Log.i("adddddddddroom","..........");
    }

    public void addDevice(View view){
        if(mDevice!=null){
            mDevice.setLocationID(mRoomID);
            try {
                setCustomNameAD(mEditText.getText().toString());
            }catch(Exception e){
                Toast.makeText(view.getContext(),"the custom name of device is null",Toast.LENGTH_SHORT).show();
                setCustomNameAD("New "+mDevice.getProductName());
            }
            mDevice.setCustomName(mCustomNameAD);

            postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,mDevice.getProductID());
            sendMessage(PackageType.PACKAGE_TYPE_DEVICE
                    , mDevice
                    , MessageAction.ADD);
        }else {
            Toast.makeText(view.getContext(),"please choose a device",Toast.LENGTH_SHORT).show();
        }

    }

    public void sendMessage(String packageType, Object object,String actionType){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,actionType));
    }

    public void postProgress(Context context, String packageType, String messageID){
        mProgressPoster = new StreamProgressPoster(context,packageType,messageID);
        mProgressPoster.startSteaming();

    }

    public void cancel(View view){
        ((Activity)view.getContext()).finish();
    }

}
