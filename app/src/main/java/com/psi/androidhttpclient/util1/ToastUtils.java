package com.psi.androidhttpclient.util1;

import android.widget.Toast;
import com.psi.androidhttpclient.common.ApplicationContext;

/**
 * Created by dingeryue on 2016年08月20.
 */
public class ToastUtils {

  private static Toast toast;
  public static void show(CharSequence msg){
    showInner(msg, Toast.LENGTH_SHORT);
  }

  public  static void showLong(CharSequence msg){
    showInner(msg, Toast.LENGTH_LONG);
  }

  private static void showInner(CharSequence msg,int time){
    if(toast != null){
      toast.cancel();
    }
    toast =  Toast.makeText(ApplicationContext.getInstance(),msg,time);
    toast.show();

  }
}
