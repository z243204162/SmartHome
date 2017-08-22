package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zeyupeng.smarthome.databinding.ItemRoomHubRoomsBinding;


/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubRoomsActivityRoomAdapter extends RecyclerView.Adapter<HubRoomsActivityRoomViewHolder> {

    private Context mContext;
    private String[] mkeys;
    private LayoutInflater mLayoutInflater;

    public HubRoomsActivityRoomAdapter(Context context) {
        mContext = context;

    }

    @Override
    public HubRoomsActivityRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mLayoutInflater==null){
            mLayoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemRoomHubRoomsBinding binding = ItemRoomHubRoomsBinding.inflate(mLayoutInflater,parent,false);
        return new HubRoomsActivityRoomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(HubRoomsActivityRoomViewHolder holder, int position) {
        try{
            mkeys=HubRoomsActivityRoomViewModelMap
                    .getInstance()
                    .getViewModelMap()
                    .keySet()
                    .toArray(
                            new String[HubRoomsActivityRoomViewModelMap
                                    .getInstance()
                                    .getViewModelMap()
                                    .size()]);
            HubRoomsActivityRoomViewModel viewModel=HubRoomsActivityRoomViewModelMap
                    .getInstance()
                    .getViewModel(mkeys[position]);
            holder.bind(viewModel);
        }catch(Exception e){
            Toast.makeText(mContext,"fail to load room data, adapter can not find the view model", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public int getItemCount() {
        return HubRoomsActivityRoomViewModelMap
                .getInstance()
                .getViewModelMap()
                .size();
    }

}
