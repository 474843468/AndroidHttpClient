package com.psi.androidhttpclient.bill.client;

import com.psi.androidhttpclient.client.model.RequestParams;

/**
 * BIIClient的配置
 * Created by XieDu on 2016/2/23.
 */
public class BIIClientConfig {

    private static volatile BiiConfigInterface config;

    public static String getBiiUrl(){
        return config.getBiiUrl();
    }

    public static String getBPMSUrl(){
        return config.getBMPSUrl();
    }

    public static String getVaryficationCodeUrl(){
        return config.getVaryficationCodeUrl();
    }

    /**
     * @return 通用参数，包括headers和urlParams
     */
    public static RequestParams getCommonParams(){
//        return config.getCommonParams();
        return null;
    }



    public static void setConfig(BiiConfigInterface param){
        config = param;
    }
}
