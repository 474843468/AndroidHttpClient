package com.psi.androidhttpclient.ui.fragment;

/**
 * 模块工厂类
 * Created by lxw on 2016/7/22 0022.
 */
public interface ModuleFactory {

    /**
     * 根据模块ID，返回fragment实例
     * @param moduleID
     * @return
     */
    BussFragment getModuleFragmentInstance(String moduleID);
}
