package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.support.v7.widget.RecyclerView;

import com.example.zeyupeng.smarthome.databinding.ItemRoomMainBinding;


/**
 * Created by zeyu peng on 2017-06-19.
 */

public class MainActivityRoomViewHolder extends RecyclerView.ViewHolder {

    private ItemRoomMainBinding mItemRoomMainBinding;
    public MainActivityRoomViewHolder(ItemRoomMainBinding itemRoomMainBinding) {
        super(itemRoomMainBinding.getRoot());
        this.mItemRoomMainBinding=itemRoomMainBinding;
    }

    public void bind(MainActivityRoomViewModel mainActivityRoomViewModel){
        this.mItemRoomMainBinding.setRecyclerViewItemViewModel(mainActivityRoomViewModel);
        
    }

    public ItemRoomMainBinding getItemRoomMainBinding() {
        return this.mItemRoomMainBinding;
    }

}
