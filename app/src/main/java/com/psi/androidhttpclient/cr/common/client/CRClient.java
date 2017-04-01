package com.psi.androidhttpclient.cr.common.client;

import com.google.gson.Gson;
import com.psi.androidhttpclient.client.BaseHttpClient;
import com.psi.androidhttpclient.client.network.GsonConverterFactory;
import com.psi.androidhttpclient.client.network.Method;
import com.psi.androidhttpclient.client.network.RequestInfo;
import com.psi.androidhttpclient.common.CRGlobalConst;
import com.psi.androidhttpclient.cr.common.model.CRRequest;
import com.psi.androidhttpclient.cr.common.model.CRResponse;
import com.psi.androidhttpclient.cr.common.response.CRBeanParser;
import com.psi.androidhttpclient.util1.LoggerUtils;
import com.psi.androidhttpclient.util1.gson.GsonUtils;
import java.util.HashMap;
import okhttp3.Response;
import rx.Observable;

/**
 * 处理网络请求
 * Created by XieDu on 2016/3/8.
 */
public enum CRClient {

    instance;//单例对象

    // gson解析用
    private Gson gson;
    private BaseHttpClient httpClient;


    /**
     * 构造函数
     */
     CRClient() {
        gson = GsonUtils.getGson();
        httpClient = getHttpClient();
    }


    public Observable<Response>get (String url) {
        RequestInfo requestInfo=new RequestInfo(httpClient).url(url).method(Method.GET);
        return httpClient.execute(requestInfo);
    }


    public <T, R> Observable<R> post(String method, T params, final Class<? extends CRResponse<R>> clazz) {
        String url = CRClientConfig.getBiiUrl();
        return post(url, method, params, clazz);
    }

    public <T, R> Observable<R> post(String method, final Class<? extends CRResponse<R>> clazz) {

        return post(method, new Object(), clazz);
    }

    public <T, R> Observable<R> post(String url, String method, T params, final Class<? extends CRResponse<R>> clazz) {
        String rquestString = GsonUtils.getGson(gson,params.getClass()).toJson(new CRRequest<>(method, params));
        LoggerUtils.Info("request:" + rquestString);
        RequestInfo postRequest = new RequestInfo(httpClient).url(url).method(Method.POST).addFormParam("json", rquestString)
                .addHeader("Connection", "Keep-Alive")
                .addHeader("User-Agent","X-ANDR|1.5.24")
                .addHeader("Accept-Encoding", "")
                .addHeader("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8")
                .addHeader("bfw-ctrl", "json")
                .addHeader("Accept-Language", "zh-cn");
        return httpClient.execute(postRequest, clazz)
                .flatMap(new CRBeanParser<R>());
    }

    public BaseHttpClient getHttpClient() {
        if (null == httpClient) {


            httpClient = new BaseHttpClient.HttpClientBuilder()
                    .setConnectTimeout(CRGlobalConst.TIMEOUT)
                    .setReadTimeout(CRGlobalConst.TIMEOUT)
                    .setWriteTimeout(CRGlobalConst.TIMEOUT)
                    .setConverterFactory(new GsonConverterFactory(gson))
                    .build();

        }
        return httpClient;
    }

    public static void config(CRConfigInterface param){
        CRClientConfig.setConfig(param);
    }

    /**
     * 保存cookies
     * @param cookiesMap
     */
    public void saveCookies(HashMap<String, HashMap<String, String>> cookiesMap){
        httpClient.saveCookies(cookiesMap);
    }

    /**
     * 清楚cookies
     */
    public void clearCookies(){
        httpClient.clearCookies();
    }
}
