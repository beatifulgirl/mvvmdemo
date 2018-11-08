package com.example.zhy.mvvmdemo.http;

import android.util.Log;

import com.example.zhy.mvvmdemo.retrofitinterface.RetrofitInterface;
import com.example.zhy.mvvmdemo.utils.TokenPersist;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {

    private static final int DEFAULT_TIMEOUT = 8; //连接 超时的时间，单位：秒
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            addInterceptor(genericClient()).
            addInterceptor(getHttpLoggingInterceptor()).
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;

    /**
     * 单例创建retrofitInterface对象
     * @return
     */
    public synchronized static RetrofitInterface getRetrofit() {
        //初始化retrofit的配置
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLConstant.URL_BASE)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }

    /**
     * 请求返回日志
     * @return
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }

    //设置头部信息
    public static Interceptor genericClient() {
        Interceptor interceptor =  new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Charset", "UTF-8")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("qpToken", TokenPersist.getToken())
                        .build();
                return chain.proceed(request);
            }

        };
        return interceptor;
    }
}
