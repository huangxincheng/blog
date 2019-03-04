package com.limaila.blog.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Slf4j
public class OkHttpUtil {

    private static OkHttpClient okHttpClient;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)         //设置连接超时
                .readTimeout(60, TimeUnit.SECONDS)           //设置读超时
                .writeTimeout(60, TimeUnit.SECONDS)         //设置写超时
                .retryOnConnectionFailure(true)                    //是否自动重连
                .build();
    }

    private static Request bulidRequest(String url, Map<String, Object> headers) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.addHeader(key, (String) headers.get(key));
            }
        }
        Request request = builder.build();
        return request;
    }

    private static Request buildFormRequest(String url, Map<String, Object> map, Map<String, Object> headers) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.addHeader(key, (String) headers.get(key));
            }
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (map != null) {
            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    formBuilder.add(key, map.get(key) + "");
                }
            }
        }
        Request request = builder.post(formBuilder.build()).build();
        return request;
    }

    private static Request buildJsonRequest(String url, String data, Map<String,Object> headers) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.addHeader(key, (String) headers.get(key));
            }
        }
        RequestBody requestBody = FormBody.create(okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_UTF8_VALUE), data);
        Request request = builder.post(requestBody).build();
        return request;
    }


    public static String doPostByJson(String url, Map<String, Object> headers, Object data) {
        return doPostByJson(url, headers, JSON.toJSONString(data));
    }

    public static String doPostByJson(String url, Map<String, Object> headers, String data) {
        log.debug("OkHttpUtil doPostByJson url =" + url);
        String resContent = null;
        try {
            Request request = buildJsonRequest(url, data, headers);
            Response response = okHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                resContent = response.body().string();
            } else {
                log.error("OkHttpUtil doPostByJson response isSuccessful =" + response.isSuccessful());
            }
        } catch (Exception e) {
            log.error("OkHttpUtil doPostByJson",e);
        }
        return resContent;
    }


    public static String doPostByForm(String url) {
        return doPostByForm(url, null, null);
    }

    public static String doPostByForm(String url, Map<String, Object> map) {
        return doPostByForm(url, null, map);
    }

    public static String doPostByForm(String url, Map<String, Object> headers, Map<String, Object> map) {
        log.debug("OkHttpUtil doPostByForm url =" + url);
        String resContent = null;
        try {
            Request request = buildFormRequest(url, map, headers);
            Response response = okHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                resContent = response.body().string();
            } else {
                log.error("OkHttpUtil doPostByForm response isSuccessful =" + response.isSuccessful());
            }
        } catch (Exception e) {
            log.error("OkHttpUtil doPostByForm",e);
        }
        return resContent;
    }


    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static String doGet(String url, Map<String, Object> params) {
        return doGet(url, null, params);
    }

    /**
     * @param url 请求的url
     * @param headers 请求头
     * @param params url参数
     * @return
     */
    public static String doGet(String url, Map<String, Object> headers, Map<String, Object> params) {
        log.debug("OkHttpUtil doGet url =" + url);
        String resContent = null;
        try {
            //构建URL参数
            if (params != null) {
                for (String key : params.keySet()) {
                    if (url.contains("?"))
                        url += "&" + key + "=" + params.get(key);
                    else
                        url += "?" + key + "=" + params.get(key);
                }
            }
            Request request = bulidRequest(url, headers);
            Response response = okHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                return response.body().string();
            } else {
                log.error("OkHttpUtil doGet response isSuccessful =" + response.isSuccessful());
            }
        } catch (Exception e) {
           log.error("OkHttpUtil doGet",e);
        }
        return resContent;
    }
}