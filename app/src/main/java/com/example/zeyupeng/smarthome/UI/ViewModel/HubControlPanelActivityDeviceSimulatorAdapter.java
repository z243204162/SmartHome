package com.example.zeyupeng.smarthome.UI.ViewModel;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;

import com.example.zeyupeng.smarthome.databinding.ItemDeviceSimulatorActivityHubRoomControlPanelBinding;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityDeviceSimulatorAdapter extends RecyclerView.Adapter<HubControlPanelActivityDeviceSimulatorViewHolder> {

    private Context mContext;
    private List<String> mkeys;
    private LayoutInflater mLayoutInflater;


    public HubControlPanelActivityDeviceSimulatorAdapter(Context context,String roomID, String productType) {
        mContext = context;
        this.mkeys=new ArrayList<>();

        try{
            for (Map.Entry<String, String> entry: RoomDataOnHub.getInstance().getRoom(roomID).getDeviceMap().entrySet()){
                try{
                    if(HubControlPanelActivityDeviceSimulatorViewModelMap
                            .getInstance()
                            .getViewModel(entry.getKey())
                            .getProductTypeHC()
                            .equals(productType)){
                        this.mkeys.add(entry.getKey());
                    }
                }catch (NullPointerException e){
                    Toast.makeText(context,"Fail to load Devices, adapter can not find it", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(context,"Fail to load DevicesMap, adapter can not find it", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public HubControlPanelActivityDeviceSimulatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mLayoutInflater==null){
            mLayoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemDeviceSimulatorActivityHubRoomControlPanelBinding Binding = ItemDeviceSimulatorActivityHubRoomControlPanelBinding
                .inflate(mLayoutInflater,parent,false);
        return new HubControlPanelActivityDeviceSimulatorViewHolder(Binding);
    }

    @Override
    public void onBindViewHolder(HubControlPanelActivityDeviceSimulatorViewHolder holder, int position) {

        try{
            HubControlPanelActivityDeviceSimulatorViewModel viewModel=HubControlPanelActivityDeviceSimulatorViewModelMap
                    .getInstance()
                    .getViewModel(mkeys.get(position));

            holder.bind(viewModel);
        }catch(Exception e){
            Toast.makeText(mContext,"fail to load device data, adapter can not find the view model", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public int getItemCount() {
        //return 1;
        return this.mkeys.size();
        //this method is called right after constructor, so the itemcount must be set as soon as possible, do not put it in a for statement
    }

}
