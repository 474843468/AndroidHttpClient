package com.psi.androidhttpclient.netWork;

import java.io.Serializable;

/**
 * Created by zjq on 2016/5/9.
 * 改单请求
 */
public class HttpModifyBillReq implements Serializable {
  private String mTableId;//桌台id
  private String mMoveTableId;//目标桌台id
 // private String mOrderId;//订单id
  private long mPeopleNum;//人数
  private String mRemarks; //备注
  private String mWaiterId;  //服务生id
  private String mPromotioInfoId;//促销计划

  public String getPromotioInfoId() {
    return mPromotioInfoId;
  }

  public void setPromotioInfoId(String promotioInfoId) {
    mPromotioInfoId = promotioInfoId;
  }

  public String getTableId() {
    return mTableId;
  }

  public void setTableId(String tableId) {
    mTableId = tableId;
  }

  public String getMoveTableId() {
    return mMoveTableId;
  }

  public void setMoveTableId(String moveTableId) {
    mMoveTableId = moveTableId;
  }

  public long getPeopleNum() {
    return mPeopleNum;
  }

  public void setPeopleNum(long peopleNum) {
    mPeopleNum = peopleNum;
  }
  //
  //public String getOrderId() {
  //  return mOrderId;
  //}
  //
  //public void setOrderId(String orderId) {
  //  mOrderId = orderId;
  //}

  public String getRemarks() {
    return mRemarks;
  }

  public void setRemarks(String remarks) {
    mRemarks = remarks;
  }

  public String getWaiterId() {
    return mWaiterId;
  }

  public void setWaiterId(String waiterId) {
    mWaiterId = waiterId;
  }
}
