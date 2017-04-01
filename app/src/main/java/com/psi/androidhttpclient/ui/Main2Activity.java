package com.psi.androidhttpclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.orhanobut.logger.Logger;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.utils.ToastUtils;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;

public class Main2Activity extends BaseToolbarActivity1 {
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
    Button s1 = (Button) findViewById(R.id.socketio__1);
    s1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        socketio("c", "123456", "/productList");
      }
    });
    Button s2 = (Button) findViewById(R.id.socketio__2);
    Button s3 = (Button) findViewById(R.id.socketio__3);
    Button s4 = (Button) findViewById(R.id.socketio__4);
    mContentView = (RelativeLayout) findViewById(R.id.content);
    initBottomSheet();
    s2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        startActivity(intent);
      }
    });
  }

  public void materialSearchView(View v) {
    Intent intent = new Intent(Main2Activity.this, SelectedTableModifyBillActivity.class);
    startActivity(intent);
  }

  public void actionbar_menu(View v) {
    Intent intent = new Intent(Main2Activity.this, Main5Activity.class);
    startActivity(intent);
  }

  public void actionbar_search(View v) {
    Intent intent = new Intent(Main2Activity.this, Main6Activity.class);
    startActivity(intent);
  }

  public void activity7(View v) {
    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
    startActivity(intent);
  }

  public void activity8(View v) {
    Intent intent = new Intent(Main2Activity.this, Main6Activity.class);
    startActivity(intent);
  }

  public void activity9(View v) {
    Intent intent = new Intent(Main2Activity.this, Main6Activity.class);
    startActivity(intent);
  }

  public void activity10(View v) {
    Intent intent = new Intent(Main2Activity.this, Main6Activity.class);
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
