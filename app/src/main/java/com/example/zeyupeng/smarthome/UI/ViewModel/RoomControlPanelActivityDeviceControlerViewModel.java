package com.example.zeyupeng.smarthome.UI.ViewModel;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.LockStatus;
import com.example.zeyupeng.smarthome.Model.MyDevices.Adjuster;
import com.example.zeyupeng.smarthome.Model.MyDevices.DoorLock;
import com.example.zeyupeng.smarthome.Model.MyDevices.ModeSwitch;
import com.example.zeyupeng.smarthome.Model.MyDevices.NotificationSender;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductStatus;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyPlans.PlanActions;
import com.example.zeyupeng.smarthome.Model.MyPlans.PlanFactory;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageAction;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageEditer;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.PubNub.Message.PackageType;
import com.example.zeyupeng.smarthome.PubNub.RealTimeControlandMonitor.StreamProgressPoster;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.View.IntentTag;
import com.example.zeyupeng.smarthome.View.NFCCardSimulatorDialogActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeyu peng on 2017-06-21.
 */

public class RoomControlPanelActivityDeviceControlerViewModel extends BaseObservable {
    private String viewModelID;
    private String custumNameRC;
    private String productNameRC;
    private String productTypeRC;
    private String descriptionRC;
    private String statusRC;
    private List<String> controlTypeRC;
    private String locationID;
    private int imageRC;


    private String[] modeOptionsRC;
    private String modeRC;
    private int modeChosen;

    private String lockStatusRC;
    private String passwordRC;
    private String passwordInputRC;
    private boolean nFCmodifyPermit;
    private boolean resetPasswordModeRC;
    private String newPasswordRC;


    private int adjustableValueRC;
    private int maxValueRC;
    private String adjustableValueNameRC;


    private String minuteRC;
    private String hourRC;
    private List<String> repeatDateRC;
    private String planActionRC;

    private boolean notificationPermitRC;

    private MessageSender mMessageSender;
    private MessageEditer mMessageEditer;

    private StreamProgressPoster mProgressPoster;



    public RoomControlPanelActivityDeviceControlerViewModel() {
        this.mMessageEditer=new MessageEditer();
        this.repeatDateRC = new ArrayList<>();
        this.setMinuteRC("00");
        this.setHourRC("00");

    }



    public String getViewModelID() {
        return viewModelID;
    }
    @Bindable
    public String getCustumNameRC() {
        return custumNameRC;
    }
    @Bindable
    public String getProductNameRC() {
        return productNameRC;
    }
    @Bindable
    public String getProductTypeRC() {
        return productTypeRC;
    }
    @Bindable
    public String getDescriptionRC() {
        return descriptionRC;
    }
    @Bindable
    public String getStatusRC() {
        return statusRC;
    }
    @Bindable
    public int getImageRC() {
        return imageRC;
    }
    @Bindable
    public String[] getModeOptionsRC() {
        return modeOptionsRC;
    }
    @Bindable
    public String getModeRC() {
        return modeRC;
    }

    public int getModeChosen() {
        return modeChosen;
    }

    @Bindable
    public int getAdjustableValueRC() {
        return adjustableValueRC;
    }
    @Bindable
    public int getMaxValueRC() {
        return maxValueRC;
    }
    @Bindable
    public String getAdjustableValueNameRC() {
        return adjustableValueNameRC;
    }

    @Bindable
    public String getLockStatusRC() {
        return lockStatusRC;
    }
    @Bindable
    public String getPasswordRC() {
        return passwordRC;
    }
    @Bindable
    public String getPasswordInputRC() {
        return passwordInputRC;
    }
    public boolean getNFCModifyPermit() {
        return nFCmodifyPermit;
    }
    @Bindable
    public boolean getResetPasswordModeRC() {
        return resetPasswordModeRC;
    }
    @Bindable
    public String getNewPasswordRC() {
        return newPasswordRC;
    }
    @Bindable
    public String getMinuteRC() {
        return minuteRC;
    }
    @Bindable
    public String getHourRC() {
        return hourRC;
    }
    @Bindable
    public List<String> getRepeatDateRC() {
        return repeatDateRC;
    }
    @Bindable
    public String getPlanActionRC() {
        return planActionRC;
    }
    @Bindable
    public boolean getNotificationPermitRC() {
        return notificationPermitRC;
    }

