package com.psi.androidhttpclient.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.widget.SwipeDismissRecyclerViewTouchListener;
import java.util.LinkedList;
import java.util.List;

/**
 * recyclerview滑动删除item
 */
public class Main8Activity extends ActionBarActivity {

  private void showDialog(String msg) {
    AlertDialog alert = new AlertDialog.Builder(Main8Activity.this).setTitle("alert")
        .setMessage(msg)
        .setCancelable(false)
        .create();
    alert.setCanceledOnTouchOutside(true);
    alert.show();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main8);

    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    final RecyclerView anotherRecyclerView =
        (RecyclerView) findViewById(R.id.recyclerHorizontalView);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this);
    horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

    recyclerView.setLayoutManager(layoutManager);
    anotherRecyclerView.setLayoutManager(horizontalLayoutManager);

    List<String> dataset = new LinkedList<String>();
    for (int i = 0; i < 100; i++) {
      dataset.add("item" + i);
    }
    final MyAdapter adapter = new MyAdapter(dataset);
    final MyAdapter adapter1 = new MyAdapter(dataset);

    recyclerView.setAdapter(adapter);
    anotherRecyclerView.setAdapter(adapter1);

    // Bind touch listener

    SwipeDismissRecyclerViewTouchListener listener =
        new SwipeDismissRecyclerViewTouchListener.Builder(recyclerView,
            new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
              @Override public boolean canDismiss(int position) {
                return true;
              }

              @Override public void onDismiss(View view) {
                int id = recyclerView.getChildPosition(view);
                adapter.mDataset.remove(id);
                adapter.notifyDataSetChanged();

                Toast.makeText(getBaseContext(), String.format("Delete item %d", id),
                    Toast.LENGTH_LONG).show();
              }
            }).setIsVertical(false)
            .setItemTouchCallback(new SwipeDismissRecyclerViewTouchListener.OnItemTouchCallBack() {
              @Override public void onTouch(int index) {
                showDialog(String.format("Click item %d", index));
              }
            })
            .setItemClickCallback(new SwipeDismissRecyclerViewTouchListener.OnItemClickCallBack() {
              @Override public void onClick(int position) {
                showDialog(String.format("Click item %d", position));
              }
            })
            .create();

    recyclerView.setOnTouchListener(listener);

    // set touch listener for recyclerHorizontalView(horizontal swipe to remove)

    SwipeDismissRecyclerViewTouchListener verticalListener =
        new SwipeDismissRecyclerViewTouchListener.Builder(anotherRecyclerView,
            new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
              @Override public boolean canDismiss(int position) {
                return true;
              }

              @Override public void onDismiss(View view) {
                int id = recyclerView.getChildPosition(view);
                adapter.mDataset.remove(id);
                adapter.notifyDataSetChanged();

                Toast.makeText(getBaseContext(), String.format("Delete item %d", id),
                    Toast.LENGTH_LONG).show();
              }
            }).setIsVertical(true)
            .setItemTouchCallback(new SwipeDismissRecyclerViewTouchListener.OnItemTouchCallBack() {
              @Override public void onTouch(int index) {
                showDialog(String.format("Click item %d", index));
              }
            })
            .setItemClickCallback(new SwipeDismissRecyclerViewTouchListener.OnItemClickCallBack() {
              @Override public void onClick(int position) {
                showDialog(String.format("Click item %d", position));
              }
            })
            .create();

    anotherRecyclerView.setOnTouchListener(verticalListener);
  }

  public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public List<String> mDataset;

    public MyAdapter(List<String> dataset) {
      super();
      mDataset = dataset;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
      ViewHolder holder = new ViewHolder(view);
      return holder;
    }

    @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
      viewHolder.mTextView.setText(mDataset.get(i));
    }

    @Override public int getItemCount() {
      return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      public TextView mTextView;

      public ViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView;
      }
    }
  }
}