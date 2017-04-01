package com.psi.androidhttpclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.message.Event;
import com.psi.androidhttpclient.message.OrderMessage;
import com.psi.androidhttpclient.netWork.AppConfirmOrderDetails;
import com.psi.androidhttpclient.netWork.HttpAddProdReq;
import com.psi.androidhttpclient.netWork.HttpConfirmOrderReq;
import com.psi.androidhttpclient.netWork.HttpModifyBillReq;
import com.psi.androidhttpclient.netWork.HttpRefundProdReq;
import com.psi.androidhttpclient.netWork.HttpServingProdReq;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import cz.msebera.android.httpclient.Header;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class MainActivity1 extends BaseToolbarActivity1 {
  private EditText mEtIp;
  private TextView mTvResponse;
  private Button mBtnSendTest;
  private Button mBtnSendTest2;
  private Button mBtnSendTest3;
  private Button mBtnSendTest4;
  private Button mBtnSendTest5;
  private Button mBtnSendTest6;

  private AsyncHttpClient mAsyncHttpClient;
  private OkHttpClient mOkHttpClient;
  private OkHttpUtils Client;
  private Socket mSocket;

  @Override protected int provideContentViewId() {
    return R.layout.activity_main;
  }

  @Override protected String provideToolbarTitle() {
    return this.getPackageName()+"";
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mAsyncHttpClient = new AsyncHttpClient();

   // setContentView(R.layout.activity_main);
    // mEtIp = (EditText) findViewById(R.id.et_ip);
    mOkHttpClient = new OkHttpClient.Builder()
        //                .addInterceptor(new LoggerInterceptor("TAG"))
        .connectTimeout(10000L, TimeUnit.MILLISECONDS).readTimeout(10000L, TimeUnit.MILLISECONDS)
        //其他配置
        .build();
    Client = new OkHttpUtils(mOkHttpClient);
    OkHttpUtils.initClient(mOkHttpClient);

    mTvResponse = (TextView) findViewById(R.id.tv_response);
    mBtnSendTest = (Button) findViewById(R.id.btn_send_test);
    mBtnSendTest2 = (Button) findViewById(R.id.btn_send_test2);
    mBtnSendTest3 = (Button) findViewById(R.id.btn_send_test3);
    mBtnSendTest4 = (Button) findViewById(R.id.btn_send_test4);
    mBtnSendTest5 = (Button) findViewById(R.id.btn_send_test5);
    mBtnSendTest6 = (Button) findViewById(R.id.btn_send_test6);
    Button mBtnSendTest7 = (Button) findViewById(R.id.btn___7);
    Button mBtn___8 = (Button) findViewById(R.id.btn___8___);
    Button mBtn___9 = (Button) findViewById(R.id.btn___9___);
    Button mBtn___10 = (Button) findViewById(R.id.btn___10___);
    Button mBtn___11 = (Button) findViewById(R.id.btn___11___);

    Button mBtn___12 = (Button) findViewById(R.id.btn___12___);
    Button mBtn___13 = (Button) findViewById(R.id.btn___13___);
    Button mBtn___14 = (Button) findViewById(R.id.btn___14___);
    Button mBtn___15 = (Button) findViewById(R.id.btn___15___);
    Button mBtn___16 = (Button) findViewById(R.id.btn___16___);
    Button mBtn___17 = (Button) findViewById(R.id.btn___17___);
    Button mBtn___18 = (Button) findViewById(R.id.btn___18___);
    Button mBtn___19 = (Button) findViewById(R.id.btn___19___);
    Button mBtn___20 = (Button) findViewById(R.id.btn___20___);

    mBtnSendTest.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        sends("type", "1", "/login");
      }
    });

    mBtnSendTest2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("type", "1", "/tableAreaList");
      }
    });
    mBtnSendTest3.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("tableId", "-1", "/tableList");
      }
    });
    mBtnSendTest4.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("type", "dddd", "/productCategoryList");
      }
    });
    mBtnSendTest5.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        sends("cateId", "4", "/productList");
      }
    });
    mBtnSendTest6.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("prodId", "1", "/productDetailsList");
      }
    });
    mBtnSendTest7.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("tableId", "63e3ab8d01994ce2afc1e6a303b5e568", "/orderInfo");
      }
    });
    mBtn___8.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        HttpServingProdReq req = new HttpServingProdReq();
        req.setIsServing(0);
        req.setObjId("1cf80188817f4813ab95a4c30107afc01489491041729");
        req.setTableId("63e3ab8d01994ce2afc1e6a303b5e568");
        req.setOrderId("195810555920170313181654000005");
        String s = new Gson().toJson(req);
        get2("servingProd", s, "/servingProd");
      }
    });
    mBtn___9.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("optReason", "1dd", "/optReason");
      }
    });
    mBtn___10.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        HttpRefundProdReq body = new HttpRefundProdReq();
        body.setOrderId("195810555920170314193034000001");
        body.setRefundNum(1);
        body.setTableId("03a4e866dbd64ca5ba0348a290c69a7a");
        body.setRefundMultipleNum(0);
        body.setWaiterId("292aece32aa74910ade5a2fc9cf789fc");
        body.setRefundReasonObjId("f52f441f705943548e24ad10c021db67");
        body.setObjId("b0cfddfc052940019203af74d7c484b71489491042904");
        Logger.e(body.toString() + "----------------");
        String s = new Gson().toJson(body);
        sends("refundProd", s, "/refundProd");
      }
    });
    mBtn___11.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        AppConfirmOrderDetails app = new AppConfirmOrderDetails();
        app.setFormatId("3471a43b77db4fd0befa825278e964c5");//小芬
        app.setRemarks("9fecf76a02774dcdbaeca3fa4cb18fc2");//不放蒜
        //app.setMethodId("");
        app.setMultNum(20);
        app.setProdId("9fecf76a02774dcdbaeca3fa4cb18fc2");//"羊肉炒蛋",
        app.setNum(10);
        app.setIsDelay(1);
        List<AppConfirmOrderDetails> appConfirmOrderDetailses = new ArrayList<>();
        appConfirmOrderDetailses.add(app);

        HttpAddProdReq req = new HttpAddProdReq();
        req.setConfirmOrderDetailsList(appConfirmOrderDetailses);
        req.setOrderId("195810555920170315140302000004");
        req.setWaiterId("292aece32aa74910ade5a2fc9cf789fc");
        req.setTableId("63e3ab8d01994ce2afc1e6a303b5e568");
        String s = new Gson().toJson(req);
        sends("addProd", s, "/addProd");
      }
    });
    mBtn___12.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        sends("", "", "/productRemarks");
      }
    });
    mBtn___13.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        HttpModifyBillReq req = new HttpModifyBillReq();
        req.setTableId("a548f587567c430dbe6f18ed3dca5902");//7号桌
        req.setMoveTableId("63e3ab8d01994ce2afc1e6a303b5e568");//3号桌
        req.setWaiterId("292aece32aa74910ade5a2fc9cf789fc");
        req.setRemarks("621b2310a7bc45db86f99001cda11b43");
        req.setPeopleNum(55);
        req.setPromotioInfoId("");
        String s = new Gson().toJson(req);
        sends("modifyBill", s, "/modifyBill");
      }
    });
    mBtn___14.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        AppConfirmOrderDetails app = new AppConfirmOrderDetails();
        app.setFormatId("3471a43b77db4fd0befa825278e964c5");//小芬
        app.setRemarks("不放蒜");
        //app.setMethodId("");
        app.setMultNum(20);
        app.setProdId("9fecf76a02774dcdbaeca3fa4cb18fc2");//"羊肉炒蛋",
        app.setNum(10);
        app.setIsDelay(1);
        List<AppConfirmOrderDetails> appConfirmOrderDetailses = new ArrayList<>();
        appConfirmOrderDetailses.add(app);

        HttpConfirmOrderReq req = new HttpConfirmOrderReq();
        req.setConfirmOrderDetailsList(appConfirmOrderDetailses);
        req.setRemarks("速度");
        req.setPeopleNum(44);
        req.setPromotioInfoId("2d2ba5b75127462f83af6d5e6c48b1ec");
        req.setWaiterId("292aece32aa74910ade5a2fc9cf789fc");
        req.setTableId("604b1981a6c44af5a61cd1d5ea119dca");//2号桌
        String s = new Gson().toJson(req);
        sends("confirmOrder", s, "/confirmOrder");
      }
    });
    mBtn___15.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        sends("padPicture", "padPicture", "/padPicture");
      }
    });
    mBtn___16.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(MainActivity1.this, Main2Activity.class);
        startActivity(intent);
        socketio("cateId", "sssssssssssssssssssssssss", "/productList");
      }
    });
    mBtn___17.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        socketio1("cateId", "4", "/productList");
      }
    });
    mBtn___18.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("cateId", "4", "/productList");
      }
    });
    mBtn___19.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("cateId", "4", "/productList");
      }
    });
    mBtn___20.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        get2("cateId", "4", "/productList");
      }
    });
  }

  private void socketio1(String cateId, String s, String s1) {
    try {
      String authToken = "123";
      IO.Options opts = new IO.Options();
      opts.forceNew = true;
      opts.query = "auth_token=" + authToken;
      mSocket = IO.socket("http://localhost", opts);
      // You can get a callback with Ack when the server received a message:
      mSocket.emit("foo", "woot", new Ack() {
        @Override public void call(Object... args) {
        }
      });
      //  And vice versa:
      // ack from client to server
      mSocket.on("foo", new Emitter.Listener() {
        @Override public void call(Object... args) {
          Ack ack = (Ack) args[args.length - 1];
          ack.call();
        }
      });
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    mSocket.on("foo", new Emitter.Listener() {
      @Override public void call(Object... args) {
        JSONObject obj = (JSONObject) args[0];
      }
    });
  }

  private void socketio2(String cateId, String s, String s1) {

    OrderMessage orderMessage1 = new OrderMessage();
    orderMessage1.setOrderNo("2222222222222222222");
    orderMessage1.setFrom("11");
    orderMessage1.setTo("toUser");
    String s3 = new Gson().toJson(orderMessage1);
    mSocket.emit(Event.CREATE_ORDER,s3);


    OrderMessage orderMessage2 = new OrderMessage();
    orderMessage2.setOrderNo("222222222222222222211");
    orderMessage2.setFrom("111");
    orderMessage2.setTo("toUser");
    String s2 = new Gson().toJson(orderMessage2);
    mSocket.emit(Event.CREATE_ORDER,s2, new Ack() {
      @Override public void call(Object... args) {
        Ack ack = (Ack) args[args.length - 1];
        Logger.e(ack.toString());
        ack.call();

      }
    });

    //  And vice versa:
    // ack from client to server
    mSocket.on(Event.CREATE_ORDER, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Ack ack = (Ack) args[args.length - 1];
        Logger.e(ack.toString());
        ack.call();
      }
    });

  }
  private void socketio(String cateId, String s, String s1) {
    Socket socket = null;
    try {
      socket = IO.socket("http://192.168.1.72:9092/?id=fuwsheng8&r=co000215");
    } catch (URISyntaxException e) {
      e.printStackTrace();
      Logger.e(e.toString());
    }
    //final Socket finalSocket = socket;
    final Socket finalSocket = socket;
    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

      @Override public void call(Object... args) {
      Logger.e("EVENT_CONNECT");
        OrderMessage orderMessage1 = new OrderMessage();
        orderMessage1.setOrderNo("222222222222222222211");
        orderMessage1.setFrom("'fuwsheng8'");
        orderMessage1.setTo("toUser");
        String s2 = new Gson().toJson(orderMessage1);
        Logger.e(s2.toString());
        finalSocket.emit(Event.CREATE_ORDER, s2, new Ack() {
          @Override public void call(Object... args) {
            Logger.e("Ack"+args);
          }
        });
        // finalSocket.emit("foo", "hi");
        //finalSocket.disconnect();
      }
    }).on("event", new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e("event");
      }
    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

      @Override public void call(Object... args) {
        Logger.e("EVENT_DISCONNECT");
      }
    })
    .on(Event.CREATE_ORDER, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e(Event.CREATE_ORDER);
      }
    });
    socket.connect();

    //JSONObject obj = new JSONObject();
    //try {
    //  obj.put("hello", "server");
    //  obj.put("binary", new byte[42]);
    //} catch (JSONException e) {
    //  e.printStackTrace();
    //}

    //  And vice versa:
    // ack from client to server
    socket.on(Event.CREATE_ORDER, new Emitter.Listener() {
      @Override public void call(Object... args) {
        Logger.e("Ack1"+args);
        Ack ack = (Ack) args[args.length - 1];
        ack.call();
      }
    });
    socket.emit(Event.CREATE_ORDER, "dddddddddddsss");
  }

  private void get2(String s0, String s1, String s) {
    OkHttpUtils.get()
        .url("http://192.168.1.170:5000" + s)
        .addParams(s0, s1)
        .build()
        .execute(new StringCallback() {
          @Override public void onError(Call call, Exception e, int i) {
            Logger.e(call.toString() + "---" + e + i);
          }

          @Override public void onResponse(String resp, int i) {
            Logger.e("---" + resp);
            mTvResponse.setText(resp);
          }
        });
  }

  public static final MediaType MEDIA_TYPE_MARKDOWN =
      MediaType.parse("application/text; charset=utf-8");
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  OkHttpClient client = new OkHttpClient();

  private void getpost1(String s0, String s1, String s) {
    try {
      Response response =
          OkHttpUtils.get().url("http://192.168.1.170:5000" + s).tag(this).build().execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void post2(String s0, String s1, String s) {
    OkHttpUtils.post()
        .url("http://192.168.1.170:5000" + s)
        .addParams(s0, s1)
        .build()
        .execute(new StringCallback() {
          @Override public void onError(Call call, Exception e, int i) {
            Logger.e(call.toString() + "---" + e + i);
          }

          @Override public void onResponse(String resp, int i) {
            Logger.e("---" + resp);
            mTvResponse.setText(resp);
          }
        });
  }

  private void post(String s0, String s1, String s) {
    try {
      Request request = new Request.Builder().url("http://192.168.1.170:5000" + s)
          .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, s1))
          .build();

      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      String string = response.body().string();
      System.out.println(response.body().string());
      mTvResponse.setText(string);
    } catch (IOException e) {
      e.printStackTrace();
      Logger.e(e.toString());
    }
  }

  private void sendOkhhttp(String cateId, String s, String s1) {
    //创建okHttpClient对象
    OkHttpClient mOkHttpClient = new OkHttpClient();
    //创建一个Request
    final Request request = new Request.Builder().url("http://192.168.1.170:5000" + s).build();
    //new call
    Call call = mOkHttpClient.newCall(request);
    //Response execute = call.execute();
    //请求加入调度
    call.enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {

      }

      @Override public void onResponse(Call call, final Response response) throws IOException {
        final String s = response.body().string();
        runOnUiThread(new Runnable() {
          @Override public void run() {
            mTvResponse.setText(s);
          }
        });
      }
    });
  }

  //Gson gson = new Gson();
  //FireMessage fireMessage = new FireMessage();
  //fireMessage.setOperateType(FireMessage.ALL_OCCUPIED_TABLE_REQ);
  //UUID u = UUID.randomUUID();
  //
  //ToastUtils.showShort(MainActivity1.this, u.toString());
  //Logger.i(u.toString());
  //fireMessage.setUUID(u);
  ////setData
  //fireMessage.setData(gson.toJson(s1));
  private void sends(String key, String value, String s) {
    //String ip = mEtIp.getText().toString();
    //checkIpEmpty(ip);
    //String serverip = "http://172.17.116.54:5000" + s;
    String serverip = "http://192.168.1.170:5000" + s;
    RequestParams params = new RequestParams();
    //Student s1 = new Student("lj", "99", 25);
    //String json = new Gson().toJson(s1);
    params.put(key, value);
    mAsyncHttpClient.get(serverip, params, new TextHttpResponseHandler() {
      @Override public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
        Logger.e(s);
        mTvResponse.setText(s);
      }

      @Override public void onSuccess(int i, Header[] headers, String s) {
        Logger.e(s);
        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //HttpProductListResp httpProductListResp = gson.fromJson(s, HttpProductListResp.class);
        //Logger.e(httpProductListResp.toString());
        mTvResponse.setText(s);
      }
    });
  }

  private void checkIpEmpty(String ip) {
    if (ip == null || ip.toString().trim().equals("")) {
      Toast.makeText(MainActivity1.this, "请输入ip", Toast.LENGTH_SHORT).show();
      return;
    }
  }

  private void post3(String s0, String s1, final String s) {
    Map map = new HashMap();
    map.put(s0, s1);
    String json = String.valueOf(map);
    OkHttpClient okHttpClient = new OkHttpClient();
    RequestBody requestBody = RequestBody.create(JSON, s1);
    //创建一个请求对象
    Request request =
        new Request.Builder().url("http://192.168.1.170:5000" + s).post(requestBody).build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {

      @Override public void onFailure(Call call, IOException e) {

      }

      @Override public void onResponse(Call call, final Response response) throws IOException {
        runOnUiThread(new Runnable() {
          @Override public void run() {
            mTvResponse.setText(response.body().toString());
          }
        });
      }
    });
  }

  private void post1(String s0, String s1, String s) {
    //RequestBody formBody = new FormEncodingBuilder()
    //    .add("","")
    //    .build();
    //
    //final Request request = new Request.Builder()
    //    .url("http://www.wooyun.org")
    //    .post(formBody)
    //    .build();
    //
    //new Thread(new Runnable() {
    //  @Override
    //  public void run() {
    //    Response response = null;
    //    try {
    //      response = client.newCall(request).execute();
    //      if (response.isSuccessful()) {
    //        Logger.e("WY","打印POST响应的数据：" + response.body().string());
    //      } else {
    //        throw new IOException("Unexpected code " + response);
    //      }
    //    } catch (IOException e) {
    //      e.printStackTrace();
    //    }
    //  }
    //}).start();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    //可以取消同一个tag的

    Client.cancelTag(this);//取消以Activity.this作为tag的请求
  }
}
