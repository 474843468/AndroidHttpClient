package com.psi.androidhttpclient.netWork;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 * Created by jianliu on 2017/3/13.
 */
public class HttpProductListResp extends HttpResp{
    @Expose
    private List<PxProductInfo> list;

    public List<PxProductInfo> getList() {
        return list;
    }

    public void setList(List<PxProductInfo> list) {
        this.list = list;
    }

    @Override public String toString() {
        return "HttpProductListResp{" +
            "list=" + list +
            '}';
    }
}
