package com.psi.androidhttpclient.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.lang.reflect.Field;
import java.util.List;

/**
 * fragment跳转控制类
 * Created by lxw on 2016/6/4.
 */
public class Fragmentation {

  static final String ARG_REQUEST_CODE = "fragmentation_arg_request_code";
  static final String ARG_RESULT_CODE = "fragmentation_arg_result_code";
  static final String ARG_RESULT_BUNDLE = "fragmentation_arg_bundle";
  static final String ARG_IS_ROOT = "fragmentation_arg_is_root";

  public static final long BUFFER_TIME = 300L;

  private BussActivity mActivity;
  private FragmentManager mFragmentManager;
  private int mContainerId;

  // 添加
  public static final int TYPE_ADD = 0;
  // 替换
  public static final int TYPE_ADD_WITH_POP = 1;
  private Handler mHandler;

  /**
   * 构造方法
   */
  public Fragmentation(BussActivity activity, int containerId) {
    this.mActivity = activity;
    mContainerId = containerId;
    this.mFragmentManager = mActivity.getSupportFragmentManager();

    mHandler = mActivity.getHandler();
  }

  /**
   * 分发事务
   */
  public void dispatchStartTransaction(BussFragment from, BussFragment to, int requestCode,
      int launchMode, int type) {
    if (from != null) {
      mFragmentManager = from.getFragmentManager();
    }

    // 移动到popTo 后
    //FragmentTransactionBugFixHack.reorderIndices(mFragmentManager);

    if (type == TYPE_ADD) {
      saveRequestCode(to, requestCode);
    }

    if (handleLaunchMode(to, launchMode)) return;

    // 在SingleTask/SingleTop启动模式之后 开启防抖动
    mActivity.setFragmentClickable(false);

    switch (type) {
      case TYPE_ADD:
        start(from, to);
        break;
      case TYPE_ADD_WITH_POP:
        if (from != null) {
          startWithFinish(from, to);
        } else {
          throw new RuntimeException("startWithPop(): getTopFragment() is null");
        }
        break;
    }
  }

  /**
   * 添加一个fragment并显示该fragment
   */
  void start(BussFragment from, BussFragment to) {
    String toName = to.getClass().getName();
    FragmentTransaction ft = mFragmentManager.beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .add(mContainerId, to, toName);

    if (from != null) {
      ft.hide(from);
    } else {
      Bundle bundle = to.getArguments();
      bundle.putBoolean(ARG_IS_ROOT, true);
    }

    ft.addToBackStack(toName);
    boolean tmp = tmp(mFragmentManager);
    if (tmp) {
      ft.commitAllowingStateLoss();
    } else {
      ft.commit();
    }
  }

