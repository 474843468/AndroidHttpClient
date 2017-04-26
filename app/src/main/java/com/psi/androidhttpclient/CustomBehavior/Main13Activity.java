package com.psi.androidhttpclient.customBehavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.psi.androidhttpclient.R;

/**
 *       <!--最重要的是设置： app:layout_scrollFlags="scroll|exitUntilCollapsed"这样它才能随滑动而滚动。-->
 <!---->
 <!--在toolbar中设置：app:layout_collapseMode="pin"，使它不随滑动而退出屏幕。在AppbarLayout中设置显示折叠的高度。
 还可以在包裹的ImageView中设置滑动联动效果： app:layout_collapseMode="parallax" app:layout_collapseParallaxMultiplier="0.7"。-->
 <!---->
 <!--其实，实现也不是特别的复杂，只要理解了这几个新空间的用途，很容易就可以做到自己想要的酷炫效果。好了，如果你有什么疑问欢迎下方留言交流。希望对你有所帮助。-->
 */
public class Main13Activity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main13);
    /**
     *   setSupportActionBar(toolBarTitle);
     ctl_title.setCollapsedTitleGravity(Gravity.CENTER);
     ctl_title.setExpandedTitleGravity(Gravity.CENTER);
     ctl_title.setTitle("手机安全卫士");
     ctl_title.setExpandedTitleColor(Color.WHITE);
     ctl_title.setCollapsedTitleTextColor(Color.WHITE);
     */
    findViewById(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(Main13Activity.this,EasyBehaviorActivity.class));
      }
    });
  }


  public void titleMode(View v){
    findViewById(R.id.iv_avatar).setVisibility(View.INVISIBLE);
    findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
  }

  public void avatarMode(View v ){

    findViewById(R.id.iv_avatar).setVisibility(View.VISIBLE);
    findViewById(R.id.tv_title).setVisibility(View.INVISIBLE);
  }
}
