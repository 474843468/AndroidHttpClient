package com.psi.androidhttpclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.psi.androidhttpclient.R;
import java.util.List;

public class TableTypeAdapter extends RecyclerView.Adapter<TableTypeAdapter.ViewHolder> {
  private List<String> mList;

  private final LayoutInflater mInflater;
  private final int mNumColor;

  private int mSelectedItem = 0;
  private ItemSelectedListener mListener;
  private int mPreSelectedItem = 0;

  public TableTypeAdapter(Context context, List<String> list) {
    mList = list;
    mNumColor = context.getResources().getColor(R.color.colorPrimary);
    mInflater = LayoutInflater.from(context);
  }

  public void setData(List<String> list) {
    this.mSelectedItem = 0;
    this.mPreSelectedItem = 0;
    this.mList = list;
    notifyDataSetChanged();
  }

  public void setSelectedItem(int pos) {
    if (mSelectedItem < getItemCount() && mSelectedItem >= 0) {
      if (mSelectedItem != pos) {
        this.mSelectedItem = pos;
        if (mPreSelectedItem >= 0) {
          notifyItemChanged(mPreSelectedItem);
        }
        notifyItemChanged(mSelectedItem);
        mPreSelectedItem = pos;
      }
    }
  }

  public List<String> getData() {
    return mList;
  }

  @Override public TableTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.item_table_area_type, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(TableTypeAdapter.ViewHolder holder, final int position) {
    String tableAreaType = mList.get(position);
    holder.mTvName.setText(tableAreaType);
    holder.des.setText(tableAreaType);
    holder.mRlContainer.setSelected(mSelectedItem == position);
    holder.mRlContainer.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (getItemCount() > 0 && position >= 0 ) {
         // mListener.itemSelected(position);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return mList.size();
  }

  public interface ItemSelectedListener {
    void itemSelected(int pos);
  }

  public void addItemSelectedListener(ItemSelectedListener listener) {
    this.mListener = listener;
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView mTvName;
    TextView des;
    LinearLayout mRlContainer;
    public ViewHolder(View itemView) {
      super(itemView);
      mRlContainer= (LinearLayout)itemView;
      mTvName = (TextView) itemView.findViewById(R.id.tv_name);
      des = (TextView) itemView.findViewById(R.id.tv_des);
    }
  }

}