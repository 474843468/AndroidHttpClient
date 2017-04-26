package com.psi.androidhttpclient.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.ui.fragment.FragmentA;
import com.psi.androidhttpclient.ui.fragment.FragmentB;
import com.psi.androidhttpclient.ui.fragment.FragmentC;
import com.psi.androidhttpclient.ui.fragment.FragmentD;
import com.psi.androidhttpclient.ui.fragment.FragmentE;
import eu.long1.spacetablayout.SpaceTabLayout;
import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {
  SpaceTabLayout tabLayout;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main5);
    //add the fragments you want to display in a List
    List<Fragment> fragmentList = new ArrayList<>();
    fragmentList.add(new FragmentA());
    fragmentList.add(new FragmentB());
    fragmentList.add(new FragmentC());
    fragmentList.add(new FragmentD());
    fragmentList.add(new FragmentE());

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
    tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

    //we need the savedInstanceState to retrieve the position
    tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, savedInstanceState);
  }


  //we need the outState to memorize the position
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    tabLayout.saveState(outState);
    super.onSaveInstanceState(outState);
  }
  //初始化菜单
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // 添加OptionsMenu菜单项
        /* menu.add(groupId, itemId, order, title)
         * groupId:菜单项所在的组
         * itemId:菜单项编号
         * order:排序
         * title：标题
         * setIcon()方法为菜单设置图标，这里使用的是系统自带的图标，同学们留意一下,
         * 以 android.R开头的资源是系统提供的，我们自己提供的资源是以R开头的  */

    menu.add(0, 1, Menu.NONE, "蓝牙发送").setIcon(android.R.drawable.ic_menu_send);

    //添加子菜单
    SubMenu subMenu = menu.addSubMenu(0,2,Menu.NONE, "重要程度>>").setIcon(android.R.drawable.ic_menu_share);
    //添加子菜单项
    subMenu.add(2, 201, 1, "☆☆☆☆☆");
    subMenu.add(2, 202, 2, "☆☆☆");
    subMenu.add(2, 203, 3, "☆");

    menu.add(0, 3, Menu.NONE, "重命名").setIcon(android.R.drawable.ic_menu_edit);
    menu.add(0, 4, Menu.NONE, "删除").setIcon(android.R.drawable.ic_menu_close_clear_cancel);



    return true;
  }

  //根据菜单执行相应内容
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

      case 1:
        Toast.makeText(getApplicationContext(), "蓝牙发送……", Toast.LENGTH_SHORT).show();
        return true;

      case 201:
        Toast.makeText(getApplicationContext(), "非常重要：☆☆☆☆☆", Toast.LENGTH_SHORT).show();
        return true;
      case 202:
        Toast.makeText(getApplicationContext(), "重要：☆☆☆", Toast.LENGTH_SHORT).show();
        return true;
      case 203:
        Toast.makeText(getApplicationContext(), "普通：☆", Toast.LENGTH_SHORT).show();
        return true;

      case 3:
        Toast.makeText(getApplicationContext(), "重命名……", Toast.LENGTH_SHORT).show();
        return true;

      case 4:
        Toast.makeText(getApplicationContext(), "删除……", Toast.LENGTH_SHORT).show();
        return true;

    }
    return false;
  }
}

