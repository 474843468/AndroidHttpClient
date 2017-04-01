package com.psi.androidhttpclient.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by zjq on 2016/5/3.
 */
public class ApplicationContext extends Application {

  private static Context mContext;
  //private static User mUser;
  private static ApplicationContext instance;
  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    mContext = getApplicationContext();
    initLogger();
    //initDb();
    initStetho();
  }
 public static ApplicationContext getInstance(){
   return instance;
 }
  // @formatter:on
  public static Context getContext() {
    return mContext;
  }

  /**
   * 初始化数据库
   */
  //private void initDb() {
  //  DbCore.init(getApplicationContext());
  //  DbCore.update(getApplicationContext());
  //}

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
  /**
   * 初始化Stetho
   */
  private void initStetho() {
    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
  }

  /**
   * Logger
   */
  private void initLogger() {
    Logger.init("FireLog").setMethodCount(3).hideThreadInfo().setLogLevel(LogLevel.FULL);
  }



  //public User getUser() {
  //  if (mUser == null) {
  //    String userId = (String) SPUtils.get(this, Setting.LOGIN_USER, "");
  //    if (!TextUtils.isEmpty(userId)) {
  //      mUser = DaoServiceUtil.getUserService()
  //          .queryBuilder()
  //          .where(UserDao.Properties.ObjectId.eq(userId))
  //          .unique();
  //    }
  //  }
  //  return mUser;
  //}
  //
  //public void setUser(User user) {
  //  mUser = user;
  //}
}
