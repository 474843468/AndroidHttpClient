package com.psi.androidhttpclient.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.utils.ToastUtils;

public abstract class BaseToolbarActivity1 extends BaseActivity1 {
  abstract protected String provideToolbarTitle();

  protected AppBarLayout mAppBar;
  protected Toolbar mToolbar;
  protected boolean mIsHidden = false;
  private ActionBar mActionBar;
  protected boolean mIsCanBack = true;
  protected boolean mIsHasToolbarTitle = true;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    if (mToolbar == null || mAppBar == null) {
      throw new IllegalStateException("No toolbar");
    }
    if (mIsHasToolbarTitle) {
      mToolbar.setTitle(provideToolbarTitle());
    }
    mToolbar.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onToolbarClick();
      }
    });
//toobar代替anctionBar
    setSupportActionBar(mToolbar);
    mActionBar = getSupportActionBar();

    if (mIsCanBack) {
      if (mActionBar != null) {
        /**
         * setHomeButtonEnabled这个小于4.0版本是默认为true的。该方法的作用：决定左上角的图标是否可以点击。没有向左的小图标。 true 图标可以点击 false 不可以点击。
         actionBar.setDisplayHomeAsUpEnabled(true) // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
         actionBar.setDisplayShowHomeEnabled(true) //使左上角图标可点击，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOM
         actionBar.setDisplayShowCustomEnabled(true) // 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用，对应ActionBar.DISPLAY_SHOW_CUSTOM
         actionBar.setDisplayShowTitleEnabled(true) //对应ActionBar.DISPLAY_SHOW_TITLE
         */
        mActionBar.setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
          mActionBar.setHomeAsUpIndicator(R.mipmap.ic_toolbar_return);
       //坑爹的谷歌加了一个默认效果，还以为设置为null就不显示了，结果正好相反，nnd
        // mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.md_transparent));
      }
    }

    if (Build.VERSION.SDK_INT >= 21) {
      mAppBar.setElevation(10.6f);
    }
    hideOrShowToolbar();
    setAppBarAlpha(0.3f);
  }

  public void onToolbarClick() {
  //  ToastUtils.showShort(this,"onToolbarClick");
  }
  //当客户点击MENU按钮的时候，调用该方法

  /**
   *     看一看menu.add方法的参数：
   第一个int类型的group ID参数，代表的是组概念，你可以将几个菜单项归为一组，以便更好的以组的方式管理你的菜单按钮。
   第二个int类型的item ID参数，代表的是项目编号。这个参数非常重要，一个item ID对应一个menu中的选项。在后面使用菜单的时候，就靠这个item ID来判断你使用的是哪个选项。
   第三个int类型的order ID参数，代表的是菜单项的显示顺序。默认是0，表示菜单的显示顺序就是按照add的显示顺序来显示。
   第四个String类型的title参数，表示选项中显示的文字。
   * @param menu
   * @return
   */

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add("---------标题--------");
    menu.add(0, 1, 1, R.string.exit).setIcon(R.mipmap.ic_launcher);
    menu.add(1,1,1,R.string.about111).setIcon(R.mipmap.ic_add_prod);
    menu.add(1,2,1,R.string.about121);

    menu.add(0,2,2,R.string.about);
    menu.add(0,1,2,R.string.about012);
    menu.add(1,3,2,R.string.about132);
    menu.add(3,1,2,R.string.about312);
    menu.add(1,2,2,R.string.about122);
    return super.onCreateOptionsMenu(menu);
  }
  //当客户点击菜单当中的某一个选项时，会调用该方法
  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == 1){
      ToastUtils.showShort(this,"11");
      return true;
    }else if (item.getItemId() == android.R.id.home) {
      ToastUtils.showShort(this,item.getItemId()+"");
      onBackPressed();
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  protected void setAppBarAlpha(float alpha) {
    mAppBar.setAlpha(alpha);
  }

  protected void hideOrShowToolbar() {
    mAppBar.animate()
        .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
        .setInterpolator(new DecelerateInterpolator(2))
        .start();

    mIsHidden = !mIsHidden;
  }
}
