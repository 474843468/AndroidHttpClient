package com.psi.androidhttpclient.adapter;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017-05-02.
 */
public interface GankApi {
  @GET("data/福利/{number}/{page}")
  Observable<GankBeautyResult> getBeauties(@Path("number") int number, @Path("page") int page);
}
