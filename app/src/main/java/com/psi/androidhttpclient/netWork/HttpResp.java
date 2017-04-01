package com.psi.androidhttpclient.netWork;

import java.io.Serializable;

public class HttpResp implements Serializable {
  public static final int SUCCESS = 1;
  public static final int FAILURE = 0;
  private int statusCode;
  private String msg;

  public HttpResp() {
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public HttpResp(int statusCode, String msg) {
    this.statusCode = statusCode;
    this.msg = msg;
  }
}
