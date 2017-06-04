package com.psi.androidhttpclient.widget.ObjectAnimator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class ParabolaAnimation {
  public static int mCount;

  /**TimeInterpolator控制动画的速度，而TypeEvaluator控制动画的值，他们可以共同作用，也可以单独作用（让另一个使用默认值）。

   实际上，TypeEvaluator中的一个参数fraction，就是『复合函数』中TimeInterpolator计算的结果。即fraction=getInterpolation()。
   * Interpolator直译过来就是插补器，也译作插值器，直接控制动画的变化速率，这涉及到变化率概念，形象点说就是加速度，可以简单理解为变化的快慢。
   * 从上面的继承关系可以清晰的看出来，Interpolator是一个接口，并未提供插值逻辑的具体实现，它的非直接子类有很多，
   *
   *    Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果accelerated(加速)
   *    ，decelerated(减速),repeated(重复),bounced(弹跳)等。
   * AccelerateDecelerateInterpolator 在动画开始与结束的地方速率改变比较慢，在中间的时候加速

   AccelerateInterpolator  在动画开始的地方速率改变比较慢，然后开始加速

   AnticipateInterpolator 开始的时候向后然后向前甩

   AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值

   BounceInterpolator   动画结束的时候弹起

   CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线

   DecelerateInterpolator 在动画开始的地方快然后慢

   LinearInterpolator   以常量速率改变

   OvershootInterpolator    向前甩一定值后再回到原来位置



   keyFrame是一个 时间/值 对，通过它可以定义一个在特定时间的特定状态，即关键帧，而且在两个keyFrame之间可以定义不同的Interpolator，就好像多个动画的拼接，第一个动画的结束点是第二个动画的开始点。KeyFrame是抽象类，要通过ofInt(),ofFloat(),ofObject()获得适当的KeyFrame，然后通过PropertyValuesHolder.ofKeyframe获得PropertyValuesHolder对象，如以下例子：
   [java] view plain copy print?在CODE上查看代码片派生到我的代码片
   Keyframe kf0 = Keyframe.ofInt(0, 400);
   Keyframe kf1 = Keyframe.ofInt(0.25f, 200);
   Keyframe kf2 = Keyframe.ofInt(0.5f, 400);
   Keyframe kf4 = Keyframe.ofInt(0.75f, 100);
   Keyframe kf3 = Keyframe.ofInt(1f, 500);
   PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("width", kf0, kf1, kf2, kf4, kf3);
   ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(btn2, pvhRotation);
   rotationAnim.setDuration(2000);
   上述代码的意思为：设置btn对象的width属性值使其：

   开始时 Width=400
   动画开始1/4时 Width=200
   动画开始1/2时 Width=400
   动画开始3/4时 Width=100
   动画结束时 Width=500
   第一个参数为时间百分比，第二个参数是在第一个参数的时间时的属性值。
   定义了一些Keyframe后，通过PropertyValuesHolder类的方法ofKeyframe一个PropertyValuesHolder对象，然后通过ObjectAnimator.ofPropertyValuesHolder获得一个Animator对象。
   用下面的代码可以实现同样的效果（上述代码时间值是线性，变化均匀）：
   [java] view plain copy print?在CODE上查看代码片派生到我的代码片
   ObjectAnimator oa=ObjectAnimator.ofInt(btn2, "width", 400,200,400,100,500);
   oa.setDuration(2000);
   oa.start();
   * 整个抛物线
   */
  public static void startAnimation(final View view) {
    mCount = 300;
    Keyframe[] keyframes = new Keyframe[mCount];
    final float keyStep = 1f / (float) mCount;
    float key = keyStep;
    for (int i = 0; i < mCount; ++i) {
      keyframes[i] = Keyframe.ofFloat(key, i + 1);
      key += keyStep;
    }

    PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
    key = keyStep;
    for (int i = 0; i < mCount; ++i) {
      keyframes[i] = Keyframe.ofFloat(key, -getY(i + 1));
      key += keyStep;
    }

    PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes);
    ObjectAnimator yxBouncer =
        ObjectAnimator.ofPropertyValuesHolder(view, pvhY, pvhX).setDuration(3000);
    yxBouncer.setInterpolator(new BounceInterpolator());
    yxBouncer.start();
  }
  /**PropertyValuesHolder这个类可以先将动画属性和值暂时的存储起来，后一起执行，在有些时候可以使用替换掉AnimatorSet，减少代码量*/
  //半个抛物线，从上向下
  public static void starthalfAnimation(final View view) {
    mCount = 150;
    Keyframe[] keyframes = new Keyframe[mCount];
    final float keyStep = 1f / (float) mCount;
    float key = keyStep;
    for (int i = 0; i < mCount; ++i) {
      keyframes[i] = Keyframe.ofFloat(key, i + 150);
      key += keyStep;
    }

    PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
    key = keyStep;
    for (int i = 0; i < mCount; ++i) {
      keyframes[i] = Keyframe.ofFloat(key, -getY(i + 150));
      key += keyStep;
    }

    PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes);
    /**PropertyValuesHolder这个类可以先将动画属性和值暂时的存储起来，后一起执行，在有些时候可以使用替换掉AnimatorSet，减少代码量*/
    ObjectAnimator yxBouncer =
        ObjectAnimator.ofPropertyValuesHolder(view, pvhY, pvhX).setDuration(3000);
    yxBouncer.setInterpolator(new BounceInterpolator());
    yxBouncer.start();
  }

  /**
   * 围绕x轴旋转
   */
  public static void startRotationXAnimation(final View view) {
    ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 260.0F).setDuration(800).start();
    ;
  }

  /**
   * 围绕x轴旋转
   */
  public static void startRotationYAnimation(final View view) {
    ObjectAnimator.ofFloat(view, "rotationY", 260.0F).setDuration(800).start();
    ;
  }

  /**
   * 放大
   */
  public static void startRotateBigAnimation(final View view) {
    ObjectAnimator animator =
        ObjectAnimator.ofFloat(view,/* "scaleX"*/"", 0f, 1.0f).setDuration(800);
    ;
    animator.start();
    animator.addUpdateListener(new AnimatorUpdateListener() {

      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float cVal = (Float) animation.getAnimatedValue();
        view.setAlpha(cVal);
        view.setScaleX(cVal);
        view.setScaleY(cVal);
      }
    });
  }

  //缩小
  public static void startRotateSmallAnimation(final View view) {
    ObjectAnimator animator =
        ObjectAnimator.ofFloat(view, /*"scaleY"*/"", 1.0f, 0.0f).setDuration(1000);
    ;
    animator.start();
    animator.addUpdateListener(new AnimatorUpdateListener() {

      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float cVal = (Float) animation.getAnimatedValue();
        view.setAlpha(cVal);
        view.setScaleX(cVal);
        view.setScaleY(cVal);
      }
    });
  }

  /**
   * 放大效果
   */
  public static void propertyValuesHolder(View view) {
    PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
    PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
    PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
    ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
  }

  //垂直动画
  public static void verticalAnimation(View view) {
    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("translationY", 0.0f, 180f);
    ObjectAnimator.ofPropertyValuesHolder(view, holderY).setDuration(2000).start();
  }

  //水平动画
  public static void HorizontalAnimation(View view) {
    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("translationX", 0.0f, 180f);
    ObjectAnimator.ofPropertyValuesHolder(view, holderY).setDuration(2000).start();
  }

  //也是抛物线
  public static void parabolaAnimation(View view) {
    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("translationY", 0.0f, 180f);
    PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("translationX", 0.0f, 180f);
    ObjectAnimator.ofPropertyValuesHolder(view, holderX, holderY).setDuration(2000).start();
  }

  /**
   * 自由落体
   */
  public static void verticalRun(final View view, Activity context) {
    /**
     *           dm = new DisplayMetrics();
     getWindowManager().getDefaultDisplay().getMetrics(dm);
     //获得手机的宽带和高度像素单位为px
     String str = "手机屏幕分辨率为:" + dm.widthPixels
     +" * "+dm.heightPixels;
     */
    DisplayMetrics metrics = new DisplayMetrics();
    context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    ValueAnimator animator = ValueAnimator.ofFloat(0, metrics.heightPixels / 3 - view.getHeight());
    animator.setTarget(view);
    animator.setDuration(1000).start();
    //      animator.setInterpolator(value)
    animator.addUpdateListener(new AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        view.setTranslationY((Float) animation.getAnimatedValue());
      }
    });
  }

  //Parabola抛物线动画
  public static void ParabolaValueAnimation(final View view) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.addListener(new AnimatorListener() {

      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        /*ViewGroup group = (ViewGroup) view.getParent();
				if (group != null) {
					group.removeView(view);
				}*/
      }

      @Override public void onAnimationCancel(Animator animation) {

      }
    });
    valueAnimator.setDuration(3000);
    valueAnimator.setObjectValues(new PointF(0, 0));
    valueAnimator.setInterpolator(new BounceInterpolator());
    valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
      /**
       *
       * @param fraction  从0。0 -  1.0
       * @param startValue
       * @param endValue
       * @return
       */
      @Override public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF pointF = new PointF();
        Log.e("point", fraction + "");
        pointF.x = 200 * fraction * 3 / 2;
        pointF.y = 0.5f * 200 * (fraction * 3) * (fraction * 3) / 2;
        return pointF;
      }
    });

    valueAnimator.start();
    valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

      @Override public void onAnimationUpdate(ValueAnimator animation) {
        PointF pointF = (PointF) animation.getAnimatedValue();
        view.setTranslationX(pointF.x);
        view.setTranslationY(pointF.y);
      }
    });
  }

  public static final float a = -1f / 75f;

  /**
   * 这里是根据三个坐标点{（0,0），（300,0），（150,300）}计算出来的抛物线方程
   */
  public static float getY(float x) {
    if (x > 300) {
      x = 300;
    }
    return a * x * x + 4 * x;
  }
}
