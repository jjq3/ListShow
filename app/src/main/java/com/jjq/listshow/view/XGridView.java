package com.jjq.listshow.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by jiangjieqiang on 2017/7/24.
 */

public class XGridView extends GridView {

    public XGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public XGridView(Context context) {
        super(context);
    }
    public XGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}