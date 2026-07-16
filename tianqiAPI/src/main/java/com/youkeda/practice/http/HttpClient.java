package com.youkeda.practice.http;

import com.youkeda.practice.config.ConfigLoader;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class HttpClient {

    private static volatile OkHttpClient instance;

    private HttpClient() {
    }

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new OkHttpClient.Builder()
                            .connectTimeout(ConfigLoader.getConnectTimeout(), TimeUnit.MILLISECONDS)
                            .readTimeout(ConfigLoader.getReadTimeout(), TimeUnit.MILLISECONDS)
                            .writeTimeout(ConfigLoader.getWriteTimeout(), TimeUnit.MILLISECONDS)
                            .build();
                }
            }
        }
        return instance;
    }
}