    public List<String> getControlTypeRC() {
        return controlTypeRC;
    }





    public void setViewModelID(String viewModelID) {
        this.viewModelID = viewModelID;
    }

    public void setCustumNameRC(String custumName) {
        this.custumNameRC = custumName;
        notifyPropertyChanged(BR.custumNameRC);
    }

    public void setProductNameRC(String productName) {
        this.productNameRC = productName;
        notifyPropertyChanged(BR.productNameRC);
    }

    public void setProductTypeRC(String productType) {
        this.productTypeRC = productType;
        notifyPropertyChanged(BR.productTypeRC);
    }

    public void setDescriptionRC(String description) {
        this.descriptionRC = description;
        notifyPropertyChanged(BR.descriptionRC);
    }

    public void setStatusRC(String status) {
        this.statusRC = status;
        notifyPropertyChanged(BR.statusRC);
    }

    public void setImageRC(int imageRC) {
        this.imageRC = imageRC;
        notifyPropertyChanged(BR.imageRC);
    }

    public void setControlTypeRC(List<String> controlTypeRC) {
        this.controlTypeRC = controlTypeRC;
    }

    public void setModeOptionsRC(String[] modeOptions) {
        this.modeOptionsRC = modeOptions;
        notifyPropertyChanged(BR.modeOptionsRC);
    }

    public void setModeRC(String mode) {
        this.modeRC = mode;
        notifyPropertyChanged(BR.modeRC);
    }



    public void setModeChosen(int modeChosen) {
        this.modeChosen = modeChosen;
    }

    public void setAdjustableValueRC(int adjustableValueRC) {
        this.adjustableValueRC = adjustableValueRC;
        notifyPropertyChanged(BR.adjustableValueRC);
    }

    public void setMaxValueRC(int maxValueRC) {
        this.maxValueRC = maxValueRC;
        notifyPropertyChanged(BR.maxValueRC);
    }

    public void setAdjustableValueNameRC(String adjustableValueNameRC) {
        this.adjustableValueNameRC = adjustableValueNameRC;
        notifyPropertyChanged(BR.adjustableValueNameRC);
    }

    public void setLockStatusRC(String lockStatus) {
        this.lockStatusRC = lockStatus;
        notifyPropertyChanged(BR.lockStatusRC);
    }

    public void setPasswordRC(String passwordRC) {
        this.passwordRC = passwordRC;
        notifyPropertyChanged(BR.passwordRC);
    }


    public void setPasswordInputRC(String passwordInputRC) {
        this.passwordInputRC = passwordInputRC;
        notifyPropertyChanged(BR.passwordInputRC);
    }


    public void setNFCModifyPermit(boolean modifyPermit) {
        this.nFCmodifyPermit = modifyPermit;
    }

    public void setResetPasswordModeRC(boolean resetPasswordModeRC) {
        this.resetPasswordModeRC = resetPasswordModeRC;
        notifyPropertyChanged(BR.resetPasswordModeRC);
    }

    public void setNewPasswordRC(String newPasswordRC) {
        this.newPasswordRC = newPasswordRC;
        notifyPropertyChanged(BR.newPasswordRC);
    }

    public void setMinuteRC(String minuteRC) {
        this.minuteRC = minuteRC;
        notifyPropertyChanged(BR.minuteRC);
    }

    public void setHourRC(String hourRC) {
        this.hourRC = hourRC;
        notifyPropertyChanged(BR.hourRC);
    }

    public void setRepeatDateRC(List<String> repeatDateRC) {
        this.repeatDateRC = repeatDateRC;
        notifyPropertyChanged(BR.repeatDateRC);
    }

    public void setPlanActionRC(String planActionRC) {
        this.planActionRC = planActionRC;
        notifyPropertyChanged(BR.planActionRC);
    }

    public void setNotificationPermitRC(boolean notificationPermitRC) {
        this.notificationPermitRC = notificationPermitRC;
        notifyPropertyChanged(BR.notificationPermitRC);
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }


    public MessageSender getMessageSender() {
        return mMessageSender;
    }

    public void setMessageSender(MessageSender messageSender) {
        mMessageSender = messageSender;
    }


