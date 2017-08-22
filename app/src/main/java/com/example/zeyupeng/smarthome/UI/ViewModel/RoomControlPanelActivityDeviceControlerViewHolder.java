package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zeyupeng.smarthome.databinding.ItemDeviceControlerActivityRoomControlPanelBinding;


/**
 * Created by zeyu peng on 2017-06-21.
 */

public class RoomControlPanelActivityDeviceControlerViewHolder extends RecyclerView.ViewHolder {


    private ItemDeviceControlerActivityRoomControlPanelBinding mBinding;
    public RoomControlPanelActivityDeviceControlerViewHolder(ItemDeviceControlerActivityRoomControlPanelBinding binding ) {
        super(binding.getRoot());
        this.mBinding=binding;
    }

    public void bind(RoomControlPanelActivityDeviceControlerViewModel viewModel){
        this.mBinding.setRoomControlPanelActivityDeviceControlerViewModel(viewModel);

    }

    public ItemDeviceControlerActivityRoomControlPanelBinding getBinding() {
        return mBinding;
    }

}
