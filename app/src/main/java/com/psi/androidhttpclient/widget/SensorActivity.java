package com.psi.androidhttpclient.widget;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import com.psi.androidhttpclient.R;

/**
 * 要监视原始的传感器数据，你需要实现两个通过SensorEventListener接口暴露的回调方法：onAccuracyChanged()和onSensorChanged()。Android系统在任何发生下列事情的时候都会调用这两个方法：

 1. 传感器精度的改变：

 这种情况中，系统会调用onAccuracyChanged()方法，它提供了你要引用的发生精度变化的Sensor对象。精度使用以下四个状态常量之一来代表的：

 SENSOR_STATUS_ACCURACY_LOW

 SENSOR_STATUS_ACCURACY_MEDIUM

 SENSOR_STATUS_ACCURACY_HIGH

 SENSOR_STATUS_UNRELIABLE

 2. 传感器报告新的值：

 这种情况中，系统会调用onSensorChanged()方法，它提供了一个SensorEvent对象。SensorEvent对象包含了有关新的传感器数据的信息，包括：数据的精度、产生数据的传感器、产生数据时的时间戳、以及传感器记录的新的数据。

 下列代码显示了如何使用onSensorChanged()方法来监视来自亮度传感器的数据。这个例子在一个TextView中显示原始的传感器数据：
 */
public class SensorActivity extends Activity implements SensorEventListener {
  private SensorManager mSensorManager;
  private Sensor mLight;

  @Override public final void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main5);
    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
  }

  @Override public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    // Do something here if sensor accuracy changes.
  }

   @Override  public final void onSensorChanged(SensorEvent event){
    // The light sensor returns a single value.
    // Many sensors return 3 values, one for each axis.
    //  float lux =event.values[0];
    // Do something with this sensor value.
    }
  @Override protected void onResume () {

      super.onResume();

      mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override protected void onPause () {

      super.onPause();

      mSensorManager.unregisterListener(this);
    }
  }