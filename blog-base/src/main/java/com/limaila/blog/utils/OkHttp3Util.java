package com.limaila.blog.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Slf4j
public class OkHttp3Util {

    public volatile static OkHttp3Util instance;

    private MediaType APPLICATION_JSON_MEDIATYPE = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);

    private MediaType APPLICATION_FORM_MEDIATYPE = MediaType.parse(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE);

    private OkHttpClient okHttpClient;

    /**
     * 单例模式获取OkHttp3Util
     * @return
     */
    public static OkHttp3Util getInstance() {
        if (instance == null) {
            synchronized (OkHttp3Util.class) {
                if (instance == null) {
                    instance = new OkHttp3Util();
                }
            }
        }
        return instance;
    }

    private OkHttp3Util() {
        okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(10, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
//        支持HTTPS请求，跳过证书验证
        ClientBuilder.sslSocketFactory(createSSLSocketFactory(), new MyX509TrustManager());
        ClientBuilder.hostnameVerifier((s, sslSession) -> true);
        okHttpClient = ClientBuilder.build();
    }


    /**
     * 通过同步方式采用GET方法获取数据
     * @param url
     * @return
     */
    public Response getDataForSync(String url) {
        // 获取request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        // 将request封装成Call
        Call call = okHttpClient.newCall(request);
        // 调用同步执行方法
        return syncExecute(call);
    }

    /**
     * 通过异步方式采用GET方法获取数据
     * @param url
     * @return
     */
    public void getDataForAsync(String url, Callback callback) {
        // 获取request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        // 将request封装成Call
        Call call = okHttpClient.newCall(request);
        // 调用异步执行方法
        asyncExecute(call, callback);
    }

    /**
     * 通过同步方式采用POST方法JSON方式获取数据
     * @param url
     * @return
     */
    public Response postJsonDataForSync(String url, String data, Map<String, Serializable> headers) {
        Request.Builder builder = new Request.Builder();
        RequestBody body = RequestBody.create(APPLICATION_JSON_MEDIATYPE, data);
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.post(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 通过异步方式采用POST方法JSON方式获取数据
     * @param url
     * @return
     */
    public void postJsonDataForAsync(String url, String data, Map<String, Serializable> headers, Callback callback) {
        Request.Builder builder = new Request.Builder();
        RequestBody body = RequestBody.create(APPLICATION_JSON_MEDIATYPE, data);
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.post(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 通过同步方式采用POST方法FORM表单方式获取数据
     * @param url
     * @return
     */
    public Response postFormDataForSync(String url, Map<String, Serializable> datas, Map<String, Serializable> headers) {
        Request.Builder builder = new Request.Builder();
        RequestBody formBody = FormBody.create(APPLICATION_FORM_MEDIATYPE, JSON.toJSONString(datas));
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.post(formBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 通过同步方式采用PUT方法JSON方式获取数据
     * @param url
     * @return
     */
    public Response putJsonDataForSync(String url, String data, Map<String, Serializable> headers) {
        Request.Builder builder = new Request.Builder();
        RequestBody body = RequestBody.create(APPLICATION_JSON_MEDIATYPE, data);
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.put(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 通过异步方式采用PUT方法JSON方式获取数据
     * @param url
     * @return
     */
    public void putJsonDataForAsync(String url, String data, Map<String, Serializable> headers, Callback callback) {
        Request.Builder builder = new Request.Builder();
        RequestBody body = RequestBody.create(APPLICATION_JSON_MEDIATYPE, data);
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.put(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }

    /**
     * 通过同步方式采用DELETE方法JSON方式获取数据
     * @param url
     * @return
     */
    public Response deleteJsonDataForSync(String url, Map<String, Serializable> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.delete().url(url).build();
        Call call = okHttpClient.newCall(request);
        return syncExecute(call);
    }

    /**
     * 通过异步方式采用DELETE方法JSON方式获取数据
     * @param url
     * @return
     */
    public void deleteJsonDataForSync(String url, Map<String, Serializable> headers, Callback callback) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            headers.forEach((s, o) -> {
                builder.addHeader(s, o.toString());
            });
        }
        Request request = builder.delete().url(url).build();
        Call call = okHttpClient.newCall(request);
        asyncExecute(call, callback);
    }


    /**
     * 封装异步执行方法
     * @param call
     * @param callback
     */
    private void asyncExecute(Call call, Callback callback) {
        call.enqueue(callback);
    }

    /**
     * 封装同步执行方法
     * @param call
     * @return
     */
    private Response syncExecute(Call call) {
        Response response = null;
        try {
            response = call.execute();
            if (!response.isSuccessful()) {
                response.close();
            }
        } catch (IOException e) {
            log.error("OkHttp3Util execute fail",e);
        }
        return response;
    }


    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new MyX509TrustManager()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    /**
     * 用于信任所有证书
     */
    class MyX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
