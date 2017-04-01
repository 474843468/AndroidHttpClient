package com.psi.androidhttpclient.bocyun.common.client;

import com.psi.androidhttpclient.client.model.RequestParams;

public interface YunConfigInterface {

	/**
	 * 获取新接口bii地址
	 * @return 新接口bii地址
	 */
	String getUrl();

	/**
	 * 获取通用参数
	 * @return 通用参数
	 */
	RequestParams getCommonParams();

}
