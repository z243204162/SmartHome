package com.example.zeyupeng.smarthome.View.CustomView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zeyu peng on 2017-08-14.
 */

public class MyItemDecorator extends RecyclerView.ItemDecoration {

    int space;
    boolean isHorizontalLayout;
    public MyItemDecorator(int space) {
        this.space = space;
    }

    public MyItemDecorator(int space, boolean isHorizontalLayout) {
        this.space = space;
        this.isHorizontalLayout = isHorizontalLayout;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(isHorizontalLayout)
        {
            outRect.bottom=space;
            outRect.right=space;
            outRect.left=space;
            outRect.top=space;

        } else {
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.top = space;
            else
                outRect.top = 0;

        }


    }
}