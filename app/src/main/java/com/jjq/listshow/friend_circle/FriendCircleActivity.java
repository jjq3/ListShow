package com.jjq.listshow.friend_circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjq.listshow.R;
import com.jjq.listshow.friend_circle.grid.GridViewFriendActivity;
import com.jjq.listshow.friend_circle.recycler.RecyclerViewFriendActivity;

/**
 * Created by jiangjieqiang on 2016/10/17.
 */

public class FriendCircleActivity extends AppCompatActivity{

    private Button button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        button1 = (Button)findViewById(R.id.button5);
        button2 = (Button)findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendCircleActivity.this, RecyclerViewFriendActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendCircleActivity.this, GridViewFriendActivity.class);
                startActivity(intent);
            }
        });

    }
}
