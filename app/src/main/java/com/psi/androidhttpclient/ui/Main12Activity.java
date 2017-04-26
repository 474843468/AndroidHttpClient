package com.psi.androidhttpclient.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import com.psi.androidhttpclient.R;

/**
 * CollapsingToolbarLayout包裹 Toolbar 的时候提供一个可折叠的 Toolbar，一般作为AppbarLayout的子视图使用。

 CollapsingToolbarLayout 提供以下属性和方法是用：

 Collapsing title：ToolBar的标题，当CollapsingToolbarLayout全屏没有折叠时，title显示的是大字体，在折叠的过程中，title不断变小到一定大小的效果。你可以调用setTitle(CharSequence)方法设置title。
 Content scrim：ToolBar被折叠到顶部固定时候的背景，你可以调用setContentScrim(Drawable)方法改变背景或者 在属性中使用 app:contentScrim=”?attr/colorPrimary”来改变背景。
 Status bar scrim：状态栏的背景，调用方法setStatusBarScrim(Drawable)。还没研究明白，不过这个只能在Android5.0以上系统有效果。
 Parallax scrolling children：CollapsingToolbarLayout滑动时，子视图的视觉差，可以通过属性app:layout_collapseParallaxMultiplier=”0.6”改变。
 CollapseMode ：子视图的折叠模式，有两种“pin”：固定模式，在折叠的时候最后固定在顶端；“parallax”：视差模式，在折叠的时候会有个视差折叠的效果。
 我们可以在布局中使用属性app:layout_collapseMode=”parallax”来改变。

 总结： CollapsingToolbarLayout主要是提供一个可折叠的Toolbar容器，对容器中的不同视图设置layout_collapseMode折叠模式，来达到不同的折叠效果。

 1.Toolbar 的高度layout_height必须固定，不能 “wrap_content”，否则Toolbar不会滑动，也没有折叠效果。
 2.为了能让FloatingActionButton也能折叠且消失出现，我们必须给FAB设置锚点属性

 app:layout_anchor="@id/appbar"
 1
 1
 意思是FAB浮动按钮显示在哪个布局区域。
 且设置当前锚点的位置

 app:layout_anchorGravity=”bottom|end|right”

 意思FAB浮动按钮在这个布局区域的具体位置。
 两个属性共同作用才是的FAB 浮动按钮也能折叠消失，出现。

 3.给需要有折叠效果的组件设置 layout_collapseMode属性。

 */
public class Main12Activity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window window = getWindow();
      // Translucent status bar
      window.setFlags(
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    setContentView(R.layout.activity_main12);

    // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //
    //toolbar.setTitle("题栏");
    //toolbar.setNavigationIcon(R.drawable.back);
    //setSupportActionBar(toolbar);

  }
}
