package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.SeekBar;

import com.example.zeyupeng.smarthome.BR;
import com.example.zeyupeng.smarthome.SettingKeys;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zeyu peng on 2017-08-10.
 */

public class SettingActivityViewModel extends BaseObservable {

    private int uploadInterval;
    private int detectInterval;
    private SharedPreferences sharedPref;


    public SettingActivityViewModel(Context context) {
        getSetings(context);
    }


    public void getSetings(Context context){
        this.sharedPref = context.getSharedPreferences(SettingKeys.SMART_HOME_SETTING, MODE_PRIVATE);
        this.setDetectInterval(sharedPref.getInt(SettingKeys.ONLINE_DETECTOR_TIME_INTERVAL,15));
        this.setUploadInterval(sharedPref.getInt(SettingKeys.UPLOADOR_TIME_INTERVAL,5));
    }

    public void changeUploadInterval(SeekBar seekBar){
        if(seekBar.getProgress()==0){
            this.setUploadInterval(1);
        }else{
            this.setUploadInterval(seekBar.getProgress());
        }
        sharedPref = seekBar.getContext().getSharedPreferences(SettingKeys.SMART_HOME_SETTING,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SettingKeys.UPLOADOR_TIME_INTERVAL, this.getUploadInterval());
        editor.commit();

    }
    public void changedetectInterval(SeekBar seekBar){
        if(seekBar.getProgress()==0){
            this.setDetectInterval(1);
        }else{
            this.setDetectInterval(seekBar.getProgress());
        }
        sharedPref = seekBar.getContext().getSharedPreferences(SettingKeys.SMART_HOME_SETTING,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SettingKeys.ONLINE_DETECTOR_TIME_INTERVAL, this.getDetectInterval());
        editor.commit();
    }
    @Bindable
    public int getUploadInterval() {
        return uploadInterval;
    }

    public void setUploadInterval(int uploadInterval) {
        this.uploadInterval = uploadInterval;
        notifyPropertyChanged(BR.uploadInterval);
    }
    @Bindable
    public int getDetectInterval() {
        return detectInterval;
    }

    public void setDetectInterval(int detectInterval) {
        this.detectInterval = detectInterval;
        notifyPropertyChanged(BR.detectInterval);
    }



}
