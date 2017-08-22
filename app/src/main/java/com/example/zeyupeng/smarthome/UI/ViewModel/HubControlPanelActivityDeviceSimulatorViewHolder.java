package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.support.v7.widget.RecyclerView;

import com.example.zeyupeng.smarthome.databinding.ItemDeviceSimulatorActivityHubRoomControlPanelBinding;


/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubControlPanelActivityDeviceSimulatorViewHolder extends RecyclerView.ViewHolder {


    private ItemDeviceSimulatorActivityHubRoomControlPanelBinding mBinding;
    public HubControlPanelActivityDeviceSimulatorViewHolder(ItemDeviceSimulatorActivityHubRoomControlPanelBinding binding ) {
        super(binding.getRoot());
        this.mBinding=binding;
    }

    public void bind(HubControlPanelActivityDeviceSimulatorViewModel viewModel){
        this.mBinding.setHubControlPanelActivityDeviceSimulatorViewModel(viewModel);

    }

    public ItemDeviceSimulatorActivityHubRoomControlPanelBinding getBinding() {
        return mBinding;
    }

}
