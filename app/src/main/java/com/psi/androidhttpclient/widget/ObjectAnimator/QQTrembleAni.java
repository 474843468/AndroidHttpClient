package com.psi.androidhttpclient.widget.ObjectAnimator;


import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * QQ抖一抖特效的自定义View动画实现
 */
public class QQTrembleAni extends Animation {
  @Override
  protected void applyTransformation(float interpolatedTime, Transformation t) {
    t.getMatrix().setTranslate(
        (float) Math.sin(interpolatedTime * 50) * 8,
        (float) Math.sin(interpolatedTime * 50) * 8
    );// 50越大频率越高，8越小振幅越小
    super.applyTransformation(interpolatedTime, t);
  }
}