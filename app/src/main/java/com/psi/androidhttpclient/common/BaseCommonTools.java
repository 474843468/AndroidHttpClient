package com.psi.androidhttpclient.common;

import android.app.Activity;
import android.app.Application;
import java.util.HashMap;

public abstract class BaseCommonTools {
  protected static BaseCommonTools instance;

  public BaseCommonTools() {
  }

  public static BaseCommonTools getInstance() {
    return instance;
  }

  public abstract Application getCurApplication();

  public abstract void loginTimeOutHandler(Activity var1, String var2);

  public abstract void noOperateTimeOutHandler(Activity var1);

  public abstract HashMap<String, HashMap<String, String>> getCookieMap();

  public abstract void SetCookie(String var1, String var2);

  public abstract int getScreenTimeOut();

  public abstract void setCurrentActivity(Activity var1);

  public abstract String getdeviceInfo();

  public abstract String getpushAddress();
}