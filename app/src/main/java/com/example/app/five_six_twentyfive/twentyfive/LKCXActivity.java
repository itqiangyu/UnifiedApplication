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
import com.example.app.thirty_seven.ewm_activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 第25题：路况查询
 */
public class LKCXActivity extends AppCompatActivity {

    private TextView ttv1, ttv2, ttv3, ttv4, ttv5, ttv6;
    private Timer time, time2, time3;
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

                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.menu:
                        intent = new Intent(LKCXActivity.this, Main.class);// 页面跳转首页
                        break;
                    case R.id.hjzb:
                        intent = new Intent(LKCXActivity.this, HJZBActivity.class);// 该页面跳转环境指标页面
                        break;
                    case R.id.ssxs:
                        intent = new Intent(LKCXActivity.this, SSXSActivity.class);// 该页面跳转实时显示页面
                        break;
                    case R.id.ewm:
                        intent = new Intent(LKCXActivity.this, ewm_activity.class);// 该页面跳转二维码支付页面
                        break;
                }
                if (intent != null) {
                    LKCXActivity.this.startActivity(intent);
                    finish();
                }

                return true;
            }
        });

        inflater.inflate(R.menu.lkcx_menu, menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show();// 显示控件
    }

    public void PM() {/**PM2.5**/
        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(1);
            }
        }, 0, 3000);
    }

    public void WD() {/**温度**/
        time2 = new Timer();
        time2.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(2);
            }
        }, 0, 3000);
    }

    public void SD() {/**湿度**/
        time3 = new Timer();
        time3.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(3);
            }
        }, 0, 3000);
    }

    /**
     * 一号道路
     */
    public void dl1() {/**道路状态：显示一号道路**/
        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(4);
            }
        }, 0, 3000);
    }

    /**
     * 二号道路
     */
    public void dl2() {/**道路状态：显示二号道路**/
        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(5);
            }
        }, 0, 3000);
    }

    /**
     * 三号道路
     */
    public void dl3() {/**道路状态：显示三号道路**/
        time = new Timer();
        time.schedule(new TimerTask() {
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
            ttv4 = findViewById(R.id.tab1);
            ttv5 = findViewById(R.id.tab2);
            ttv6 = findViewById(R.id.tab3);
            switch (msg.what) {
                case 1:
                    ttv1 = findViewById(R.id.textView5);
                    ttv1.setText(String.valueOf(Main.pm));
                    break;
                case 2:
                    ttv2 = findViewById(R.id.textView6);
                    ttv2.setText(String.valueOf(Main.wendu));
                    break;
                case 3:
                    ttv3 = findViewById(R.id.textView7);
                    ttv3.setText(String.valueOf(Main.shidu));
                    break;
                case 4:
                    v1 = findViewById(R.id.vv1);
                    scopeNum(v1, ttv4, Main.num1, 1, 2, 3, 4, 5);
                    break;
                case 5:
                    v1 = findViewById(R.id.vv2);
                    scopeNum(v1, ttv5, Main.num2, 1, 2, 3, 4, 5);
                    break;
                case 6:
                    v1 = findViewById(R.id.vv3);
                    scopeNum(v1, ttv6, Main.num3, 1, 2, 3, 4, 5);
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
    private void scopeNum(View v, TextView t, Integer num, Integer num1, Integer num2, Integer num3, Integer num4, Integer num5) {
        if (num > num5) {
            return;
        }
        if (num > num4) {
            v.setBackgroundColor(Color.parseColor("#4c060e"));
            t.setText("2号道路:爆表");
            return;
        }
        if (num > num3) {
            v.setBackgroundColor(Color.parseColor("#ff0103"));
            t.setText("2号道路:堵塞");
            return;
        }
        if (num > num2) {
            v.setBackgroundColor(Color.parseColor("#ffff01"));
            t.setText("2号道路:拥挤");
            return;
        }
        if (num > num1) {
            v.setBackgroundColor(Color.parseColor("#98ed1f"));
            t.setText("2号道路:较通畅");
            return;
        }
        // 0 - 1 之间
        v.setBackgroundColor(Color.parseColor("#00FF34"));
        t.setText("2号道路:通畅");
    }

}
