package com.jjq.listshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjq.listshow.friend_circle.FriendCircleActivity;
import com.jjq.listshow.taobao_change.TaobaoActivity;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4, button5, button6, button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button8);
        button6 = (Button)findViewById(R.id.button9);
        button7 = (Button)findViewById(R.id.button10);

        //朋友圈列表
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FriendCircleActivity.class);
                startActivity(intent);
            }
        });

        //仿淘宝商品页面item样式切换
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaobaoActivity.class);
                startActivity(intent);
            }
        });

    }
}
