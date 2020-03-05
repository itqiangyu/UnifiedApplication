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
import com.example.app.thirty_seven.ewm_activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 第五题：环境指标
 */
public class HJZBActivity extends AppCompatActivity {

    private RelativeLayout v1, v2, v3, v4, v5, v6;
    private Timer time1, time2, time3, time4, time5, time6;
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

    public void menu_btn(View view) {
        PopupMenu menubtn = new PopupMenu(this, view);
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
                }else if (item.getItemId() == R.id.ewm) {
                    Intent intent1 = new Intent(HJZBActivity.this, ewm_activity.class);// 该页面跳转路口查询页面
                    HJZBActivity.this.startActivity(intent1);
                    finish();
                }

                return true;
            }
        });

        inflater.inflate(R.menu.hjzb_menu, menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show();// 显示控件
    }

    public void wd() {/**温度**/
        time1 = new Timer();
        time1.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(1);
            }
        }, 0, 3000);
    }

    public void sd() {/**湿度**/
        time2 = new Timer();
        time2.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(2);
            }
        }, 0, 3000);
    }

    public void gz() {/**光照**/
        time3 = new Timer();
        time3.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(3);
            }
        }, 0, 3000);
    }

    public void co2() {/**二氧化碳**/
        time4 = new Timer();
        time4.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(4);
            }
        }, 0, 3000);
    }

    public void pm() {/**PM2.5**/
        time5 = new Timer();
        time5.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(5);
            }
        }, 0, 3000);
    }

    public void dlzt() {/**道路状态：默认显示一号道路**/
        time6 = new Timer();
        time6.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(6);
            }
        }, 0, 3000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
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
                    scopeNum(v1, Main.wendu, 29, 26);
                    textv1 = findViewById(R.id.tv1);
                    textv1.setText(String.valueOf(Main.wendu));
                    break;
                case 2:
                    scopeNum(v2, Main.shidu, 69, 64);
                    textv1 = findViewById(R.id.tv2);
                    textv1.setText(String.valueOf(Main.shidu));
                    break;
                case 3:
                    scopeNum(v3, Main.guangzhao, 3000, 1000);
                    textv1 = findViewById(R.id.tv3);
                    textv1.setText(String.valueOf(Main.guangzhao));
                    break;
                case 4:
                    scopeNum(v4, Main.co, 6000, 3000);
                    textv1 = findViewById(R.id.tv4);
                    textv1.setText(String.valueOf(Main.co));
                    break;
                case 5:
                    scopeNum(v5, Main.pm, 100, 30);
                    textv1 = findViewById(R.id.tv5);
                    textv1.setText(String.valueOf(Main.pm));
                    break;
                case 6:
                    dlztScope(v6, Main.num1, 1, 2, 3, 4, 5);
                    textv1 = findViewById(R.id.tv6);
                    textv1.setText(String.valueOf(Main.num1));
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 判断数值的范围
     *
     * @param v      判断的 RelativeLayout（）
     * @param num    判断的数值
     * @param maxNum 最大
     * @param minNum 最小
     */
    private void scopeNum(RelativeLayout v, Integer num, Integer maxNum, Integer minNum) {
        // 爆表
        if (num >= maxNum) {
            v.setBackgroundColor(Color.parseColor("#FF0103"));
            return;
        }
        // 最低
        if (num <= minNum) {
            v.setBackgroundColor(Color.parseColor("#00FF34"));
            return;
        }
        // 之间
        v.setBackgroundColor(Color.parseColor("#FFF000"));
    }

    /**
     * 道路状态的比较
     *
     * @param v
     * @param num
     * @param maxNum
     * @param minNum
     */
    private void dlztScope(RelativeLayout v, Integer num, Integer num1, Integer num2, Integer num3, Integer num4, Integer num5) {
        if (num > num5) {
            return;
        }
        if (num > num4) {
            v6.setBackgroundColor(Color.parseColor("#4c060e"));
            return;
        }
        if (num > num3) {
            v6.setBackgroundColor(Color.parseColor("#ff0103"));
            return;
        }
        if (num > num2) {
            v6.setBackgroundColor(Color.parseColor("#ffff01"));
            return;
        }
        if (num > num1) {
            v6.setBackgroundColor(Color.parseColor("#98ed1f"));
            return;
        }
        // 0 - 1 之间
        v6.setBackgroundColor(Color.parseColor("#00FF34"));
    }


}
