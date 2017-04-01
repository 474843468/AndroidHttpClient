//package com.psi.androidhttpclient.util1;
//
//import android.os.Looper;
//import android.view.View;
//import com.jakewharton.rxbinding.view.RxView;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import rx.Observable;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action1;
//import rx.functions.Func1;
//
///**
// * 作者：XieDu
// * 创建时间：2016/11/14 22:48
// * 描述：
// */
//public class ViewUtils {
//
//    /**
//     * 设置双击事件
//     *
//     * @param view 被双击的view
//     * @param action 双击事件
//     */
//    public static void doubleClick(final View view, final Action1<View> action) {
//        Observable<Void> observable = RxView.clicks(view).share();//pulish普通的转换为可连接observable  refcount可连接的转换为普通的
//        observable.buffer(observable.debounce(200, TimeUnit.MILLISECONDS))
//            //buffer( )  — 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
//            //debounce过滤操作,只有在空闲了一段时间后才发射数据，通俗的说，就是如果一段时间没有操作，就执行一次操作
//                  .filter(new Func1<List<Void>, Boolean>() {//Filter  — 过滤，过滤掉没有通过谓词测试的数据项，只发射通过测试的
//                      @Override
//                      public Boolean call(List<Void> voids) {
//                          return voids != null && voids.size() >= 2;
//                      }
//                  })
//                  .map(new Func1<List<Void>, View>() {//Map  — 映射，通过对序列的每一项都应用一个函数变换Observable发射的数据，实质是对序列中的每一项执行一个函数，函数的参数就是这个数据项
//                      @Override
//                      public View call(List<Void> voids) {
//                          return view;
//                      }
//                  })
//                  .observeOn(AndroidSchedulers.mainThread())//ObserveOn  — 指定观察者观察Observable的调度程序（工作线程）
//                  .subscribe(action);
//    }
//
//    public static void invalidate(View view){
//        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
//            view.invalidate();//说明：请求重绘View树，即draw()过程，假如视图发生大小没有变化就不会调用layout()过程，并且只绘制那些“需要重绘视图，即谁(View的话，只绘制该View ；ViewGroup，则绘制整个ViewGroup)请求invalidate()方法，就绘制该视图。
//        } else {
//            view.postInvalidate();
//        }
//    }
//}
