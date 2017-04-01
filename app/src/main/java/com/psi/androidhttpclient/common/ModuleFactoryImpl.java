package com.psi.androidhttpclient.common;

import com.psi.androidhttpclient.ui.fragment.BussFragment;
import com.psi.androidhttpclient.ui.fragment.ModuleFactory;
import java.util.HashMap;

/**
 * Created by Administrator on 2017-04-01.
 */
public class ModuleFactoryImpl implements ModuleFactory {

  private static HashMap<String, Class<? extends BussFragment>> moduleMap = new HashMap();

  static {

    // 账户管理
  //  moduleMap.put(ModuleCode.MODULE_ACCOUNT_0000, OverviewFragment.class);
  }



  //根据模块ID，返回fragment实例
  @Override public BussFragment getModuleFragmentInstance(String moduleID) {
    BussFragment fragment = null;
    try {
      fragment = moduleMap.get(moduleID).newInstance();
    } catch (Exception ex) {

    }
    return fragment;
  }
}
