package com.psi.androidhttpclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.orhanobut.logger.Logger;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.utils.ToastUtils;
import com.psi.androidhttpclient.widget.TagFlowLayout.FlowLayout;
import com.psi.androidhttpclient.widget.TagFlowLayout.TagAdapter;
import com.psi.androidhttpclient.widget.TagFlowLayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main3Activity extends BaseToolbarActivity1 {
  @Bind(R.id.tags_format) TagFlowLayout mFlowLayout;

  @Override protected int provideContentViewId() {
    return R.layout.activity_main3;
  }

  @Override protected String provideToolbarTitle() {
    return getPackageName().toString();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);

    List<String> mVals = new ArrayList<>();
    for (; ; ) {
      mVals.add(System.currentTimeMillis() + "");
      if (mVals.size() > 20) {
        break;
      }
    }

    TagAdapter<String> tagAdapter = new TagAdapter<String>(mVals) {
      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) LayoutInflater.from(Main3Activity.this)
            .inflate(R.layout.item_tags, mFlowLayout, false);
        tv.setText(s);
        return tv;
      }
    };
    //预先设置选中
    tagAdapter.setSelectedList(1, 3, 5, 7, 8, 9);
    mFlowLayout.setAdapter(tagAdapter);
    mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int position, FlowLayout parent) {
        Toast.makeText(Main3Activity.this, position + "", Toast.LENGTH_SHORT).show();
        return true;
      }
    });
    mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
      @Override public void onSelected(Set<Integer> selectPosSet) {
        setTitle("choose:" + selectPosSet.toString());
      }
    });

    //获得所有选中的pos集合
    Set<Integer> selectedList = mFlowLayout.getSelectedList();
    Logger.e(selectedList.size() + "");
  }



  public void zxingscan(View v) {
    Intent intent = new Intent(Main3Activity.this, ScanActivity.class);
    startActivity(intent);
  }

  public void baidumap(View v) {
    Intent intent = new Intent(Main3Activity.this, BaiduMapActivity.class);
    startActivity(intent);
  }

  public void welcomeCoordinator(View v) {
    Intent intent = new Intent(Main3Activity.this, WelcomeActivity.class);
    startActivity(intent);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
     * if it is present.
     */
    getMenuInflater().inflate(R.menu.bill_cart_sheet, menu);
    return true;
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等） TODO
     * Auto-generated method stub
     */
    return super.onPrepareOptionsMenu(menu);
  }

  @Override public void onOptionsMenuClosed(Menu menu) {
    /**
     * 每次菜单被关闭时调用. （菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项） TODO
     * Auto-generated method stub
     */
    super.onOptionsMenuClosed(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getOrder()) {
      case 0:
        ToastUtils.showShort(Main3Activity.this, "0");
        break;
      case 1:
        ToastUtils.showShort(Main3Activity.this, "1");
        break;
    }
    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。 TODO Auto-generated
     * method stub
     */
    return super.onOptionsItemSelected(item);
  }

  //@Override public void onToolbarClick() {
  //  super.onToolbarClick();
  //}

  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
