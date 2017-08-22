package com.example.zeyupeng.smarthome.UI.ViewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zeyupeng.smarthome.databinding.ItemPlanMainBinding;

/**
 * Created by zeyu peng on 2017-07-10.
 */

public class MainActivityPlansAdapter  extends RecyclerView.Adapter<MainActivityPlansViewHolder> {
    private Context mContext;
    private String[] mkeys;
    private LayoutInflater mLayoutInflater;

    public MainActivityPlansAdapter(Context context) {
        mContext = context;
    }


    @Override
    public MainActivityPlansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mLayoutInflater==null){
            mLayoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemPlanMainBinding binding = ItemPlanMainBinding.inflate(mLayoutInflater,parent,false);
        return new MainActivityPlansViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MainActivityPlansViewHolder holder, int position) {
        try{
            mkeys=MainActivityPlansViewModelMap
                    .getInstance()
                    .getViewModelMap()
                    .keySet()
                    .toArray(
                            new String[MainActivityPlansViewModelMap
                                    .getInstance()
                                    .getViewModelMap()
                                    .size()]);
            MainActivityPlansViewModel viewModel=MainActivityPlansViewModelMap
                    .getInstance()
                    .getViewModel(mkeys[position]);
            holder.bind(viewModel);
        }catch(Exception e){
            Toast.makeText(mContext,"fail to load plan data, adapter can not find the view model", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return MainActivityPlansViewModelMap
                .getInstance()
                .getViewModelMap()
                .size();
    }
}
