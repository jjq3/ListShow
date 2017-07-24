package com.jjq.listshow.friend_circle.grid;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jjq.listshow.friend_circle.model.SaidPictureVO;
import com.jjq.listshow.utils.ScreenUtils;
import com.jjq.listshow.view.XGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjieqiang on 2017/7/24.
 */

public class FriendGridView extends XGridView implements AdapterView.OnItemClickListener {

    private static final int GRIDVIEW_PADDING = 10;

    private List<SaidPictureVO> saidPictureVOList = new ArrayList<>();
    private Context context;

    private PhotoAdapter photoAdapter;
    private int mColumnNum;

    public FriendGridView(Context context) {
        super(context);
        this.context = context;
    }

    public FriendGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public FriendGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPhotoAdapter(List<SaidPictureVO> saidPictureVOs) {
        saidPictureVOList.clear();
        saidPictureVOList.addAll(saidPictureVOs);
        int count = saidPictureVOList.size();
        //当图片数量为1，2，4的时候，gridview显示两列
        if (count == 1 || count == 2 || count == 4) {
            mColumnNum = 2;
            setNumColumns(2);
        } else {
            mColumnNum = 3;
            setNumColumns(3);
        }
        int width = calculateColumnWidth();
        setColumnWidth(width);
        photoAdapter = new PhotoAdapter(context, saidPictureVOList, width);
        this.setAdapter(photoAdapter);
        this.setOnItemClickListener(this);
        photoAdapter.notifyDataSetChanged();
    }

    private int calculateColumnWidth() {
        int width = ScreenUtils.getScreenWidth((Activity) getContext());
        if (mColumnNum == 2) {
            width = (width - ScreenUtils.dp2px(getContext(), GRIDVIEW_PADDING * 2 + GRIDVIEW_PADDING)) / mColumnNum;
        } else if (mColumnNum == 3) {
            width = (width - ScreenUtils.dp2px(getContext(), GRIDVIEW_PADDING * 2 + GRIDVIEW_PADDING * 2)) / mColumnNum;
        }
        return width;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
    }
}
