package com.psi.androidhttpclient.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.adapter.TableTypeAdapter;
import com.psi.androidhttpclient.utils.ToastUtils;
import com.psi.androidhttpclient.widget.TouchMoveRcv;
import java.util.ArrayList;
import java.util.List;

public class materialSearchView extends AppCompatActivity {
 // @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.search_view) MaterialSearchView searchView;
  @Bind(R.id.swp) SwipeRefreshLayout mSwp;
  @Bind(R.id.rcv_table_type) RecyclerView mRcvTableType;
  @Bind(R.id.rcv_table_list) TouchMoveRcv mRcvTableList;
  private List<String> mMVals;
  private TableTypeAdapter mTableTypeAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_material_search_view);
    ButterKnife.bind(this);
    mMVals = new ArrayList<>();
    for (; ; ) {
      mMVals.add(System.currentTimeMillis() + "");
      if (mMVals.size() > 40) {
        break;
      }
    }

   // mToolbar.setTitle("选择改单桌台");
   // setSupportActionBar(mToolbar);
   // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   // getSupportActionBar().setDisplayShowHomeEnabled(true);
   // mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
   //
   //   @Override public void onClick(View v) {
   //     if (searchView.isSearchOpen()) {
   //       searchView.closeSearch();
   //     } else {
   //       materialSearchView.this.finish();
   //     }
   //   }
   // });

    final EditText etSearch = (EditText) searchView.findViewById(R.id.searchTextView);
    final ImageButton btnSearch =
        (ImageButton) LayoutInflater.from(this).inflate(R.layout.view_btn_search, null);
    FrameLayout.LayoutParams params =
        new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.RIGHT);
    searchView.addView(btnSearch, params);
    searchView.setHint("ffff");
    searchView.isSearchOpen();
    btnSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        String query = etSearch.getText().toString();
        //if (TextUtils.isEmpty(query)){
        //  ToastUtils.showShort(materialSearchView.this,"输入有误");
        //  return ;
        //}
        if (mSwp.isRefreshing()) return;
        mSwp.setRefreshing(true);
        List<String> strings = reqOccupiedTableList(query);
        mTableTypeAdapter.setData(strings);
        searchView.closeSearch();

      }
    });
    searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        if (TextUtils.isEmpty(query)) {
          ToastUtils.showShort(null, "输入有误!");
          return false;
        }
        if (mSwp.isRefreshing()) return false;
        mSwp.setRefreshing(true);
        List<String> strings = reqOccupiedTableList(query);
        mTableTypeAdapter.setData(strings);
        searchView.closeSearch();
        return true;
      }

      @Override public boolean onQueryTextChange(String newText) {
        return false;
      }
    });

    searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override public void onSearchViewShown() {
        btnSearch.setVisibility(View.VISIBLE);
      }

      @Override public void onSearchViewClosed() {
        btnSearch.setVisibility(View.GONE);
      }
    });
    //rcv table list
    //  List<PxTableInfo>  mTableList = new ArrayList<>();
    //  TableTypeListAdapter  mTableListAdapter = new TableTypeListAdapter(this, mTableList);
    ////  mTableListAdapter.setItemClickListener(new materialSearchView.TableItemClickListener());
    //  final LinearLayoutManager tableListLm = new LinearLayoutManager(this);
    //  mRcvTableList.setLayoutManager(tableListLm);
    //  mRcvTableList.setAdapter(mTableListAdapter);
    //  mRcvTableList.addOnScrollListener(new TableListRcvOnScrollListener(tableListLm));

    mTableTypeAdapter = new TableTypeAdapter(this, mMVals);
    mRcvTableType.setAdapter(mTableTypeAdapter);
    mRcvTableType.setLayoutManager(new LinearLayoutManager(this));
    //  mTableTypeAdapter.addItemSelectedListener(new materialSearchView.TableTypeSelectListener(tableListLm));
    //swp
    //mSwp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    //  @Override public void onRefresh() {
    //    reqOccupiedTableList(null);
    //  }
    //});
  }

  /**
   * table type item selected listener
   */
  class TableTypeSelectListener implements TableTypeAdapter.ItemSelectedListener {
    private LinearLayoutManager mLm;

    public TableTypeSelectListener(LinearLayoutManager lm) {
      mLm = lm;
    }

    @Override public void itemSelected(int pos) {
      //String type = mTableTypeAdapter.getData().get(pos).getType();
      //int firstTablePos = getFirstTablePosByType(type);
      //mTableTypeAdapter.setSelectedItem(pos);
      ////scroll
      //moveToPosition(mLm, firstTablePos);
      //mExceptPos = firstTablePos;
    }
  }

  /**
   * 获取第一个table pos
   */
  private int getFirstTablePosByType(String type) {
    //List<PxTableInfo> data = mTableListAdapter.getData();
    //for (int i = 0; i < data.size(); i++) {
    //  PxTableInfo pxTableInfo = data.get(i);
    //  if (type.equals(pxTableInfo.getType())) {
    //    return i;
    //  }
    //}
    return 0;
  }

  private List<String> reqOccupiedTableList(String query) {
    for (; ; ) {
      mMVals.add(System.currentTimeMillis() + "");
      if (mMVals.size() > 40) {
        break;
      }
    }
    if (null == query) {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      stopRefreshing();
      return mMVals;
    }

    List<String> reqList = new ArrayList<>();
    for (String s : mMVals) {
      if (s.contains(query)) {
        reqList.add(s);
      }
    }
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    stopRefreshing();
    return reqList;
  }
  //@Override
  //public void onBackPressed() {
  //  if (searchView.isSearchOpen()) {
  //    searchView.closeSearch();
  //  } else {
  //    super.onBackPressed();
  //  }
  //}

  /**
   * stop swp refreshing
   */
  private void stopRefreshing() {
    if (mSwp.isRefreshing()) mSwp.setRefreshing(false);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    MenuItem item = menu.findItem(R.id.action_search);
    searchView.setMenuItem(item);
    return true;
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      if (searchView.isSearchOpen()) {
        searchView.closeSearch();
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
