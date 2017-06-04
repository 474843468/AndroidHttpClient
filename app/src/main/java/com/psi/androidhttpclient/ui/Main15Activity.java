package com.psi.androidhttpclient.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.widget.ObjectAnimator.OnDragButton;
import com.psi.androidhttpclient.widget.ObjectAnimator.ParabolaAnimation;


public class Main15Activity extends Activity {
  private OnDragButton dragButton;
  private ImageView mIv_View,mIv_View1,mIv_View2,mIv_View3,mIv_View4,mIv_View5,mIv_View6;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main15);
    dragButton = (OnDragButton)findViewById(R.id.tv_drag);
    mIv_View =(ImageView)findViewById(R.id.iv_image);
    mIv_View1 =(ImageView)findViewById(R.id.iv_image1);
    mIv_View2 =(ImageView)findViewById(R.id.iv_image2);
    mIv_View3 =(ImageView)findViewById(R.id.iv_image3);
    mIv_View4 =(ImageView)findViewById(R.id.iv_image4);
    mIv_View5=(ImageView)findViewById(R.id.iv_image5);
    mIv_View6=(ImageView)findViewById(R.id.iv_image6);
    ParabolaAnimation.ParabolaValueAnimation(mIv_View4);//Parabola抛物线动画
    ParabolaAnimation.HorizontalAnimation(mIv_View1);//水平动画
    ParabolaAnimation.starthalfAnimation(mIv_View2);//半个抛物线，从上向下
    ParabolaAnimation.propertyValuesHolder(mIv_View3);//放大效果
    ParabolaAnimation.parabolaAnimation(mIv_View);//也是抛物线
    ParabolaAnimation.propertyValuesHolder(mIv_View5);//放大效果
    ParabolaAnimation.startRotateSmallAnimation(mIv_View6);////缩小
    //		dragButton.setText("你奶奶");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
