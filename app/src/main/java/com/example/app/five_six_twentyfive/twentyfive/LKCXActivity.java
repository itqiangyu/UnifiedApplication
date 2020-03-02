package com.example.app.five_six_twentyfive.twentyfive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.five_six_twentyfive.Main;
import com.example.app.five_six_twentyfive.five.HJZBActivity;
import com.example.app.five_six_twentyfive.six.SSXSActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 第25题：路况查询
 */
public class LKCXActivity extends AppCompatActivity {

    private TextView ttv1,ttv2,ttv3,ttv4,ttv5,ttv6;
    private Timer time,time2,time3;
    private View v1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lkcx);

        PM();
        WD();
        SD();

        dl1();
        dl2();
        dl3();

    }

    public  void  menu_btn(View view){
        PopupMenu menubtn = new PopupMenu(this,view);
        // 这里的view代表popupMenu需要依附的view
        // 获取布局文件
        MenuInflater inflater = menubtn.getMenuInflater();
        // 实例化menu布局文件
        menubtn.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            /** 通过setOnMenuItemClickListener接口调用已有的menu布局 **/
            @Override
            public boolean onMenuItemClick(MenuItem item) {/** 控制item的点击事件 **/

                if (item.getItemId() == R.id.menu) {
                    Intent intent = new Intent(LKCXActivity.this, Main.class);// 页面跳转首页
                    LKCXActivity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.hjzb) {
                    Intent intent = new Intent(LKCXActivity.this, HJZBActivity.class);// 该页面跳转环境指标页面
                    LKCXActivity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.ssxs) {
                    Intent intent = new Intent(LKCXActivity.this, SSXSActivity.class);// 该页面跳转实时显示页面
                    LKCXActivity.this.startActivity(intent);
                    finish();
                }
                return true;
            }
        });

        inflater.inflate(R.menu.lkcx_menu,menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show();// 显示控件
    }

    public void PM(){/**PM2.5**/
        time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(1);
            }
        },0,3000);
    }

    public void WD(){/**温度**/
        time2=new Timer();
        time2.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(2);
            }
        },0,3000);
    }

    public void SD(){/**湿度**/
        time3=new Timer();
        time3.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(3);
            }
        },0,3000);
    }

    /**
     * 一号道路
     */
    public void dl1(){/**道路状态：显示一号道路**/
        time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(4);
            }
        },0,3000);
    }

    /**
     * 二号道路
     */
    public void dl2(){/**道路状态：显示二号道路**/
        time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(5);
            }
        },0,3000);
    }

    /**
     * 三号道路
     */
    public void dl3(){/**道路状态：显示三号道路**/
        time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(6);
            }
        },0,3000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            ttv4 = findViewById(R.id.tab1);
            ttv5 = findViewById(R.id.tab2);
            ttv6 = findViewById(R.id.tab3);
            switch (msg.what) {
                case 1:
                    ttv1 = findViewById(R.id.textView5);
                    ttv1.setText(String.valueOf(Main.num5));
                    break;
                case 2:
                    ttv2 = findViewById(R.id.textView6);
                    ttv2.setText(String.valueOf(Main.num1));
                    break;
                case 3:
                    ttv3 = findViewById(R.id.textView7);
                    ttv3.setText(String.valueOf(Main.num2));
                    break;
                case 4:
                    v1 = findViewById(R.id.vv1);
                    if (Main.num6 >0 && Main.num6 <=1){
                        v1.setBackgroundColor(Color.parseColor("#00FF34"));
                        ttv4.setText("1号道路:通畅");
                    }else if (Main.num6 > 1 && Main.num6 <= 2){
                        v1.setBackgroundColor(Color.parseColor("#98ed1f"));
                        ttv4.setText("1号道路:较通畅");
                    }else if (Main.num6 > 2 && Main.num6 <= 3){
                        v1.setBackgroundColor(Color.parseColor("#ffff01"));
                        ttv4.setText("1号道路:拥挤");
                    }else if (Main.num6 > 3 && Main.num6 <= 4){
                        v1.setBackgroundColor(Color.parseColor("#ff0103"));
                        ttv4.setText("1号道路:堵塞");
                    }else if (Main.num6 > 4 && Main.num6 <= 5){
                        v1.setBackgroundColor(Color.parseColor("#4c060e"));
                        ttv4.setText("1号道路:爆表");
                    }
                    break;
                case 5:
                    v1 = findViewById(R.id.vv2);
                    if (Main.num7 >0 && Main.num7 <=1){
                        v1.setBackgroundColor(Color.parseColor("#00FF34"));
                        ttv5.setText("2号道路:通畅");
                    }else if (Main.num7 > 1 && Main.num7 <= 2){
                        v1.setBackgroundColor(Color.parseColor("#98ed1f"));
                        ttv5.setText("2号道路:较通畅");
                    }else if (Main.num7 > 2 && Main.num7 <= 3){
                        v1.setBackgroundColor(Color.parseColor("#ffff01"));
                        ttv5.setText("2号道路:拥挤");
                    }else if (Main.num7 > 3 && Main.num7 <= 4){
                        v1.setBackgroundColor(Color.parseColor("#ff0103"));
                        ttv5.setText("2号道路:堵塞");
                    }else if (Main.num7 > 4 && Main.num7 <= 5){
                        v1.setBackgroundColor(Color.parseColor("#4c060e"));
                        ttv5.setText("2号道路:爆表");
                    }
                    break;
                case 6:
                    v1 = findViewById(R.id.vv3);
                    if (Main.num8 >0 && Main.num8 <=1){
                        v1.setBackgroundColor(Color.parseColor("#00FF34"));
                        ttv6.setText("3号道路:通畅");
                    }else if (Main.num8 > 1 && Main.num8 <= 2){
                        v1.setBackgroundColor(Color.parseColor("#98ed1f"));
                        ttv6.setText("3号道路:较通畅");
                    }else if (Main.num8 > 2 && Main.num8 <= 3){
                        v1.setBackgroundColor(Color.parseColor("#ffff01"));
                        ttv6.setText("3号道路:拥挤");
                    }else if (Main.num8 > 3 && Main.num8 <= 4){
                        v1.setBackgroundColor(Color.parseColor("#ff0103"));
                        ttv6.setText("3号道路:堵塞");
                    }else if (Main.num8 > 4 && Main.num8 <= 5){
                        v1.setBackgroundColor(Color.parseColor("#4c060e"));
                        ttv6.setText("3号道路:爆表");
                    }
                    break;
            }

            super.handleMessage(msg);
        }
    };

}
