package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.PubNub.Message.MessageSender;
import com.example.zeyupeng.smarthome.databinding.ItemDeviceControlerActivityRoomControlPanelBinding;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-06-21.
 */

public class RoomControlPanelActivityDeviceControlerAdapter extends RecyclerView.Adapter<RoomControlPanelActivityDeviceControlerViewHolder> {

    private Context mContext;
    private List<String> mkeys;
    private LayoutInflater mLayoutInflater;
    private MessageSender mMessageSender;
    private String mRoomID;

    public RoomControlPanelActivityDeviceControlerAdapter(Context context,String roomID, String productType,MessageSender messageSender) {
        mContext = context;
        this.mkeys=new ArrayList<>();
        this.mMessageSender=messageSender;
        this.mRoomID=roomID;

        try {
            for (Map.Entry<String, String>
                    entry: RoomDataOnMobile.getInstance().getRoom(mRoomID).getDeviceMap().entrySet()){
                Log.i("dddddddebug","RoomControlPanelActivityDeviceControlerAdapter   update ui");

                try {
                    if(RoomControlPanelActivityDeviceControlerViewModelMap
                            .getInstance()
                            .getViewModel(entry.getKey())
                            .getProductTypeRC()
                            .equals(productType)){
                        this.mkeys.add(entry.getKey());
                    }
                }catch (NullPointerException e){
                    Toast.makeText(context,"Fail to load Devices, adapter can not find it", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(context,"Fail to find DevicesMap, adapter can not find it", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public RoomControlPanelActivityDeviceControlerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mLayoutInflater==null){
            mLayoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemDeviceControlerActivityRoomControlPanelBinding Binding = ItemDeviceControlerActivityRoomControlPanelBinding
                .inflate(mLayoutInflater,parent,false);
        return new RoomControlPanelActivityDeviceControlerViewHolder(Binding);
    }

    @Override
    public void onBindViewHolder(RoomControlPanelActivityDeviceControlerViewHolder holder, int position) {
        try{
            RoomControlPanelActivityDeviceControlerViewModel viewModel=RoomControlPanelActivityDeviceControlerViewModelMap
                    .getInstance()
                    .getViewModel(mkeys.get(position));
            viewModel.setMessageSender(mMessageSender);
            holder.bind(viewModel);
        }catch(Exception e){
            Toast.makeText(mContext,"fail to load devices, adapter can not find the view model", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public int getItemCount() {
        //return 1;
        return this.mkeys.size();
        //this method is called right after constructor, so the itemcount must be set as soon as possible, do not put it in a for statement
    }

}
