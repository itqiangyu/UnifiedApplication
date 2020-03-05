package com.example.app.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.app.retrofit.pojo.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private TextView tv_main_title;//标题
    private TextView tv_back, tv_register, tv_find_psw;//返回键,显示的注册，找回密码
    private Button btn_login;//登录按钮
    private String userName, password;//获取的用户名，密码
    private EditText et_user_name, et_psw, etEt_phone;//编辑框

    /**
     * 创建Register实例
     */
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://47.106.99.53/application/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    /**
     * 代理模式：创建 登录接口 的实例
     */
    private ILogin request = retrofit.create(ILogin.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //隐藏标题状态栏
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    //获取界面控件
    private void init() {
        //从main_title_bar中获取的id
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("用户登录");
        tv_back = findViewById(R.id.tv_back);
        //从activity_login.xml中获取的
        tv_register = findViewById(R.id.tv_register);
        tv_find_psw = findViewById(R.id.tv_find_psw);
        btn_login = findViewById(R.id.btn_login);
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        etEt_phone = findViewById(R.id.et_psw_phone);
        //返回键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录界面销毁
                Login.this.finish();
            }
        });
        //立即注册控件的点击事件
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到注册界面，并实现注册功能
                Intent intent = new Intent(Login.this, Register.class);
                startActivityForResult(intent, 1);
            }
        });
        //找回密码控件的点击事件
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到找回密码界面（此页面暂未创建）
                //为了跳转到找回密码界面
                Intent intent = new Intent(Login.this, Retrieve.class);
                startActivityForResult(intent, 1);
            }
        });
        //登录按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码 getText().toString().trim();
                userName = et_user_name.getText().toString().trim();
                password = et_psw.getText().toString().trim();

                // TextUtils.isEmpty
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(Login.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    et_user_name.setError("用户账号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    et_psw.setError("密码不能为空！");
                    return;
                }
                // 发送请求
                try {
                    Call<Result> resultCall = request.login(userName, password);
                    resultCall.enqueue(new Callback<Result>() {
                        /**
                         * 请求成功回调函数
                         * @param call
                         * @param response
                         */
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            Result body = response.body();
    //                        System.out.println(response.headers());
    //                        System.out.println(response.toString());
    //                        System.out.println(body);
                            if (body == null) {
                                Toast.makeText(Login.this, "登录异常，请重试。", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            System.out.println(body.getStatus());

                            if (body.getStatus() != 200) {
                                // 登录失败
                                Toast.makeText(Login.this, body.getMsg(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // 否则登录成功
                            Toast.makeText(Login.this, body.getMsg(), Toast.LENGTH_SHORT).show();

                            // 保存登录状态，在界面保存登录的用户名 定义个方法 saveLoginStatus boolean 状态 , userName 用户名;
                            saveLoginStatus(true, userName);
                            //登录成功后关闭此页面进入主页
                            Intent data = new Intent();
                            //datad.putExtra( ); name , value ;
                            data.putExtra("isLogin", true);
                            //RESULT_OK为Activity系统常量，状态码为-1
                            // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                            setResult(RESULT_OK, data);
                            //销毁登录界面
                            Login.this.finish();
                            //跳转到主界面，登录成功的状态传递到 MainActivity 中
                            startActivity(new Intent(Login.this, Main.class));
                        }

                        /**
                         * 请求失败的回调函数
                         * @param call
                         * @param throwable
                         */
                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Toast.makeText(Login.this, "网络错误，请检查网络。", Toast.LENGTH_SHORT).show();
                            System.out.println("登录错误：" + t.getMessage());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 保存登录状态和登录用户名到SharedPreferences中
     */
    private void saveLoginStatus(boolean status, String userName) {
        //saveLoginStatus(true, userName);
        //loginInfo表示文件名  SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName);
        //提交修改
        editor.commit();
    }

    /**
     * 注册成功的数据返回至此
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    @Override
    //显示数据， onActivityResult
    //startActivityForResult(intent, 1); 从注册界面中获取数据
    //int requestCode , int resultCode , Intent data
    // LoginActivity -> startActivityForResult -> onActivityResult();
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            String userName = data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)) {
                //设置用户名到 et_user_name 控件
                et_user_name.setText(userName);
                //et_user_name控件的setSelection()方法来设置光标位置
                et_user_name.setSelection(userName.length());
            }
        }
    }
}
