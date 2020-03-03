package com.example.app.retrofit;

import com.example.app.retrofit.pojo.Result;
import com.example.app.retrofit.pojo.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * 注册
 */
public interface IRegister {

    /**
     * 注册
     * @param user
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Call<Result> register(@Field("userName") String userName, @Field("password") String password, @Field("phone") String phone);

}
