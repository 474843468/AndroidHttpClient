package com.psi.androidhttpclient.widget.dialogview.loadingDialog;

import android.app.Activity;

/**
 * 全局加载对话框帮助类，帮助生成全局加载对话框
 * Created by feibin on 2016/5/28.
 */
public class LoadingDialogHelper {
    /**
     * 构建全局加载对话框
     * @param activity activity
     * @param message 提示文字
     * @return
     */
    public static GlobalLoadingDialog getLoadingDialog(Activity activity, String message) {
//        GlobalLoadingDialog mLoadingDialog = null;
//        try {
//            mLoadingDialog = new GlobalLoadingDialog(activity);
//            mLoadingDialog.setText(message);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        return getLoadingDialog(activity, message, true);

    }
    
    /**
     * 构建全局加载对话框
     * @param activity activity
     * @param message 提示文字
     * @return
     */
    public static GlobalLoadingDialog getLoadingDialog(Activity activity, String message, boolean flag) {
        GlobalLoadingDialog mLoadingDialog = null;
        try {
            mLoadingDialog = new GlobalLoadingDialog(activity);
            mLoadingDialog.setText(message);
            mLoadingDialog.setCancelable(flag);// 设置是否使用返回键取消对话框的功能，如果使用的话，关闭按钮可见；如果不使用的话，关闭按钮不可见。
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mLoadingDialog;

    }

}
