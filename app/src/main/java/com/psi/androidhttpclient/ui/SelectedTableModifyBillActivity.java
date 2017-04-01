package  com.psi.androidhttpclient.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.adapter.TableTypeAdapter;
import com.psi.androidhttpclient.utils.ToastUtils;
import com.psi.androidhttpclient.widget.TouchMoveRcv;
import java.util.ArrayList;
import java.util.List;

public class SelectedTableModifyBillActivity extends AppCompatActivity {
  @Bind(R.id.swp) SwipeRefreshLayout mSwp;
  @Bind(R.id.rcv_table_type) RecyclerView mRcvTableType;
  @Bind(R.id.rcv_table_list) TouchMoveRcv mRcvTableList;
  private MaterialSearchView searchView;
  private List<String> mMVals=creatNewList();
  private TableTypeAdapter mTableTypeAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_colored);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back_inverted);
    toolbar.setTitle("粉丝");
    setSupportActionBar(toolbar);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {

       @Override public void onClick(View v) {
         if (searchView.isSearchOpen()) {
           searchView.closeSearch();
         } else {
           SelectedTableModifyBillActivity.this.finish();
         }
       }
     });

    searchView = (MaterialSearchView) findViewById(R.id.search_view);
    searchView.setVoiceSearch(false);
    searchView.setCursorDrawable(R.drawable.color_cursor_white);
    searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

    searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_INDEFINITE)
            .show();
        if (TextUtils.isEmpty(query)) {
          ToastUtils.showShort(null, "输入有误!");
          return false;
        }
        if (mSwp.isRefreshing()) return false;
        mSwp.setRefreshing(true);
        List<String> strings = reqOccupiedTableList(query);
        if (strings.size()==0){
          stopRefreshing();
          return false;
        }
        if (mTableTypeAdapter==null){
          mTableTypeAdapter =  new TableTypeAdapter(SelectedTableModifyBillActivity.this, strings);
          mRcvTableType.setAdapter(mTableTypeAdapter);
          mRcvTableType.setLayoutManager(new LinearLayoutManager(SelectedTableModifyBillActivity.this));
        }else {
          mTableTypeAdapter.setData(strings);
        }
        stopRefreshing();
        searchView.closeSearch();
        return true;
      }


      @Override
      public boolean onQueryTextChange(String newText) {
        //Do some magic
        return false;
      }
    });

    searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {
        //Do some magic
      }

      @Override
      public void onSearchViewClosed() {
        //Do some magic
      }
    });
    //点击事件
    searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(SelectedTableModifyBillActivity.this,"第"+i+"行",Toast.LENGTH_LONG).show();
        String itemAtPosition = (String) adapterView.getItemAtPosition(i);
        Snackbar.make(findViewById(R.id.container), "Query: " + itemAtPosition, Snackbar.LENGTH_INDEFINITE)
            .show();
        mSwp.setRefreshing(true);
        List<String> strings = reqOccupiedTableList(itemAtPosition);
        if (strings.size()==0){
          stopRefreshing();
          return;
        }
        if (mTableTypeAdapter==null){
          mTableTypeAdapter =  new TableTypeAdapter(SelectedTableModifyBillActivity.this, strings);
          mRcvTableType.setAdapter(mTableTypeAdapter);
          mRcvTableType.setLayoutManager(new LinearLayoutManager(SelectedTableModifyBillActivity.this));
        }else {
          mTableTypeAdapter.setData(strings);
        }
        stopRefreshing();
        searchView.closeSearch();

      }
    });

  }

  public  List<String> creatNewList(){
    List<String> mMVals = new ArrayList<>();
    for (; ; ) {
      mMVals.add(System.currentTimeMillis() + "");
      if (mMVals.size() > 40) {
        break;
      }
    }
      return mMVals;
  }
  /**
   * stop swp refreshing
   */
  private void stopRefreshing() {
    if (mSwp.isRefreshing()) mSwp.setRefreshing(false);
  }
  private List<String> reqOccupiedTableList(String query) {
      mMVals = creatNewList();
    if (null == query) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return mMVals;
    }

    List<String> reqList = new ArrayList<>();
    for (String s : mMVals) {
      if (s.contains(query)) {
        reqList.add(s);
      }
    }
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return reqList;
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);

    MenuItem item = menu.findItem(R.id.action_search);
    searchView.setMenuItem(item);

    return true;
  }

  @Override
  public void onBackPressed() {
    if (searchView.isSearchOpen()) {
      searchView.closeSearch();
    } else {
      super.onBackPressed();
    }
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