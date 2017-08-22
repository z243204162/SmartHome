package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.support.v7.widget.RecyclerView;

import com.example.zeyupeng.smarthome.databinding.ItemPlanMainBinding;

/**
 * Created by zeyu peng on 2017-07-10.
 */

public class MainActivityPlansViewHolder extends RecyclerView.ViewHolder{

    private ItemPlanMainBinding mBinding;
    public MainActivityPlansViewHolder(ItemPlanMainBinding binding) {
        super(binding.getRoot());
        this.mBinding=binding;
    }

    public void bind(MainActivityPlansViewModel viewModel){
        this.mBinding.setMainActivityPlansViewModel(viewModel);

    }

    public ItemPlanMainBinding getItemPlanMainBinding() {
        return this.mBinding;
    }

}
