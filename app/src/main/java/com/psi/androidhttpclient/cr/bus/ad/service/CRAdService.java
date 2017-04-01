package com.psi.androidhttpclient.cr.bus.ad.service;

import com.psi.androidhttpclient.cr.bus.ad.model.CRgetPosterList.CRgetPosterListParams;
import com.psi.androidhttpclient.cr.bus.ad.model.CRgetPosterList.CRgetPosterListResponse;
import com.psi.androidhttpclient.cr.bus.ad.model.CRgetPosterList.CRgetPosterListResult;
import com.psi.androidhttpclient.cr.common.client.CRClient;
import rx.Observable;

/**
 * 广告业务 service 
 */
public class CRAdService {
    /**
     * 获取欢迎页面全局消息列表
     *
     * @param params
     * @return
     */
    public Observable<CRgetPosterListResult> cRgetPosterList (CRgetPosterListParams params) {
        return CRClient.instance.post("CSgetPosterList", params,  CRgetPosterListResponse.class);
    }
}
