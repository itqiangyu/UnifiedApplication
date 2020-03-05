package com.example.app.thirty_seven;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.five_six_twentyfive.Main;
import com.example.app.five_six_twentyfive.five.HJZBActivity;
import com.example.app.five_six_twentyfive.six.SSXSActivity;
import com.example.app.five_six_twentyfive.twentyfive.LKCXActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


/**
 * 第三十七题
 */

public class ewm_activity extends AppCompatActivity {


    private LinearLayout x, x1, y, y1;
    private RelativeLayout c;
    private ConstraintLayout quanping;


    private TextView bianhao;
    private TextView jine;
    private Button button;
    private EditText editText;

    public EditText sleep;
    private ImageView imageView, ewm2;
    private Bitmap mBitmap;


    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewm_activity);
        //初始化
        ZXingLibrary.initDisplayOpinion(this);

        spinner = findViewById(R.id.spinner);


        //spinner列表
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.carnum, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        x = findViewById(R.id.linearLayout_zhifu);
        y = findViewById(R.id.linearLayout_zhifu2);
        x1 = findViewById(R.id.lineneiron);
        y1 = findViewById(R.id.lineneiron2);
        c = findViewById(R.id.ewmtp);
        quanping = findViewById(R.id.quanpingewm);

        sleep = findViewById(R.id.shuimian);
        jine = findViewById(R.id.jine);
        bianhao = findViewById(R.id.bianhao);
        button = findViewById(R.id.button1);
        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.ewm2);
        ewm2 = findViewById(R.id.ewm3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String textContent = editText.getText().toString();
                final String textContent1 = sleep.getText().toString();
                final int w = Integer.parseInt(textContent1);
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(ewm_activity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    x1.setVisibility(View.GONE);
                    y1.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            button.post(new TimerTask() {
                                @Override
                                public void run() {
                                    mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                                    imageView.setImageBitmap(mBitmap);
                                    ewm2.setImageBitmap(mBitmap);
                                    //System.out.println("刷新二维码了");
                                }
                            });
                        }
                    };
                    timer.schedule(task, 0, w * 1000);
                }
            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Toast.makeText(ewm_activity.this, "wwww", Toast.LENGTH_SHORT).show();
                bianhao.setText("车辆编号：" + spinner.getSelectedItem().toString());
                jine.setText("付费金额：" + editText.getText().toString());
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x1.setVisibility(View.GONE);
                x.setVisibility(View.GONE);
                y.setVisibility(View.GONE);
                y1.setVisibility(View.GONE);
                c.setVisibility(View.GONE);
                if (quanping.getVisibility() == View.GONE) {
                    quanping.setVisibility(View.VISIBLE);
                }
            }
        });
        quanping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quanping.getVisibility() == View.VISIBLE) {
                    quanping.setVisibility(View.GONE);
                    y.setVisibility(View.VISIBLE);
                    y1.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                }
            }
        });
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
                    Intent intent = new Intent(ewm_activity.this, Main.class);// 页面跳转首页
                    ewm_activity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.hjzb) {
                    Intent intent = new Intent(ewm_activity.this, HJZBActivity.class);// 该页面跳转环境指标页面
                    ewm_activity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.ssxs) {
                    Intent intent = new Intent(ewm_activity.this, SSXSActivity.class);// 该页面跳转实时显示页面
                    ewm_activity.this.startActivity(intent);
                    finish();
                } else if (item.getItemId() == R.id.lkcx) {
                    Intent intent1 = new Intent(ewm_activity.this, LKCXActivity.class);// 该页面跳转路口查询页面
                    ewm_activity.this.startActivity(intent1);
                    finish();
                } else if (item.getItemId() == R.id.ewm) {
                    Intent intent1 = new Intent(ewm_activity.this, ewm_activity.class);// 该页面跳转路口查询页面
                    ewm_activity.this.startActivity(intent1);
                    finish();
                }

                return true;
            }
        });
        inflater.inflate(R.menu.ewmzf_menu, menubtn.getMenu());/** 加载menu文件 **/
        menubtn.show();// 显示控件
    }

}

