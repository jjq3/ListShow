package com.jjq.listshow.friend_circle.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jjq.listshow.R;
import com.jjq.listshow.friend_circle.model.SaidPictureVO;
import com.jjq.listshow.friend_circle.model.SaidVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjieqiang on 2016/10/26.
 */

public class GridViewFriendActivity extends AppCompatActivity{

    private ListView listView;

    List<SaidVO> saidVOList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_friend);
        listView = (ListView)findViewById(R.id.gridview_friend_listview);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            List<SaidPictureVO> list = new ArrayList<>();
            SaidVO saidVO = new SaidVO();
            for (int j = 0; j < i; j++) {
                list.add(new SaidPictureVO(R.mipmap.ic_launcher, "picture" + j));
            }
            saidVO.setSaidPictureVOs(list);
            saidVO.setContent("这是一条很酷的说说");
            saidVOList.add(saidVO);
        }
    }
}
