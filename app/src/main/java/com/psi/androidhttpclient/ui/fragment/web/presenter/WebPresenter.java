//package com.psi.androidhttpclient.ui.fragment.web.presenter;
//
//import com.psi.androidhttpclient.ui.fragment.RxPresenter;
//import com.psi.androidhttpclient.ui.fragment.rx.SchedulersCompat;
//import com.psi.androidhttpclient.ui.fragment.rx.lifecycle.RxLifecycle;
//import com.psi.androidhttpclient.ui.fragment.web.ui.WebContract;
//import java.util.concurrent.TimeUnit;
//import rx.Observable;
//import rx.functions.Func1;
//
//public class WebPresenter extends RxPresenter implements WebContract.Presenter {
//
//    private WebContract.View mWebView;
//    protected GlobalService mGlobalService;
//
//    public WebPresenter(WebContract.View view) {
//        mWebView = view;
//        mGlobalService = new GlobalService();
//    }
//
//    @Override
//    public void qryHeartBeat(final int heartBeatPeriod) {
//        Observable.interval(0, heartBeatPeriod, TimeUnit.SECONDS)
//                  .flatMap(new Func1<Long, Observable<String>>() {
//                      @Override
//                      public Observable<String> call(Long aLong) {
//                          return mGlobalService.psnCommonQueryOprIp(
//                                  new PsnCommonQueryOprIpParams());
//                      }
//                  })
//                  .compose(this.<String>bindUntilEvent(RxLifecycle.Event.BGTASK_DESTROY))
//                  .compose(SchedulersCompat.<String>applyIoSchedulers())
//                  .subscribe(new BIIBaseSubscriber<String>() {
//
//                      @Override
//                      public void commonHandleException(BiiResultErrorException biiResultErrorException) {
//                      }
//
//                      @Override
//                      public void handleException(BiiResultErrorException biiResultErrorException) {
//                            qryHeartBeat(heartBeatPeriod);
//                      }
//
//                      @Override
//                      public void onCompleted() {
//
//                      }
//
//                      @Override
//                      public void onNext(String s) {
//
//                      }
//                  });
//    }
//}