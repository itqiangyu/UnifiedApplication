package com.example.app.five_six_twentyfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.five_six_twentyfive.five.HJZBActivity;
import com.example.app.five_six_twentyfive.six.SSXSActivity;
import com.example.app.five_six_twentyfive.twentyfive.LKCXActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {

    public Timer time1;
    public static int num1, num2, num3, num4, num5, num6, num7, num8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DataRefresh();
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

                if (item.getItemId() == R.id.hjzb) {
                    Intent intent = new Intent(Main.this, HJZBActivity.class);// 该页面跳转环境指标页面
                    Main.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.ssxs) {
                    Intent intent = new Intent(Main.this, SSXSActivity.class);// 该页面跳转实时显示页面
                    Main.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.lkcx) {
                    Intent intent1 = new Intent(Main.this, LKCXActivity.class);// 该页面跳转路口查询页面
                    Main.this.startActivity(intent1);
                    finish();
                }

                return true;
            }
        });

        inflater.inflate(R.menu.main_menu, menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show();// 显示控件
    }

    public void DataRefresh() {
        time1 = new Timer();
        time1.schedule(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                num1 = random.nextInt(9) + 22;//温度
                num2 = random.nextInt(10) + 60;//湿度
                num3 = random.nextInt(4000);//光照
                num4 = random.nextInt(6500);//CO2
                num5 = random.nextInt(110);//PM2.5
                num6 = random.nextInt(4) + 1;//1号道路
                num7 = random.nextInt(4) + 1;//2号道路
                num8 = random.nextInt(4) + 1;//3号道路
            }
        }, 0, 3000);
    }
}
