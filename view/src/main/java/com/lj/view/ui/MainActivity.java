package com.lj.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.lj.view.R;
import uk.co.senab.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

  private PhotoView photoView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    photoView = new PhotoView(getApplicationContext());

  }
}
