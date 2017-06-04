package com.psi.androidhttpclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.psi.androidhttpclient.R;
import java.util.List;

/**
 * Created by Administrator on 2017-05-02.
 */
public class ZhuangbiListAdapter extends RecyclerView.Adapter {
  List<ZhuangbiImage> images;

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
    return new DebounceViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
    ZhuangbiImage image = images.get(position);
    Glide.with(holder.itemView.getContext()).load(image.image_url).into(debounceViewHolder.imageIv);
    debounceViewHolder.descriptionTv.setText(image.description);
  }

  @Override
  public int getItemCount() {
    return images == null ? 0 : images.size();
  }

  public void setImages(List<ZhuangbiImage> images) {
    this.images = images;
    notifyDataSetChanged();
  }

  static class DebounceViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.imageIv) ImageView imageIv;
    @Bind(R.id.descriptionTv) TextView descriptionTv;
    public DebounceViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}