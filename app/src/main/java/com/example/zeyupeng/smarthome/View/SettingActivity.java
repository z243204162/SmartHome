package com.example.zeyupeng.smarthome.View;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.UI.ViewModel.SettingActivityViewModel;
import com.example.zeyupeng.smarthome.databinding.ActivitySettingBinding;

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView(){
        setTitle(ActivityTitles.SETTING_ACTIVITY);
        ActivitySettingBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_setting);
        SettingActivityViewModel viewModel=new SettingActivityViewModel(this);
        binding.setSettingViewModel(viewModel);
    }
}
