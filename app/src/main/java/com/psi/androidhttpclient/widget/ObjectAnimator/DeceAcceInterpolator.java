package com.psi.androidhttpclient.widget.ObjectAnimator;

//分析到这里相信同学们很快就能举一反三，瞬间明白其他几种常见插值器的实现原理了，这里我就不继续解释源码了，而是要放出大招——自定义先减速后加速插值器DeceAcceInterpolator，DeceAcceInterpolator需要实现Interpolator接口，代码如下：

import android.view.animation.Interpolator;

/**
 * DeceAcceInterpolator自定义减速加速插值器
 * Created by wondertwo on 2016/3/25.
 */
public class DeceAcceInterpolator implements Interpolator {
  @Override
  public float getInterpolation(float input) {
    return ((4*input-2)*(4*input-2)*(4*input-2))/16f + 0.5f;
  }
}
  //在getInterpolation(float input)方法中返回值只有一行代码，很简单吧！把返回值((4*input-2)*(4*input-2)*(4*input-2))/16f + 0.5f翻译成数学表达式如下：

  //  [(4*input-2)^3]/16 + 0.5