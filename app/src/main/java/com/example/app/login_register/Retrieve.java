package com.example.app.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.five_six_twentyfive.Main;
import com.example.app.retrofit.ILogin;
import com.example.app.retrofit.IRetrieve;
import com.example.app.retrofit.pojo.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 找回密码
 */
public class Retrieve extends AppCompatActivity {

    private TextView tv_main_title;//标题
    private TextView tv_back, tv_register, tv_login;//返回键,显示的注册
    private Button btn_retrieve;// 找回密码按钮
    private String phone, newPassword;//获取手机号，新设置的密码
    private EditText et_retrieve_phone, et_retrieve_password;//编辑框

    /**
     * 创建Register实例
     */
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://47.106.99.53/application/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    /**
     * 代理模式：创建 找回密码接口 的实例
     */
    private IRetrieve request = retrofit.create(IRetrieve.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        init();
    }

    //获取界面控件
    private void init() {
        //从main_title_bar中获取的id
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("找回密码");
        tv_back = findViewById(R.id.tv_back);
        //从activity_login.xml中获取的
        tv_register = findViewById(R.id.tv_register);
        tv_login = findViewById(R.id.tv_login);

        // 找回密码按钮
        btn_retrieve = findViewById(R.id.btn_retrieve);
        //手机号编辑框
        et_retrieve_phone = findViewById(R.id.et_retrieve_phone);
        //密码编辑框
        et_retrieve_password = findViewById(R.id.et_retrieve_password);

        //返回键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册界面销毁
                Retrieve.this.finish();
            }
        });
        //立即注册控件的点击事件
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到注册界面，并实现注册功能
                Intent intent = new Intent(Retrieve.this, Register.class);
                startActivityForResult(intent, 1);
            }
        });
        //找回密码控件的点击事件
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登录界面
                Intent intent = new Intent(Retrieve.this, Login.class);
                startActivityForResult(intent, 1);
            }
        });
        //“找回密码”按钮的点击事件
        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码 getText().toString().trim();
                phone = et_retrieve_phone.getText().toString().trim();
                newPassword = et_retrieve_password.getText().toString().trim();

                System.out.println(phone + newPassword);

                // TextUtils.isEmpty
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(Retrieve.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    et_retrieve_phone.setError("手机号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(newPassword)) {
                    Toast.makeText(Retrieve.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    et_retrieve_password.setError("新密码不能为空！");
                    return;
                }

                Call<Result> resultCall = request.retrievePassword(phone, newPassword);
                resultCall.enqueue(new Callback<Result>() {
                    /**
                     * 请求成功回调函数
                     * @param call
                     * @param response
                     */
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        // 解析完服务器返回的json，转化为 pojo
                        Result body = response.body();
                        if (body == null) {
                            Toast.makeText(Retrieve.this, "找回密码异常，请重试。", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (body.getStatus() != 200) {
                            // 找回密码失败
                            Toast.makeText(Retrieve.this, body.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // 否则找回密码成功
                        Toast.makeText(Retrieve.this, body.getMsg(), Toast.LENGTH_SHORT).show();
                        //销毁登录界面
                        Retrieve.this.finish();
                        //跳转到主界面，登录成功的状态传递到 MainActivity 中
                        startActivity(new Intent(Retrieve.this, Login.class));
                    }

                    /**
                     * 请求失败的回调函数
                     * @param call
                     * @param throwable
                     */
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(Retrieve.this, "网络错误，请检查网络。", Toast.LENGTH_SHORT).show();
                        System.out.println("找回密码错误：" + t.getMessage());
                    }
                });
            }
        });
    }

}
