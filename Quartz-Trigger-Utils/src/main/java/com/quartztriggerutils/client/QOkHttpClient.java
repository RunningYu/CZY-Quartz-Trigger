package com.quartztriggerutils.client;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/26
 */
@Slf4j
public class QOkHttpClient {
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();

    public static String getMapping(String url) {
        log.info("QOkHttpClient getMapping url:{}", url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall( request );
        try {
            Response response = call.execute();
            log.info("QOkHttpClient getMapping response:{}", JSON.toJSONString(response.body()));
            return JSON.toJSONString(response.body());
        } catch (IOException e) {
            log.error("QOkHttpClient getMapping error:{}", e);
            return null;
        }
    }
}
