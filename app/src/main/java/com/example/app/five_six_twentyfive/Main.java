package com.example.app.five_six_twentyfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.five_six_twentyfive.five.HJZBActivity;
import com.example.app.five_six_twentyfive.six.SSXSActivity;
import com.example.app.five_six_twentyfive.twentyfive.LKCXActivity;
import com.example.app.retrofit.IDataInformation;
import com.example.app.retrofit.pojo.DataInformation;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {

    public Timer time1;
    // 温度、湿度、光照、二氧化碳、PM2.5、道路状态1、道路状态2、道路状态3
    public static Integer wendu, shidu, guangzhao, co, pm, num1, num2, num3;

    /**
     * 创建Register实例
     */
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://47.106.99.53/application/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    /**
     * 代理模式：创建 接收数据接口 实例
     */
    private IDataInformation request = retrofit.create(IDataInformation.class);

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

                Call<DataInformation> resultCall = request.receiveData();
                resultCall.enqueue(new Callback<DataInformation>() {
                    /**
                     * 请求成功回调函数
                     * @param call
                     * @param response
                     */
                    @Override
                    public void onResponse(Call<DataInformation> call, Response<DataInformation> response) {
                        DataInformation body = response.body();

                        if (body == null) {
                            Toast.makeText(Main.this, "请求异常，加载数据失败。", Toast.LENGTH_LONG).show();
                            return;
                        }

//                        Toast.makeText(Main.this, "加载数据成功，请稍等。", Toast.LENGTH_SHORT).show();
                        // 从服务器获取数据并设置到本地
                        wendu = body.getWendu();
                        shidu = body.getShidu();
                        guangzhao = body.getGuangzhao();
                        co = body.getCo();
                        pm = body.getPm();
                        num1 = body.getNum1();
                        num2 = body.getNum2();
                        num3 = body.getNum3();
                    }

                    /**
                     * 请求失败的回调函数
                     * @param call
                     * @param throwable
                     */
                    @Override
                    public void onFailure(Call<DataInformation> call, Throwable t) {
                        Toast.makeText(Main.this, "网络错误，加载数据失败。", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, 0, 3000);
    }
}
