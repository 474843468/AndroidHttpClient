package com.psi.androidhttpclient.utils;

import com.psi.androidhttpclient.adapter.FakeApi;
import com.psi.androidhttpclient.adapter.GankApi;
import com.psi.androidhttpclient.adapter.ZhuangbiApi;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class Network1 {
  private static ZhuangbiApi zhuangbiApi;
  private static GankApi gankApi;
  private static FakeApi fakeApi;
  private static OkHttpClient okHttpClient = new OkHttpClient();
  private static retrofit2.Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
  private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

  public static ZhuangbiApi getZhuangbiApi() {
    if (zhuangbiApi == null) {
      Retrofit retrofit = new Retrofit.Builder()
          .client(okHttpClient)
          .baseUrl("http://www.zhuangbi.info/")
          .addConverterFactory(gsonConverterFactory)
          .addCallAdapterFactory(rxJavaCallAdapterFactory)
          .build();
      zhuangbiApi = retrofit.create(ZhuangbiApi.class);
    }
    return zhuangbiApi;
  }

  public static GankApi getGankApi() {
    if (gankApi == null) {
      Retrofit retrofit = new Retrofit.Builder()
          .client(okHttpClient)
          .baseUrl("http://gank.io/api/")
          .addConverterFactory(gsonConverterFactory)
          .addCallAdapterFactory(rxJavaCallAdapterFactory)
          .build();
      gankApi = retrofit.create(GankApi.class);
    }
    return gankApi;
  }

  public static FakeApi getFakeApi() {
    if (fakeApi == null) {
      fakeApi = new FakeApi();
    }
    return fakeApi;
  }
}
