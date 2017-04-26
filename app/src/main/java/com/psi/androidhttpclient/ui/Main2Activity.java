package com.psi.androidhttpclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.orhanobut.logger.Logger;
import com.psi.androidhttpclient.ItemTouchHelper.Main10Activity;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.customBehavior.Main13Activity;
import com.psi.androidhttpclient.defineBehaviorFAB.Main9Activity;
import com.psi.androidhttpclient.myCoordinateLayout.Main11Activity;
import com.psi.androidhttpclient.utils.ToastUtils;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;

public class Main2Activity extends BaseToolbarActivity1 {
  {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
  }

  String ss =
      "{goods : [{'id':'17','title':'巧克力','price':'10.00','goodnums':'5'},{'id':'18','title':'青岛9度','price':'4.00','goodnums':'10'}] comments : [{'id':'3','avatar':'http://static.mamtree.com/avatar/default.png','nickname':'小企鹅','score':'4','dateline':'2017-04-01','content':'这个还不错呢','rcontent':'123按时大多数是','images':[]}] plus : 10}";
  private Socket mSocket;
  private Handler handler = new Handler() {
    @Override public void handleMessage(Message msg) {
      switch (msg.what) {
        case 0:
          Logger.e("dddddd");
          break;
      }
    }
  };
  private Emitter.Listener onLogin = new Emitter.Listener() {
    @Override public void call(Object... args) {
      String s = (String) args[0];
      if (!"".equals(s) || s != null) {
        Logger.e("server data : " + s);
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);
      }
    }
  };
  private RelativeLayout mContentView;
  private SweetSheet mSweetSheet;
  private long mLastMenuClickTime;

  @Override protected String provideToolbarTitle() {
    return "下单信息";
  }

  @Override protected int provideContentViewId() {
    return R.layout.activity_main2;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // setContentView(R.layout.activity_main2);
    mContentView = (RelativeLayout) findViewById(R.id.content);
    initBottomSheet();
  }

  public void test1(View v) {
    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    recreate();
    socketio("c", "123456", "/productList");
  }

  public void test2(View v) {
    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    recreate();
  }

  public void main3activity(View v) {
    Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
    startActivity(intent);
  }

  public void main4activity(View v) {
    Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
    startActivity(intent);
  }

  public void Main5Activity(View v) {
    //String device_model = Build.MODEL;
    //Logger.e(device_model+"liujian");
    Intent intent = new Intent(Main2Activity.this, Main5Activity.class);
    startActivity(intent);
  }

  public void material_SearchView(View v) {
    Intent intent = new Intent(Main2Activity.this, SelectedTableModifyBillActivity.class);
    startActivity(intent);
  }

  public void actionbar_search(View v) {

    //Gson gson = new Gson();
    //Bean bean = gson.fromJson(ss, Bean.class);
    //int size = bean.getGoods().size();
    //Logger.e(size+"---liuj");
    Intent intent = new Intent(Main2Activity.this, Main6Activity.class);
    startActivity(intent);
  }

  public void activity7(View v) {
    Intent intent = new Intent(Main2Activity.this, Main7Activity.class);
    startActivity(intent);
  }

  public void activity8(View v) {
    Intent intent = new Intent(Main2Activity.this, Main8Activity.class);
    startActivity(intent);
  }

  public void activity9(View v) {
    Intent intent = new Intent(Main2Activity.this, Main9Activity.class);
    startActivity(intent);
  }

  public void activity10(View v) {
    Intent intent = new Intent(Main2Activity.this, Main10Activity.class);
    startActivity(intent);
  }  public void activity11(View v) {
    Intent intent = new Intent(Main2Activity.this, Main11Activity.class);
    startActivity(intent);
  }
 public void activity12(View v) {
    Intent intent = new Intent(Main2Activity.this, Main12Activity.class);
    startActivity(intent);
  } public void activity13(View v) {
    Intent intent = new Intent(Main2Activity.this, Main13Activity.class);
    startActivity(intent);
  } public void activity14(View v) {
    Intent intent = new Intent(Main2Activity.this, Main14Activity.class);
    startActivity(intent);
  }

  private void socketio(String c, String s1, String s) {

    try {
      // mSocket = IO.socket("http://192.168.1.170:5000");
      mSocket = IO.socket("http://192.168.1.221:9092/?id=user1");
    } catch (URISyntaxException e) {
      Logger.e(e.toString());
      //  throw new RuntimeException(e);
    }
    mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e(args.toString() + "---");
      }
    });
    mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e(args.toString() + "---");
      }
    });
    mSocket.connect();
    //设置数据
    mSocket.emit("login", s1);
    //连接服务
    mSocket.on("login", onLogin);
  }

  //取消连接Server
  private void disConnect(Socket mSocket) {
    mSocket.disconnect();
    mSocket.off(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e(args.toString() + "---");
      }
    });
    mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e(args.toString() + "---");
      }
    });
  }

  @Override protected void onDestroy() {
    if (mSocket != null) {
      mSocket.off("login", onLogin);
      Logger.e("mSocket!=null");
      disConnect(mSocket);
    }
    super.onDestroy();
  }

  /**
   * 设置BottomSheet
   */
  private void initBottomSheet() {
    mSweetSheet = new SweetSheet(mContentView);
    mSweetSheet.setMenuList(R.menu.bill_cart_sheet);
    mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
    mSweetSheet.setBackgroundEffect(new DimEffect(4));
    mSweetSheet.setBackgroundClickEnable(false);
    mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
      @Override public boolean onItemClick(int position, MenuEntity menuEntity) {
        return menuClickEvent(position, menuEntity);
      }
    });
  }

  private boolean menuClickEvent(int position, MenuEntity menuEntity) {
    switch (position) {
      case 0:
        ToastUtils.showShort(Main2Activity.this, position + "");
        break;
      case 1:
        ToastUtils.showShort(Main2Activity.this, position + "");
        break;
    }
    return true;
  }

  /**
   * OptionMenu
   */
  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.bill_cart_menu, menu);
    MenuItem menuPopup = menu.findItem(R.id.action_popup);
    menuPopup.setActionView(R.layout.layout_menu_popup);
    menuPopup.getActionView().setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (System.currentTimeMillis() - mLastMenuClickTime < 1000) {
          Toast.makeText(Main2Activity.this, "请稍后点击", Toast.LENGTH_SHORT).show();
          return;
        }
        mLastMenuClickTime = System.currentTimeMillis();
        showSheet();
      }
    });
    return true;
  }
  /**
   * 回退
   */
  //@Override public boolean onOptionsItemSelected(MenuItem item) {
  //  if (item.getItemId() == android.R.id.home) {
  //    onBackPressed();
  //    return true;
  //  } else {
  //    return super.onOptionsItemSelected(item);
  //  }
  //}
  //
  ///**
  // * 回退
  // */
  //@Override public void onBackPressed() {
  //  super.onBackPressed();
  //}

  /**
   * 显示Sheet
   */
  private void showSheet() {
    if (mSweetSheet.isShow()) {
      mSweetSheet.dismiss();
    }
    mSweetSheet.toggle();
  }
}
