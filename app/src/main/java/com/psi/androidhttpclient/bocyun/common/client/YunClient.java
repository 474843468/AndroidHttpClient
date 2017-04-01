package com.psi.androidhttpclient.bocyun.common.client;

import com.google.gson.Gson;
import com.psi.androidhttpclient.bocyun.HeaderParamCenter;
import com.psi.androidhttpclient.bocyun.common.global.YunGlobalConst;
import com.psi.androidhttpclient.bocyun.common.model.YunHeader;
import com.psi.androidhttpclient.bocyun.common.model.YunRequest;
import com.psi.androidhttpclient.bocyun.common.model.YunResponse;
import com.psi.androidhttpclient.bocyun.common.response.YunBeanParser;
import com.psi.androidhttpclient.bocyun.common.tools.YunTools;
import com.psi.androidhttpclient.client.BaseHttpClient;
import com.psi.androidhttpclient.client.CookieStore;
import com.psi.androidhttpclient.client.network.GsonConverterFactory;
import com.psi.androidhttpclient.client.network.Method;
import com.psi.androidhttpclient.client.network.RequestInfo;
import com.psi.androidhttpclient.util1.LoggerUtils;
import com.psi.androidhttpclient.util1.gson.GsonUtils;
import java.util.Calendar;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.Response;
import rx.Observable;


public enum YunClient {

    instance;//单例对象

    // gson解析用
    private Gson gson;
    private BaseHttpClient httpClient;


    /**
     * 构造函数
     */
     YunClient() {
        gson = GsonUtils.getGson();
        httpClient = getHttpClient();
    }


    public Observable<Response>get (String url) {
        RequestInfo requestInfo=new RequestInfo(httpClient).url(url).method(Method.GET);
        return httpClient.execute(requestInfo);
    }


    public <T, R> Observable<R> post(String method,T params, final Class<? extends YunResponse<R>> clazz) {
        String url = YunClientConfig.getUrl();
        return post(url, method,params, clazz);
    }


    public <T, R> Observable<R> post(String url, String method,T params, final Class<? extends YunResponse<R>> clazz) {
        YunRequest<T> tVirRequest = new YunRequest<>(params);

        YunHeader header = HeaderParamCenter.getInstance();

        header.setMsgType("0");
        Calendar instance = Calendar.getInstance();
        header.setTrDt(YunTools.getFormatDate(instance));
        header.setTrTime(YunTools.getFormatTime(instance));
        header.setReqTraceNo(YunTools.getTransId(instance));

        String aes = header.getProduct()+"_"+header.getVersion()+"_"+header.getReqTraceNo();
        header.setValidFlag("");

        tVirRequest.setHeader(header);


        String rquestString = gson.toJson(tVirRequest);

        if(method != null && method.length()>0){
            url = url+method.toLowerCase();
        }
        LoggerUtils.Info("requestURL:"+url);
        LoggerUtils.Info("request:" + rquestString);//.addFormParam("json", rquestString);
        RequestInfo postRequest = new RequestInfo(httpClient).url(url).method(Method.POST).content(
            MediaType.parse("application/json"),rquestString);

        return httpClient.execute(postRequest, clazz)
                .flatMap(new YunBeanParser<R>());
    }

    public BaseHttpClient getHttpClient() {
        if (null == httpClient) {


            httpClient = new BaseHttpClient.HttpClientBuilder()
                    .setConnectTimeout(YunGlobalConst.TIMEOUT)
                    .setReadTimeout(YunGlobalConst.TIMEOUT)
                    .setWriteTimeout(YunGlobalConst.TIMEOUT)
                    .setConverterFactory(new GsonConverterFactory(gson))
                    .build();

        }
        return httpClient;
    }

    public static void config(YunConfigInterface param){
        YunClientConfig.setConfig(param);
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

    /**
     * 获取cookies
     */
    public CookieStore getCookies(){
        return httpClient.getCookies();
    }
}
