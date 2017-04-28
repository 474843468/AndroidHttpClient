package com.psi.androidhttpclient.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.orhanobut.logger.Logger;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.adapter.AppsAdapter;
import com.psi.androidhttpclient.adapter.AppsViewHolder;
import com.psi.androidhttpclient.adapter.myInfo;
import com.psi.androidhttpclient.utils.AppUtils;
import java.util.ArrayList;
import java.util.List;

public class Main14Activity extends AppCompatActivity {

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link FragmentPagerAdapter} derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  private SectionsPagerAdapter mSectionsPagerAdapter;
  List<PlaceholderFragment> fragments = new ArrayList<>();
  /**
   * The {@link ViewPager} that will host the section contents.
   */
  private ViewPager mViewPager;
  TabLayout mTabLayout;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main14);
    AppUtils.init(this);
    Logger.e(AppUtils.sScreenWidth+"");
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
   mTabLayout = (TabLayout) findViewById(R.id.tab_comment);
    //Intent intent = getIntent();
    //int allNum = intent.getExtras().getInt("allNum");
    //int shortNum = intent.getExtras().getInt("shortNum");
    //int longNum = intent.getExtras().getInt("longNum");
    //int id = intent.getExtras().getInt("id");
    //intent.getExtras().getInt("shortNum");
    //setToolBar(toolBar,String.format("%d条评论",allNum));
    setSupportActionBar(toolbar);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    PlaceholderFragment f1 = PlaceholderFragment.newInstance(1);
    PlaceholderFragment f2 = PlaceholderFragment.newInstance(2);
    fragments.add(f1);
    fragments.add(f2);
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),fragments);

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.container);
    mViewPager.setAdapter(mSectionsPagerAdapter);
    int shortNum=2;
    int longNum=3;
    mTabLayout.addTab(mTabLayout.newTab().setText(String.format("短评论(%d)",shortNum)));
    mTabLayout.addTab(mTabLayout.newTab().setText(String.format("长评论(%d)",longNum)));
    mTabLayout.setupWithViewPager(mViewPager);
    mTabLayout.getTabAt(0).setText(String.format("短评论(%d)",shortNum));
    mTabLayout.getTabAt(1).setText(String.format("长评论(%d)",longNum));
    //TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    //tabLayout.setupWithViewPager(mViewPager);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
    loadApps();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main14, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment implements AppsViewHolder.OnItemClickListener,
      AppsViewHolder.OnItemLongClickListener{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private AppsAdapter mAdapter;
    /* 是不是选择模式 */
    private boolean mIsMenuSelectMode = true;
    private List<myInfo> mResolveInfos;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main14, container, false);
      TextView textView = (TextView) rootView.findViewById(R.id.section_label);
      RecyclerView appsRcl = (RecyclerView) rootView.findViewById(R.id.rcl_apps);
     int size = AppUtils.sScreenWidth /5;
      Logger.e(size+"-------");
      FragmentActivity activity = getActivity();
      Main14Activity activity1 = (Main14Activity) activity;
      mResolveInfos =  activity1.loadApps();
      Logger.e(mResolveInfos.size()+"---liujian");
      mAdapter = new AppsAdapter(getContext(), mResolveInfos, size, this, this);
      GridLayoutManager gridLayoutManager =
          new GridLayoutManager(activity, 5, GridLayoutManager.VERTICAL, true);
      appsRcl.setLayoutManager(gridLayoutManager);
      appsRcl.setAdapter(mAdapter);
      textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
      return rootView;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      ButterKnife.bind(this, view);
      super.onViewCreated(view, savedInstanceState);
    }
 /*   那个答案说得挺清楚的, 具体区别就是adapter和layout的位置会有时间差(<16ms), 如果你改变了Adapter的数据然后刷新视图, layout需要过一段时间才会更新视图, 在这段时间里面, 这两个方法返回的position会不一样.

    另外答案还提到, 在notifyDataSetChanged之后并不能马上获取Adapter中的position, 要等布局结束之后才能获取到.

        而对于Layout的position, 在notifyItemInserted之后, Layout不能马上获取到新的position, 因为布局还没更新(需要<16ms的时间刷新视图), 所以只能获取到旧的, 但是Adapter中的position就可以马上获取到最新的position.

    大致这样*/
    @Override public void onItemClick(View v, int layoutPosition, int adapterPosition) {
      if (mIsMenuSelectMode) {
        if (!mAdapter.isPhotoSelected(adapterPosition)) {
          mAdapter.setSelectedPosition(true, adapterPosition);
        } else {
          mAdapter.setSelectedPosition(false, adapterPosition);
        }
      } else {
        ResolveInfo info = mResolveInfos.get(adapterPosition).getInfo();
        //该应用的包名
        String pkg = info.activityInfo.packageName;
        //应用的主activity类
        String cls = info.activityInfo.name;
        ComponentName componet = new ComponentName(pkg, cls);
        Intent i = new Intent();
        i.setComponent(componet);
        startActivity(i);
      }
    }

    @Override public boolean onItemLongClick(View v, int layoutPosition, int adapterPosition) {
      //if (!mIsMenuSelectMode) {
      //}
      //mAdapter.setSelectedPosition(true, adapterPosition);
      Snackbar.make(v,"dd",Snackbar.LENGTH_INDEFINITE).show();
      return true;
    }
    @Override
    public void onDestroyView() {
      super.onDestroyView();
      ButterKnife.unbind(this);
    }

  }
  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */

  public  List<myInfo> loadApps() {
    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
   List<myInfo> myInfos = new ArrayList<>();
    List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(mainIntent, 0);
    for (ResolveInfo r:resolveInfos){
      myInfo myInfo = new myInfo();
      myInfo.setInfo(r);
      myInfo.setSelected(true);
      myInfos.add(myInfo);
    }
    return myInfos;
  }
  public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private List<PlaceholderFragment> fragments;
    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }
    public SectionsPagerAdapter(FragmentManager fm,List<PlaceholderFragment> fragments) {
      super(fm);
      this.fragments = fragments;
    }

    @Override public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
    // return PlaceholderFragment.newInstance(position + 1);
     return fragments.get(position);
    }

    @Override public int getCount() {
      // Show 3 total pages.
     // return 3;
      return fragments.size();
    }

    @Override public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "SECTION 1";
        case 1:
          return "SECTION 2";
        case 2:
          return "SECTION 3";
      }
      return null;
    }
  }
}
