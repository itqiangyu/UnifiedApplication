package com.example.app.retrofit;

import com.example.app.retrofit.pojo.DataInformation;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * 接收第五、第六、第二十五题目数据：温度、湿度、光照、二氧化碳、PM2.5、道路状态1、道路状态2、道路状态3
 */
public interface IDataInformation {

    /**
     * 接收 温度、湿度、光照、二氧化碳、PM2.5、道路状态1、道路状态2、道路状态3 的数据
     * @return
     */
    @POST("user/five_six_twentyFive_data")
    Call<DataInformation> receiveData();

}
