package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.HomeDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.databinding.ItemRoomMainBinding;


import java.util.ArrayList;

/**
 * Created by zeyu peng on 2017-06-19.
 */

public class MainActivityRoomAdapter extends RecyclerView.Adapter<MainActivityRoomViewHolder> {

    private Context mContext;
    private String[] mkeys;
    private LayoutInflater mLayoutInflater;

    public MainActivityRoomAdapter(Context context) {
        mContext = context;
    }


    @Override
    public MainActivityRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mLayoutInflater==null){
            mLayoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemRoomMainBinding itemRoomMainBinding = ItemRoomMainBinding.inflate(mLayoutInflater,parent,false);
        return new MainActivityRoomViewHolder(itemRoomMainBinding);
    }

    @Override
    public void onBindViewHolder(MainActivityRoomViewHolder holder, int position) {
        try{
            mkeys=MainActivityRoomViewModelMap
                    .getInstance()
                    .getViewModelMap()
                    .keySet()
                    .toArray(
                            new String[MainActivityRoomViewModelMap
                                    .getInstance()
                                    .getViewModelMap()
                                    .size()]);
            MainActivityRoomViewModel mainActivityRoomViewModel=MainActivityRoomViewModelMap
                    .getInstance()
                    .getViewModel(mkeys[position]);
            holder.bind(mainActivityRoomViewModel);
        }catch (Exception e){
            Toast.makeText(mContext,"fail to load Room data, adapter can not find the view model", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return MainActivityRoomViewModelMap
                .getInstance()
                .getViewModelMap()
                .size();
    }

}
