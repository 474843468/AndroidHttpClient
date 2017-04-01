package com.psi.androidhttpclient.netWork;

import java.io.Serializable;

/**
 * Created by zjq on 2016/5/10.
 * 划菜
 */
public class HttpServingProdReq implements Serializable {
  private String mOrderId;//订单id
  private String mTableId;//桌台id
  private int isServing;//是否已上(0:未上,1:已上)
  private String mObjId;//ObjId  PxOrderDetails ObjiectId

  @Override public String toString() {
    return "HttpServingProdReq{" +
        "mOrderId='" + mOrderId + '\'' +
        ", mTableId='" + mTableId + '\'' +
        ", isServing=" + isServing +
        ", mObjId='" + mObjId + '\'' +
        '}';
  }

  public String getOrderId() {
    return mOrderId;
  }

  public void setOrderId(String orderId) {
    mOrderId = orderId;
  }

  public String getTableId() {
    return mTableId;
  }

  public void setTableId(String tableId) {
    mTableId = tableId;
  }

  public int getIsServing() {
    return isServing;
  }

  public void setIsServing(int isServing) {
    this.isServing = isServing;
  }

  public String getObjId() {
    return mObjId;
  }

  public void setObjId(String objId) {
    mObjId = objId;
  }
}
