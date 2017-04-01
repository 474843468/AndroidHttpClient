package com.psi.androidhttpclient.cr.bus.product.service;

import com.psi.androidhttpclient.cr.bus.product.model.CRgetProductList.CRgetProductListParams;
import com.psi.androidhttpclient.cr.bus.product.model.CRgetProductList.CRgetProductListResponse;
import com.psi.androidhttpclient.cr.bus.product.model.CRgetProductList.CRgetProductListResult;
import com.psi.androidhttpclient.cr.common.client.CRClient;
import com.psi.androidhttpclient.cr.common.client.CRClientConfig;
import rx.Observable;

/**
 * 产品推荐
 */
public class CRProductService {
    /**
     * 获取推荐列表
     * @param params
     * @return
     */
    public Observable<CRgetProductListResult> cRgetProductList (CRgetProductListParams params) {
        return CRClient.instance.post(CRClientConfig.getBiiUrl(),"CSgetProductList", params,  CRgetProductListResponse.class);
    }
}
