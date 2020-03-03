package com.example.app.retrofit;

import com.example.app.retrofit.pojo.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 登录
 */
public interface ILogin {

    /**
     * 登录
     *      如果POST("/user/ILogin")的请求是 /user/ILogin ，则是直接从ip地址（域名）开始请求，如：http://12.12.12.12/user/login
     *      如是 user/ILogin ，则是从设置的 .baseUrl("http://12.12.12.12/application/") 后面拼接，如： http://12.12.12.12/application/user/login
     *
     * @param userName
     * @param password
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Call<Result> login(@Field("userName") String userName, @Field("password") String password);
}
