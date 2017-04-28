package com.psi.androidhttpclient.adapter;

import android.content.pm.ResolveInfo;

/**
 * Created by Administrator on 2017-04-27.
 */
public class myInfo{
  private ResolveInfo info;

  public ResolveInfo getInfo() {
    return info;
  }

  public void setInfo(ResolveInfo info) {
    this.info = info;
  }

  /**
   * 照片是否被选中
   */
  private boolean isSelected = false;

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}
