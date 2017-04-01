package com.psi.androidhttpclient.bill.client;

import com.google.gson.Gson;
import com.psi.androidhttpclient.bill.model.BIIRequest;
import com.psi.androidhttpclient.bill.model.BIIResponse;
import com.psi.androidhttpclient.bill.response.BIIBeanParser;
import com.psi.androidhttpclient.client.BaseHttpClient;
import com.psi.androidhttpclient.client.CookieStore;
import com.psi.androidhttpclient.client.network.GsonConverterFactory;
import com.psi.androidhttpclient.client.network.Method;
import com.psi.androidhttpclient.client.network.RequestInfo;
import com.psi.androidhttpclient.common.BIIGlobalConst;
import com.psi.androidhttpclient.util1.LoggerUtils;
import com.psi.androidhttpclient.util1.gson.GsonUtils;
import java.util.HashMap;
import okhttp3.Response;
import rx.Observable;

/**
 * 处理网络请求
 * Created by XieDu on 2016/3/8.
 */
public enum BIIClient {

    instance;//单例对象

    // gson解析用
    private Gson gson;
    private BaseHttpClient httpClient;

    /**
     * 构造函数
     */
    BIIClient() {
        gson = GsonUtils.getGson();
        httpClient = getHttpClient();
    }

    public Observable<Response> get(String url) {
        RequestInfo requestInfo = new RequestInfo(httpClient).url(url).method(Method.GET);
        return httpClient.execute(requestInfo);
    }

    public <T, R> Observable<R> post(String method, T params,
            final Class<? extends BIIResponse<R>> clazz) {
        String url = BIIClientConfig.getBiiUrl();
        return post(url, method, params, clazz);
    }

    public <R> Observable<R> post(String method, final Class<? extends BIIResponse<R>> clazz) {
        return post(method, null, clazz);
    }

    public <T, R> Observable<R> post(String url, String method, T params,
            final Class<? extends BIIResponse<R>> clazz) {
        String rquestString =
                (params == null ? gson : GsonUtils.getGson(gson, params.getClass())).toJson(
                        new BIIRequest<>(method, params == null ? new Object() : params));
        LoggerUtils.thread();
        LoggerUtils.Info("request:" + rquestString);
        RequestInfo postRequest = new RequestInfo(httpClient).url(url)
                                                             .method(Method.POST)
                                                             .addFormParam("json", rquestString)
                                                             .addHeader("Connection", "Keep-Alive")
                                                             .addHeader("User-Agent",
                                                                     "X-ANDR|1.5.24")
                                                             .addHeader("Accept-Encoding", "")
                                                             .addHeader("Content-Type",
                                                                     "application/x-www-form-urlencoded;charset=UTF-8")
                                                             .addHeader("bfw-ctrl", "json")
                                                             .addHeader("Accept-Language", "zh-cn");
        return httpClient.execute(postRequest, clazz).flatMap(new BIIBeanParser<R>());
    }

    public BaseHttpClient getHttpClient() {
        if (null == httpClient) {

            httpClient =
                    new BaseHttpClient.HttpClientBuilder().setConnectTimeout(BIIGlobalConst.TIMEOUT)
                                                          .setReadTimeout(BIIGlobalConst.TIMEOUT)
                                                          .setWriteTimeout(BIIGlobalConst.TIMEOUT)
                                                          .setConverterFactory(
                                                                  new GsonConverterFactory(gson))
                                                          .build();
        }
        return httpClient;
    }

    public static void config(BiiConfigInterface param) {
        BIIClientConfig.setConfig(param);
    }

    /**
     * 保存cookies
     */
    public void saveCookies(HashMap<String, HashMap<String, String>> cookiesMap) {
        httpClient.saveCookies(cookiesMap);
    }

    /**
     * 清楚cookies
     */
    public void clearCookies() {
        httpClient.clearCookies();
    }

    /**
     * 获取cookies
     */
    public CookieStore getCookies() {
        return httpClient.getCookies();
    }
}
