package com.jjq.listshow.choose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.jjq.listshow.R;

/**
 * 单选RecyclerView
 * Created by jiangjieqiang on 2017/7/27.
 */

public class SingleChooseActivity extends AppCompatActivity{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlechoose);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_singlechoose);

    }
}
