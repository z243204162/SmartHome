package com.example.zeyupeng.smarthome.View;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.Observer;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.ObserverActions;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.R;
import com.example.zeyupeng.smarthome.UI.ViewModel.MainActivityAddRoomViewModel;
import com.example.zeyupeng.smarthome.databinding.ActivityMainAddRoomDialogBinding;


public class MainAddRoomDialogActivity extends Activity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ActivityTitles.MAIN_ADD_RO0M_DIALOG_ACTIVITY);
        ActivityMainAddRoomDialogBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_main_add_room_dialog);
        MainActivityAddRoomViewModel viewModel=new MainActivityAddRoomViewModel();


        binding.setMainActivityAddRoomViewModel(viewModel);
        RoomDataOnMobile.getInstance().registerObserver(this);
    }


    @Override
    public void onBackPressed() {
        RoomDataOnMobile.getInstance().removeObserver(this);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        RoomDataOnMobile.getInstance().removeObserver(this);
        super.onDestroy();
    }

    @Override
    public void update(ObserverActions action, Object object) {
        switch (action){
            case ADD_ROOM:
                //RoomDataOnMobile.getInstance().removeObserver(this);
                finish();
                break;
        }
    }
}
