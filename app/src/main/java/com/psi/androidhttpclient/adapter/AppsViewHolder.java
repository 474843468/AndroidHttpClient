package com.psi.androidhttpclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.psi.androidhttpclient.R;

/**
 * Created by Administrator on 2017-04-27.
 */
public class AppsViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
  @Bind(R.id.img_item_album)
  public ImageView imageView;
  @Bind(R.id.layout_item_album_check)
  public ImageView checkLayout;
  private OnItemClickListener mOnItemClickListener;
  private OnItemLongClickListener mOnItemLongClickListener;
  public AppsViewHolder(View itemView, int size, OnItemClickListener onItemClickListener, OnItemLongClickListener onItemLongClickListener) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    itemView.setOnLongClickListener(this);
    itemView.setOnClickListener(this);
    //imageView.setSize(size);
    //checkLayout.setSize(size);
    mOnItemClickListener = onItemClickListener;
    mOnItemLongClickListener = onItemLongClickListener;
  }
  @Override
  public void onClick(View v) {
    if (mOnItemClickListener != null) {
      mOnItemClickListener.onItemClick(v, getLayoutPosition(), getAdapterPosition());
    }
  }

  @Override
  public boolean onLongClick(View v) {
    if (mOnItemLongClickListener != null) {
      return mOnItemLongClickListener.onItemLongClick(v, getLayoutPosition(), getAdapterPosition());
    }
    return false;
  }


  public interface OnItemClickListener {
    void onItemClick(View v, int layoutPosition, int adapterPosition);
  }

  public interface OnItemLongClickListener {
    boolean onItemLongClick(View v, int layoutPosition, int adapterPosition);
  }
}