    public void deviceSwitch(View view,boolean status){
        com.example.zeyupeng.smarthome.Model.MyDevices.Switch device = (com.example.zeyupeng.smarthome.Model.MyDevices.Switch) DeviceDataOnMobile.getInstance().getDevice(viewModelID);
        if (status){
            if(statusRC.equals(ProductStatus.OFF)){
                device.turnOn();
                postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,device,MessageAction.MODIFY);
            }
        }else {
            Log.i("switch test","false");
            if(statusRC.equals(ProductStatus.ON)){
                device.turnOff();
                postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,device,MessageAction.MODIFY);
            }
        }


    }

    public void chooseMode(AdapterView<?> adapterView, View view, int i, long l){
        setModeChosen(i);

    }

    public void confirmMode(View view){

        ModeSwitch modeSwitch=(ModeSwitch)DeviceDataOnMobile.getInstance().getDevice(viewModelID);

        modeSwitch.chooseMode(getModeChosen());
        postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
        sendMessage(PackageType.PACKAGE_TYPE_DEVICE,modeSwitch,MessageAction.MODIFY);
    }


    public void stopAdjusting(SeekBar seekBar) {
        Log.i("SeekBar","onStopTrackingTouch.............");

        Adjuster adjuster=(Adjuster)DeviceDataOnMobile.getInstance().getDevice(viewModelID);
        adjuster.adjust(seekBar.getProgress());
        postProgress(seekBar.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
        sendMessage(PackageType.PACKAGE_TYPE_DEVICE,adjuster,MessageAction.MODIFY);

    }

    public void enableNotification(View view,boolean enableNotification){
        NotificationSender sender =(NotificationSender)DeviceDataOnMobile.getInstance().getDevice(viewModelID);
        if (enableNotification){
            if(!notificationPermitRC){
                sender.setNotificationPermit(true);
                postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,sender,MessageAction.MODIFY);
            }
        }else {
            if(notificationPermitRC){
                sender.setNotificationPermit(false);
                postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,sender,MessageAction.MODIFY);
            }
        }

    }

    public void modify(RoomControlPanelActivityDeviceControlerViewModel viewModel){
        this.setCustumNameRC(viewModel.getCustumNameRC());
        this.setProductNameRC(viewModel.getProductNameRC());
        this.setProductTypeRC(viewModel.getProductTypeRC());
        this.setStatusRC(viewModel.getStatusRC());
        this.setDescriptionRC(viewModel.getDescriptionRC());
        this.setImageRC(viewModel.getImageRC());

        this.setModeOptionsRC(viewModel.getModeOptionsRC());
        this.setModeRC(viewModel.getModeRC());

        this.setMaxValueRC(viewModel.getMaxValueRC());
        this.setAdjustableValueRC(viewModel.getAdjustableValueRC());
        this.setAdjustableValueNameRC(viewModel.getAdjustableValueNameRC());

        this.setNFCModifyPermit(viewModel.getNFCModifyPermit());
        this.setPasswordRC(viewModel.getPasswordRC());
        this.setLockStatusRC(viewModel.getLockStatusRC());

        this.setNotificationPermitRC(viewModel.getNotificationPermitRC());


    }

    public void deleteDevice(View view){



        postProgress(view.getContext()
                ,PackageType.PACKAGE_TYPE_DEVICE
                ,viewModelID);

        sendMessage(PackageType.PACKAGE_TYPE_DEVICE
                ,DeviceDataOnMobile.getInstance().getDevice(viewModelID)
                ,MessageAction.REMOVE);



    }

    public void selectTime(View view){

        new TimePickerDialog(view.getContext(),new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                setHourRC(String.valueOf(i));
                setMinuteRC(String.valueOf(i1));
            }
        },0,0,true).show();
    }
    public void selectDate(View view){
        TextView textView =(TextView)view;
        if(this.repeatDateRC.contains(textView.getText().toString())){
            this.repeatDateRC.remove(textView.getText().toString());
            textView.setTextColor(view.getContext().getResources().getColor(R.color.cardViewText_1));

        }else {
            this.repeatDateRC.add(textView.getText().toString());
            textView.setTextColor(view.getContext().getResources().getColor(R.color.cardViewText_Light));
        }
    }

    public void selectPlanTurnOnAction(View view){
        this.setPlanActionRC(PlanActions.TURN_ON);

        Log.i("ppppppplan action",this.planActionRC);
    }
    public void selectPlanTurnOffAction(View view){
        this.setPlanActionRC(PlanActions.TURN_OFF);
        Log.i("ppppppplan action",this.planActionRC);
    }

    public void addPlan(View view){
        if(this.planActionRC==null){
            Toast.makeText(view.getContext(),"please select an action",Toast.LENGTH_SHORT).show();
        }else {
            PlanFactory planFactory= new PlanFactory();
            Plan plan =planFactory.createPlan();
            plan.setMinute(Integer.parseInt(this.minuteRC));
            plan.setHour(Integer.parseInt(this.hourRC));
            plan.setDeviceID(this.viewModelID);
            plan.setPlanAction(this.planActionRC);
            plan.setRepeatDate(this.repeatDateRC);

            postProgress(view.getContext(),PackageType.PACKAGE_TYPE_PLAN,plan.getPlanID());
            sendMessage(PackageType.PACKAGE_TYPE_PLAN,plan,MessageAction.ADD);
        }

    }

    public void inputPassword(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("password",charSequence.toString());
        this.setPasswordInputRC(charSequence.toString());
    }

    public void lockSwitch(View view,boolean lockStatus){
        DoorLock doorLock = (DoorLock) DeviceDataOnMobile.getInstance().getDevice(viewModelID);

        if(lockStatus){
            if(this.lockStatusRC.equals(LockStatus.UNLOCKED)){
                doorLock.lock();
                postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                sendMessage(PackageType.PACKAGE_TYPE_DEVICE,doorLock,MessageAction.MODIFY);
            }
        }else{
            if(this.lockStatusRC.equals(LockStatus.LOCKED)){
                if(doorLock.getDoorLockModifyPermit()){
                    doorLock.unlock(doorLock.getPassword(),view.getContext());
                    doorLock.setDoorLockModifyPermit(false);
                    postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                    sendMessage(PackageType.PACKAGE_TYPE_DEVICE,doorLock,MessageAction.MODIFY);
                    this.setPasswordInputRC(null);
                }else{
                    doorLock.unlock(passwordInputRC,view.getContext());
                    if(doorLock.checkPassword(passwordInputRC)){
                        doorLock.setDoorLockModifyPermit(true);
                        postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
                        sendMessage(PackageType.PACKAGE_TYPE_DEVICE,doorLock,MessageAction.MODIFY);
                        this.setPasswordInputRC(null);
                    }else {
                        this.setLockStatusRC(LockStatus.LOCKED);
                    }
                }
            }
        }
    }
    public void enterResetPasswordMode(View view){
        if(this.getResetPasswordModeRC()){
            this.setResetPasswordModeRC(false);
        }else {
            this.setResetPasswordModeRC(true);
        }
    }
    public void inputNewPassword(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("password","password"+charSequence.toString());
        this.setNewPasswordRC(charSequence.toString());
    }
    public void confirmRestePassword(View view){
        DoorLock doorLock = (DoorLock) DeviceDataOnMobile.getInstance().getDevice(viewModelID);
        if(newPasswordRC!=null&&doorLock.checkPassword(passwordInputRC)){
            doorLock.resetPassword(passwordInputRC,newPasswordRC,view.getContext());
            postProgress(view.getContext(),PackageType.PACKAGE_TYPE_DEVICE,viewModelID);
            sendMessage(PackageType.PACKAGE_TYPE_DEVICE,doorLock,MessageAction.MODIFY);
            this.setNewPasswordRC(null);
            this.setPasswordInputRC(null);
            this.setResetPasswordModeRC(false);
        }else{
            Toast.makeText(view.getContext(),"Old password is not correct or new password is empty",Toast.LENGTH_SHORT).show();
        }
    }

    public void startCardSimulation(View view){
        Intent i =new Intent(view.getContext(), NFCCardSimulatorDialogActivity.class);
        i.putExtra(IntentTag.CARD_SIMULATOR.toString(),viewModelID);
        view.getContext().startActivity(i);
    }

    public void sendMessage(String packageType, Object object,String actionType){
        mMessageSender.sendRealTimeMessage(mMessageEditer.getMessage(packageType,object,actionType));
    }
    public void postProgress(Context context, String packageType, String messageID){
        mProgressPoster = new StreamProgressPoster(context,packageType,messageID);
        mProgressPoster.startSteaming();

    }


}
