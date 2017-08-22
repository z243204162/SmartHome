package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.support.v7.widget.RecyclerView;

import com.example.zeyupeng.smarthome.databinding.ItemRoomHubRoomsBinding;


/**
 * Created by zeyu peng on 2017-07-03.
 */

public class HubRoomsActivityRoomViewHolder  extends RecyclerView.ViewHolder {

    private ItemRoomHubRoomsBinding mBinding;
    public HubRoomsActivityRoomViewHolder(ItemRoomHubRoomsBinding binding) {
            super(binding.getRoot());
            this.mBinding=binding;
            }

    public void bind(HubRoomsActivityRoomViewModel viewModel){
            this.mBinding.setHubRoomActivityRoomViewModel(viewModel);

            }

    public ItemRoomHubRoomsBinding getItemRoomHubRoomsBinding() {
            return this.mBinding;
            }

}
