package com.example.app.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.retrofit.IRegister;
import com.example.app.retrofit.pojo.Result;
import com.example.app.retrofit.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    private TextView tv_main_title;//标题
    private TextView tv_back;//返回按钮
    private Button btn_register;//注册按钮
    //用户名，密码，再次输入的密码的控件
    private EditText et_user_name, et_psw, et_psw_again, et_psw_phone;
    // 再次输入的密码的控件的获取值
    private String pswAgain;
    // 标题布局
    private RelativeLayout rl_title_bar;

    /**
     * 创建Retrofit实例
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://47.106.99.53/application/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * 创建 注册接口 的实例
     */
    private IRegister request = retrofit.create(IRegister.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init() {
        //页面布局中获取对应的UI控件
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("用户注册");
        tv_back = findViewById(R.id.tv_back);
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        // 注册按钮
        btn_register = findViewById(R.id.btn_register);
        // 用户名、密码、电话号码
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        et_psw_again = findViewById(R.id.et_psw_again);
        et_psw_phone = findViewById(R.id.et_psw_phone);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                Register.this.finish();
            }
        });
        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                final User user = getEditString();

                //判断输入框内容
                if (TextUtils.isEmpty(user.getUserName())) {
                    Toast.makeText(Register.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    et_user_name.setError("用户账号不能为空！");
                    return;
                }

                if (TextUtils.isEmpty(user.getPassword())) {
                    Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    et_psw.setError("密码不能为空！");
                    return;
                }

                if (TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(Register.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!user.getPassword().equals(pswAgain)) {
                    Toast.makeText(Register.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    et_psw_again.setError("输入两次的密码不一样！");
                    return;
                }

                if (TextUtils.isEmpty(user.getPhone())) {
                    Toast.makeText(Register.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    et_psw_phone.setError("手机号不能为空！");
                    return;
                }

                Call<Result> resultCall = request.register(user.getUserName(), user.getPassword(), user.getPhone());
                resultCall.enqueue(new Callback<Result>() {
                    /**
                     * 请求成功回调函数
                     * @param call
                     * @param response
                     */
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Result body = response.body();

                        if (body == null) {
                            Toast.makeText(Register.this, "注册异常，请重试。", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (body.getStatus() != 200) {
                            Toast.makeText(Register.this, body.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // 注册成功
                        Toast.makeText(Register.this, body.getMsg(), Toast.LENGTH_SHORT).show();

                        //注册成功后把账号传递到LoginActivity.java中
                        // 返回值到loginActivity显示
                        Intent data = new Intent();
                        data.putExtra("userName", user.getUserName());
                        setResult(RESULT_OK, data);
                        //RESULT_OK为Activity系统常量，状态码为-1，
                        // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                        Register.this.finish();
                    }

                    /**
                     * 请求失败的回调函数
                     * @param call
                     * @param throwable
                     */
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(Register.this, "网络错误，请检查网络。", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 获取控件中的字符串
     */
    private User getEditString() {
        User user = new User();
        user.setUserName(et_user_name.getText().toString().trim());
        user.setPassword(et_psw.getText().toString().trim());
        user.setPhone(et_psw_phone.getText().toString().trim());
        // 获取 “再次输入密码” 的值
        pswAgain = et_psw_again.getText().toString().trim();
        return user;
    }

}
