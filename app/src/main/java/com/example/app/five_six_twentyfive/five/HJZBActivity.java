package com.example.app.five_six_twentyfive.five;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.five_six_twentyfive.Main;
import com.example.app.five_six_twentyfive.six.SSXSActivity;
import com.example.app.five_six_twentyfive.twentyfive.LKCXActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 第五题：环境指标
 */
public class HJZBActivity extends AppCompatActivity {

    private RelativeLayout v1,v2,v3,v4,v5,v6;
    private Timer time1,time2,time3,time4,time5,time6;
    private TextView textv1;
    //public static int num1,num2,num3,num4,num5,num6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hjzb);
        wd();
        sd();
        gz();
        co2();
        pm();
        dlzt();
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
                    Intent intent = new Intent(HJZBActivity.this, Main.class);// 页面跳转首页
                    HJZBActivity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.ssxs) {
                    Intent intent = new Intent(HJZBActivity.this, SSXSActivity.class);// 该页面跳转实时显示页面
                    HJZBActivity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.lkcx) {
                    Intent intent1 = new Intent(HJZBActivity.this, LKCXActivity.class);// 该页面跳转路口查询页面
                    HJZBActivity.this.startActivity(intent1);
                    finish();
                }

                return true;
            }
        });

        inflater.inflate(R.menu.hjzb_menu,menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show();// 显示控件
    }

    public void wd(){/**温度**/
        time1=new Timer();
        time1.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(1);
            }
        },0,3000);
    }

    public void sd(){/**湿度**/
        time2=new Timer();
        time2.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(2);
            }
        },0,3000);
    }

    public void gz(){/**光照**/
        time3=new Timer();
        time3.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(3);
            }
        },0,3000);
    }
    public void co2(){/**二氧化碳**/
        time4=new Timer();
        time4.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(4);
            }
        },0,3000);
    }

    public void pm(){/**PM2.5**/
        time5=new Timer();
        time5.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(5);
            }
        },0,3000);
    }

    public void dlzt(){/**道路状态：默认显示一号道路**/
        time6=new Timer();
        time6.schedule(new TimerTask() {
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
            v1 = findViewById(R.id.view1);
            v2 = findViewById(R.id.view2);
            v3 = findViewById(R.id.view3);
            v4 = findViewById(R.id.view4);
            v5 = findViewById(R.id.view5);
            v6 = findViewById(R.id.view6);

            switch (msg.what) {
                case 1:
                    if (Main.num1 >= 29){
                        v1.setBackgroundColor(Color.parseColor("#FF0103"));
                    }else if (Main.num1 > 26 && Main.num1 < 29){
                        v1.setBackgroundColor(Color.parseColor("#FFF000"));
                    }else if (Main.num1 <= 26 ){
                        v1.setBackgroundColor(Color.parseColor("#00FF34"));
                    }
                    textv1 = findViewById(R.id.tv1);
                    textv1.setText(String.valueOf(Main.num1));
                    break;
                case 2:
                    if (Main.num2 >= 69){
                        v2.setBackgroundColor(Color.parseColor("#FF0103"));
                    }else if (Main.num2 > 66 && Main.num2 < 69){
                        v2.setBackgroundColor(Color.parseColor("#FFF000"));
                    }else if (Main.num2 <= 64 ){
                        v2.setBackgroundColor(Color.parseColor("#00FF34"));
                    }
                    textv1 = findViewById(R.id.tv2);
                    textv1.setText(String.valueOf(Main.num2));
                    break;
                case 3:
                    if (Main.num3 >= 3000){
                        v3.setBackgroundColor(Color.parseColor("#FF0103"));
                    }else if (Main.num3 > 1000 && Main.num3 < 2000){
                        v3.setBackgroundColor(Color.parseColor("#FFF000"));
                    }else if (Main.num3 <= 1000){
                        v3.setBackgroundColor(Color.parseColor("#00FF34"));
                    }
                    textv1 = findViewById(R.id.tv3);
                    textv1.setText(String.valueOf(Main.num3));
                    break;
                case 4:
                    if (Main.num4 >= 6000){
                        v4.setBackgroundColor(Color.parseColor("#FF0103"));
                    }else if (Main.num4 > 3000 && Main.num4 < 6000){
                        v4.setBackgroundColor(Color.parseColor("#FFF000"));
                    }else if (Main.num4 <= 3000 ){
                        v4.setBackgroundColor(Color.parseColor("#00FF34"));
                    }
                    textv1 = findViewById(R.id.tv4);
                    textv1.setText(String.valueOf(Main.num4));
                    break;
                case 5:
                    if (Main.num5 >= 100){
                        v5.setBackgroundColor(Color.parseColor("#FF0103"));
                    }else if (Main.num5 > 30 && Main.num5 < 100){
                        v5.setBackgroundColor(Color.parseColor("#FFF000"));
                    }else if (Main.num5 <= 30 ){
                        v5.setBackgroundColor(Color.parseColor("#00FF34"));
                    }
                    textv1 = findViewById(R.id.tv5);
                    textv1.setText(String.valueOf(Main.num5));
                    break;
                case 6:
                    if (Main.num6 >0 && Main.num6 <=1){
                        v6.setBackgroundColor(Color.parseColor("#00FF34"));
                    }else if (Main.num6 > 1 && Main.num6 <= 2){
                        v6.setBackgroundColor(Color.parseColor("#98ed1f"));
                    }else if (Main.num6 > 2 && Main.num6 <= 3){
                        v6.setBackgroundColor(Color.parseColor("#ffff01"));
                    }else if (Main.num6 > 3 && Main.num6 <= 4){
                        v6.setBackgroundColor(Color.parseColor("#ff0103"));
                    }else if (Main.num6 > 4 && Main.num6 <= 5){
                        v6.setBackgroundColor(Color.parseColor("#4c060e"));
                    }
                    textv1 = findViewById(R.id.tv6);
                    textv1.setText(String.valueOf(Main.num6));
                    break;
            }

//            if(msg.what == 1){
//                v1 = findViewById(R.id.view1);
//                if (Main.num1 >= 29){
//                    v1.setBackgroundColor(Color.parseColor("#FF0103"));
//                }else if (Main.num1 > 26 && Main.num1 < 29){
//                    v1.setBackgroundColor(Color.parseColor("#FFF000"));
//                }else if (Main.num1 <= 26 ){
//                    v1.setBackgroundColor(Color.parseColor("#00FF34"));
//                }
//                textv1 = findViewById(R.id.tv1);
//                textv1.setText(String.valueOf(Main.num1));
//            }else if (msg.what == 2){
//                v2 = findViewById(R.id.view2);
//                if (Main.num2 >= 69){
//                    v2.setBackgroundColor(Color.parseColor("#FF0103"));
//                }else if (Main.num2 > 66 && Main.num2 < 69){
//                    v2.setBackgroundColor(Color.parseColor("#FFF000"));
//                }else if (Main.num2 <= 64 ){
//                    v2.setBackgroundColor(Color.parseColor("#00FF34"));
//                }
//                textv1 = findViewById(R.id.tv2);
//                textv1.setText(String.valueOf(Main.num2));
//            }else if (msg.what == 3){
//                v3 = findViewById(R.id.view3);
//                if (Main.num3 >= 3000){
//                    v3.setBackgroundColor(Color.parseColor("#FF0103"));
//                }else if (Main.num3 > 1000 && Main.num3 < 2000){
//                    v3.setBackgroundColor(Color.parseColor("#FFF000"));
//                }else if (Main.num3 <= 1000){
//                    v3.setBackgroundColor(Color.parseColor("#00FF34"));
//                }
//                textv1 = findViewById(R.id.tv3);
//                textv1.setText(String.valueOf(Main.num3));
//            }else if (msg.what == 4){
//                v4 = findViewById(R.id.view4);
//                if (Main.num4 >= 6000){
//                    v4.setBackgroundColor(Color.parseColor("#FF0103"));
//                }else if (Main.num4 > 3000 && Main.num4 < 6000){
//                    v4.setBackgroundColor(Color.parseColor("#FFF000"));
//                }else if (Main.num4 <= 3000 ){
//                    v4.setBackgroundColor(Color.parseColor("#00FF34"));
//                }
//                textv1 = findViewById(R.id.tv4);
//                textv1.setText(String.valueOf(Main.num4));
//            }else if (msg.what == 5){
//                v5 = findViewById(R.id.view5);
//                if (Main.num5 >= 100){
//                    v5.setBackgroundColor(Color.parseColor("#FF0103"));
//                }else if (Main.num5 > 30 && Main.num5 < 100){
//                    v5.setBackgroundColor(Color.parseColor("#FFF000"));
//                }else if (Main.num5 <= 30 ){
//                    v5.setBackgroundColor(Color.parseColor("#00FF34"));
//                }
//                textv1 = findViewById(R.id.tv5);
//                textv1.setText(String.valueOf(Main.num5));
//            }else if (msg.what == 6){
//                v6 = findViewById(R.id.view6);
//                if (Main.num6 >0 && Main.num6 <=1){
//                    v6.setBackgroundColor(Color.parseColor("#00FF34"));
//                }else if (Main.num6 > 1 && Main.num6 <= 2){
//                    v6.setBackgroundColor(Color.parseColor("#98ed1f"));
//                }else if (Main.num6 > 2 && Main.num6 <= 3){
//                    v6.setBackgroundColor(Color.parseColor("#ffff01"));
//                }else if (Main.num6 > 3 && Main.num6 <= 4){
//                    v6.setBackgroundColor(Color.parseColor("#ff0103"));
//                }else if (Main.num6 > 4 && Main.num6 <= 5){
//                    v6.setBackgroundColor(Color.parseColor("#4c060e"));
//                }
//                textv1 = findViewById(R.id.tv6);
//                textv1.setText(String.valueOf(Main.num6));
//            }

            super.handleMessage(msg);
        }
    };


}
