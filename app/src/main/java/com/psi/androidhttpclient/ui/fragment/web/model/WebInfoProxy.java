package com.psi.androidhttpclient.ui.fragment.web.model;

import android.webkit.JavascriptInterface;
import com.psi.androidhttpclient.util1.gson.GsonUtils;

/**
 * 作者：XieDu
 * 创建时间：2016/10/31 16:25
 * 描述：
 */
public class WebInfoProxy<T> implements IWebInfoProxy {

    private T mInfo;
    private WebListener mWebListener;

    @Override
    @JavascriptInterface
    public String getInfoJson() {
        return GsonUtils.getGson().toJson(mInfo);
    }

    @Override
    @JavascriptInterface
    public void close() {
        if (mWebListener != null) {
            mWebListener.onClosed();
        }
    }

    public T getInfo() {
        return mInfo;
    }

    public void setInfo(T info) {
        mInfo = info;
    }

    public WebListener getWebListener() {
        return mWebListener;
    }

    public void setWebListener(WebListener webListener) {
        mWebListener = webListener;
    }
}