  private boolean tmp(FragmentManager mFragmentManager) {
    try {
      Field mStateSavedField = mFragmentManager.getClass().getDeclaredField("mStateSaved");
      mStateSavedField.setAccessible(true);
      return mStateSavedField.getBoolean(mFragmentManager);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 替换顶层的fragment
   */
  void startWithFinish(BussFragment from, BussFragment to) {
    BussFragment preFragment = getPreFragment(from);
    if (preFragment != null) {
      handlerFinish(preFragment, from, to);
    }
    passSaveResult(from, to);

    mFragmentManager.beginTransaction().remove(from).commit();
    mFragmentManager.popBackStack();

    String toName = to.getClass().getName();
    FragmentTransaction ft = mFragmentManager.beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .add(mContainerId, to, toName)
        .addToBackStack(toName);

    if (preFragment != null) {
      ft.hide(preFragment);
    }
    ft.commit();
  }

  /**
   * fix anim
   */
  @Nullable private void handlerFinish(BussFragment preFragment, BussFragment from,
      BussFragment to) {
    View view = preFragment.getView();
    if (view != null) {
      // 不调用 会闪屏
      view.setVisibility(View.VISIBLE);

      ViewGroup viewGroup;
      final View fromView = from.getView();

      if (fromView != null && view instanceof ViewGroup) {
        viewGroup = (ViewGroup) view;
        ViewGroup container = (ViewGroup) mActivity.findViewById(mContainerId);
        if (container != null) {
          container.removeView(fromView);
          if (fromView.getLayoutParams().height != ViewGroup.LayoutParams.MATCH_PARENT) {
            fromView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
          }

          if (viewGroup instanceof LinearLayout) {
            viewGroup.addView(fromView, 0);
          } else {
            viewGroup.addView(fromView);
          }

          final ViewGroup finalViewGroup = viewGroup;
          to.setEnterAnimEndListener(new OnEnterAnimEndListener() {
            @Override public void onAnimationEnd() {
              finalViewGroup.removeView(fromView);
            }
          });
        }
      }
    }
  }

  /**
   * pass on Result
   */
  private void passSaveResult(BussFragment from, BaseFragment to) {
    saveRequestCode(to, from.getRequestCode());
    Bundle bundle = to.getArguments();
    bundle.putInt(ARG_RESULT_CODE, from.getResultCode());
    bundle.putBundle(ARG_RESULT_BUNDLE, from.getResultBundle());
  }

  /**
   * 获取目标Fragment的前一个Fragment
   *
   * @param fragment 目标Fragment
   */
  BussFragment getPreFragment(BussFragment fragment) {
    List<Fragment> fragmentList = fragment.getFragmentManager().getFragments();
    if (fragmentList == null) return null;

    int index = fragmentList.indexOf(fragment);
    for (int i = index - 1; i >= 0; i--) {
      Fragment preFragment = fragmentList.get(i);
      if (preFragment instanceof BaseFragment) {
        return (BussFragment) preFragment;
      }
    }
    return null;
  }

  /**
   * save requestCode
   */
  private void saveRequestCode(Fragment to, int requestCode) {
    Bundle bundle = to.getArguments();
    if (bundle == null) {
      bundle = new Bundle();
      to.setArguments(bundle);
    }
    bundle.putInt(ARG_REQUEST_CODE, requestCode);
  }

  /**
   * 处理启动方式
   * handle LaunchMode
   */
  private boolean handleLaunchMode(Fragment to, int launchMode) {
    if (launchMode == BussFragment.SINGLETOP) {
      List<Fragment> fragments = mFragmentManager.getFragments();
      int index = fragments.indexOf(to);
      // 在栈顶
      if (index == mFragmentManager.getBackStackEntryCount() - 1) {
        if (handleNewBundle(to)) return true;
      }
    } else if (launchMode == BussFragment.SINGLETASK) {
      popToFix(to, 0, mFragmentManager);
      if (handleNewBundle(to)) return true;
    }
    return false;
  }

  private boolean handleNewBundle(Fragment to) {
    if (to instanceof BussFragment) {
      BussFragment supportTo = (BussFragment) to;
      Bundle newBundle = supportTo.getNewBundle();
      supportTo.onNewBundle(newBundle);
      return true;
    }
    return false;
  }

  /**
   * 解决以singleTask或singleTop模式start时,pop多个fragment时动画引起的异常问题
   */
  private void popToFix(Fragment targetFragment, int flag, final FragmentManager fragmentManager) {
    fragmentManager.popBackStackImmediate(targetFragment.getClass().getName(), flag);

    long popAniDuration;

    if (targetFragment instanceof BussFragment) {
      BussFragment fragment = (BussFragment) targetFragment;
      popAniDuration =
          Math.max(fragment.getPopEnterAnimDuration(), fragment.getPopExitAnimDuration());
    } else {
      popAniDuration = BUFFER_TIME;
    }

    mHandler.postDelayed(new Runnable() {
      @Override public void run() {
        //FragmentTransactionBugFixHack.reorderIndices(fragmentManager);
      }
    }, popAniDuration);
  }

  /**
   * 获得栈顶SupportFragment
   */
  public BussFragment getTopFragment(FragmentManager fragmentManager) {
    List<Fragment> fragmentList = fragmentManager.getFragments();
    if (fragmentList == null) return null;

    for (int i = fragmentList.size() - 1; i >= 0; i--) {
      Fragment fragment = fragmentList.get(i);
      if (fragment instanceof BussFragment) {
        return (BussFragment) fragment;
      }
    }
    return null;
  }

  /**
   * 返回
   */
  public void back(FragmentManager fragmentManager) {
    int count = fragmentManager.getBackStackEntryCount();

    if (count > 1) {
      handleBack(fragmentManager);
    }
  }

  /**
   * 出栈到目标fragment
   *
   * @param fragmentClass 目标fragment
   * @param includeSelf 是否包含该fragment
   */
  void popTo(Class<?> fragmentClass, boolean includeSelf, Runnable afterPopTransactionRunnable,
      FragmentManager fragmentManager) {
    BussFragment targetFragment =
        (BussFragment) fragmentManager.findFragmentByTag(fragmentClass.getName());
    if (includeSelf) {
      targetFragment = getPreFragment(targetFragment);
      if (targetFragment == null) {
        throw new RuntimeException(
            "Do you want to pop all Fragments? Please call mActivity.finish()");
      }
    }
    BussFragment fromFragment = getTopFragment(fragmentManager);

    int flag = includeSelf ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0;

    if (afterPopTransactionRunnable != null) {
      if (targetFragment == fromFragment) {
        mHandler.post(afterPopTransactionRunnable);
        return;
      }

      fixPopToAnim(targetFragment, fromFragment);
      fragmentManager.beginTransaction().remove(fromFragment).commit();
      popToWithTransactionFix(fragmentClass, flag, fragmentManager);
      mHandler.post(afterPopTransactionRunnable);
    } else {
      popToFix(targetFragment, flag, fragmentManager);
    }
  }

  /**
   * 返回处理
   * handle result
   */
  private void handleBack(final FragmentManager fragmentManager) {
    List<Fragment> fragmentList = fragmentManager.getFragments();
    int count = 0;
    int requestCode = 0, resultCode = 0;
    long lastAnimTime = 0;
    Bundle data = null;

    for (int i = fragmentList.size() - 1; i >= 0; i--) {
      Fragment fragment = fragmentList.get(i);
      if (fragment instanceof BussFragment) {
        final BussFragment bussFragment = (BussFragment) fragment;
        if (count == 0) {
          // TODO
          requestCode = bussFragment.getRequestCode();
          resultCode = bussFragment.getResultCode();
          data = bussFragment.getResultBundle();

          lastAnimTime = bussFragment.getExitAnimDuration();

          count++;
        } else {

          if (requestCode != 0 && resultCode != 0) {
            final int finalRequestCode = requestCode;
            final int finalResultCode = resultCode;
            final Bundle finalData = data;

            long animTime = bussFragment.getPopEnterAnimDuration();

            fragmentManager.popBackStackImmediate();

            mHandler.postDelayed(new Runnable() {
              @Override public void run() {
                bussFragment.onFragmentResult(finalRequestCode, finalResultCode, finalData);
              }
            }, Math.max(animTime, lastAnimTime));
            return;
          }
          break;
        }
      }
    }
    fragmentManager.popBackStackImmediate();
  }

  /**
   * find Fragment from FragmentStack
   */
  @SuppressWarnings("unchecked") <T extends BussFragment> T findStackFragment(
      Class<T> fragmentClass, FragmentManager fragmentManager, boolean isChild) {
    Fragment fragment = null;
    if (isChild) {
      // 如果是 查找子Fragment,则有可能是在FragmentPagerAdapter/FragmentStatePagerAdapter中,这种情况下,
      // 它们的Tag是以android:switcher开头,所以这里我们使用下面的方式
      List<Fragment> childFragmentList = fragmentManager.getFragments();
      if (childFragmentList == null) return null;

      for (int i = childFragmentList.size() - 1; i >= 0; i--) {
        Fragment childFragment = childFragmentList.get(i);
        if (childFragment != null && childFragment.getClass()
            .getName()
            .equals(fragmentClass.getName())) {
          fragment = childFragment;
          break;
        }
      }
    } else {
      fragment = fragmentManager.findFragmentByTag(fragmentClass.getName());
    }
    if (fragment == null) {
      return null;
    }
    return (T) fragment;
  }

  /**
   * 动画结束 监听
   * Created by YoKeyword on 16/1/28.
   */
  public interface OnEnterAnimEndListener {
    void onAnimationEnd();
  }

  /**
   * 解决popTo多个fragment时动画引起的异常问题
   */
  private void popToWithTransactionFix(Class<?> fragmentClass, int flag,
      final FragmentManager fragmentManager) {

    mActivity.preparePopMultiple();
    fragmentManager.popBackStackImmediate(fragmentClass.getName(), flag);
    mActivity.popFinish();

  mHandler.post(new Runnable() {
      @Override public void run() {
       // FragmentTransactionBugFixHack.reorderIndices(fragmentManager);
      }
    });
  }

  /**
   * fix popTo anim
   */
  @Nullable private void fixPopToAnim(Fragment rootFragment, BussFragment fromFragment) {
    if (rootFragment != null) {
      View view = rootFragment.getView();
      if (view != null) {
        // 不调用 会闪屏
        view.setVisibility(View.VISIBLE);

        ViewGroup viewGroup;
        final View fromView = fromFragment.getView();

        if (fromView != null && view instanceof ViewGroup) {
          viewGroup = (ViewGroup) view;
          ViewGroup container = (ViewGroup) mActivity.findViewById(mContainerId);
          if (container != null) {
            container.removeView(fromView);
            if (fromView.getLayoutParams().height != ViewGroup.LayoutParams.MATCH_PARENT) {
              fromView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            }

            if (viewGroup instanceof LinearLayout) {
              viewGroup.addView(fromView, 0);
            } else {
              viewGroup.addView(fromView);
            }

            final ViewGroup finalViewGroup = viewGroup;
            mHandler.postDelayed(new Runnable() {
              @Override public void run() {
                finalViewGroup.removeView(fromView);
              }
            }, Math.max(fromFragment.getExitAnimDuration(), BUFFER_TIME));
          }
        }
      }
    }
  }
}
