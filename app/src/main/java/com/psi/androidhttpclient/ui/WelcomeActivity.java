package com.psi.androidhttpclient.ui;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.animator.ChatAvatarsAnimator;
import com.psi.androidhttpclient.animator.InSyncAnimator;
import com.psi.androidhttpclient.animator.RocketAvatarsAnimator;
import com.psi.androidhttpclient.animator.RocketFlightAwayAnimator;
import com.redbooth.WelcomeCoordinatorLayout;

public class WelcomeActivity extends AppCompatActivity {
  private boolean animationReady = false;
  private ValueAnimator backgroundAnimator;
  @Bind(R.id.coordinator) WelcomeCoordinatorLayout coordinatorLayout;
  private RocketAvatarsAnimator rocketAvatarsAnimator;
  private ChatAvatarsAnimator chatAvatarsAnimator;
  private RocketFlightAwayAnimator rocketFlightAwayAnimator;
  private InSyncAnimator inSyncAnimator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    ButterKnife.bind(this);
    initializeListeners();
    initializePages();
    initializeBackgroundTransitions();
  }

  private void initializePages() {
    final WelcomeCoordinatorLayout coordinatorLayout
        = (WelcomeCoordinatorLayout)findViewById(R.id.coordinator);
    coordinatorLayout.addPage(R.layout.welcome_page_1,
        R.layout.welcome_page_2,
        R.layout.welcome_page_3,
        R.layout.welcome_page_4);

    View mLastPage = coordinatorLayout.findViewById(R.id.page4);
    mLastPage.findViewById(R.id.android_spin).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(WelcomeActivity.this, Main3Activity.class);
        startActivity(intent);
        finish();
      }
    });
  }

  private void initializeListeners() {
    coordinatorLayout.setOnPageScrollListener(new WelcomeCoordinatorLayout.OnPageScrollListener() {
      @Override
      public void onScrollPage(View v, float progress, float maximum) {
        if (!animationReady) {
          animationReady = true;
          backgroundAnimator.setDuration((long) maximum);
        }
        backgroundAnimator.setCurrentPlayTime((long) progress);
      }

      @Override
      public void onPageSelected(View v, int pageSelected) {
        switch (pageSelected) {
          case 0:
            if (rocketAvatarsAnimator == null) {
              rocketAvatarsAnimator = new RocketAvatarsAnimator(coordinatorLayout);
              rocketAvatarsAnimator.play();
            }
            break;
          case 1:
            if (chatAvatarsAnimator == null) {
              chatAvatarsAnimator = new ChatAvatarsAnimator(coordinatorLayout);
              chatAvatarsAnimator.play();
            }
            break;
          case 2:
            if (inSyncAnimator == null) {
              inSyncAnimator = new InSyncAnimator(coordinatorLayout);
              inSyncAnimator.play();
            }
            break;
          case 3:
            if (rocketFlightAwayAnimator == null) {
              rocketFlightAwayAnimator = new RocketFlightAwayAnimator(coordinatorLayout);
              rocketFlightAwayAnimator.play();
            }
            break;
        }
      }
    });
  }

  private void initializeBackgroundTransitions() {
    final Resources resources = getResources();
    final int colorPage1 = ResourcesCompat.getColor(resources, R.color.page1, getTheme());
    final int colorPage2 = ResourcesCompat.getColor(resources, R.color.page2, getTheme());
    final int colorPage3 = ResourcesCompat.getColor(resources, R.color.page3, getTheme());
    final int colorPage4 = ResourcesCompat.getColor(resources, R.color.page4, getTheme());
    backgroundAnimator = ValueAnimator
        .ofObject(new ArgbEvaluator(), colorPage1, colorPage2, colorPage3, colorPage4);
    backgroundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        coordinatorLayout.setBackgroundColor((int) animation.getAnimatedValue());
      }
    });
  }

  @OnClick(R.id.skip)
  void skip() {
    coordinatorLayout.setCurrentPage(coordinatorLayout.getNumOfPages() - 1, true);
  }
  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
