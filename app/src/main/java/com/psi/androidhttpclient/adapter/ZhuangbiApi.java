package com.psi.androidhttpclient.adapter;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017-05-02.
 */
public interface ZhuangbiApi {
  @GET("search") Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
