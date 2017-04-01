package com.psi.androidhttpclient.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.util1.LogUtils;

/**
 * 底部Tab
 */
public class BottomTabIndicator extends TabIndicatorView {

  protected View rootView;
  protected ImageView ivIndicatorIcon;
  protected TextView tvTitle;
  private String tagId;
  private Class<? extends Fragment> fragmentClass;

  //    @Bind(R.id.tv_title)
  //    TextView tvTitle;
  //    @Bind(R.id.iv_indicator_icon)
  //    ImageView ivIndcatorIcon;

  public BottomTabIndicator(Context context) {
    super(context);
  }

  @Override
  protected void initView() {
    ivIndicatorIcon = (ImageView) rootView.findViewById(R.id.iv_indicator_icon);
    tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
  }

  @Override
  public View getContentView() {
    rootView = View.inflate(mContext, R.layout.boc_tab_indicator, null);
    return rootView;
  }

  public BottomTabIndicator setIcon(Drawable iocn) {
    ivIndicatorIcon.setImageDrawable(iocn);
    return this;
  }

  public BottomTabIndicator setTitle(String title) {
    tvTitle.setText(title);
    return this;
  }

  public BottomTabIndicator setTagId(String tagId) {
    this.tagId = tagId;
    return this;
  }

  public BottomTabIndicator setFragmentClass(Class<? extends Fragment> fragmentClass) {
    this.fragmentClass = fragmentClass;
    return this;
  }


  @Override
  public String getTagId() {
    return this.tagId;
  }

  @Override
  public Class<? extends Fragment> getFragmentClass() {
    return this.fragmentClass;
  }


  private boolean isBadgeShow = false;
  private int badgeNumber;
  private Paint badgePaint;
  private int badgeR = getResources().getDimensionPixelOffset(R.dimen.boc_space_between_10px);
  public void setBadgeNumber(boolean isShow,int count){
    this.isBadgeShow = isShow;
    this.badgeNumber = count;
    invalidate();
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    LogUtils.d("dding","----dispatchDraw----->"+isBadgeShow);
    if(!isBadgeShow)return;
    if(badgePaint == null){
      badgePaint = new Paint();
      badgePaint.setAntiAlias(true);
      badgePaint.setColor(Color.RED);
      badgePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    canvas.drawCircle(getMeasuredWidth()*0.75f,getMeasuredHeight()*0.2f,badgeR,badgePaint);
  }
}
