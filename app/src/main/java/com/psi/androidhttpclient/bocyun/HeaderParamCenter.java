package com.psi.androidhttpclient.bocyun;

import com.psi.androidhttpclient.bocyun.common.model.YunHeader;

/**
 * Created by dingeryue on 2016年09月21.
 */
public class HeaderParamCenter {

  private static YunHeader header = demoHeader();

  public static YunHeader getInstance(){
    return header;
  }


  private static YunHeader demoHeader(){
    YunHeader virHeader = new YunHeader();

    return  virHeader;
  }
}
