/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.psi.androidhttpclient.defineBehaviorFAB;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.psi.androidhttpclient.R;

/**
 * --判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout
 位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己
 去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，
 它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。就将AppBarLayout与ScrollView关联起来了-->
 * Created on 2016/7/15.
 *behaviord的使用
 * @author Yan Zhenjie.
 */
public class Main9Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_back_top).setOnClickListener(this);
        findViewById(R.id.btn_zhihu).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.btn_swipe_dismiss).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_top) {// 回到顶部按钮动画。
            startActivity(new Intent(this, BackTopActivity.class));
        } else if (v.getId() == R.id.btn_zhihu) {// 仿知乎首页隐藏按钮动画。
            startActivity(new Intent(this, ZhihuActivity.class));
        } else if (v.getId() == R.id.btn_bottom_sheet) {// 底部覆盖。
            startActivity(new Intent(this, BottomSheetBehaviorActivity.class));
        } else if (v.getId() == R.id.btn_swipe_dismiss) {// 滑动删除。
            startActivity(new Intent(this, SwipeDismissBehaviorActivity.class));
        }
    }
}
