package com.psi.androidhttpclient.adapter;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.psi.androidhttpclient.R;
import java.util.List;

/**
 * Created by Administrator on 2017-04-27.
 */
public class AppsAdapter extends RecyclerView.Adapter<AppsViewHolder>{
  private Context mContext;
  private int mSize;
  private List<myInfo> appsList;
  private AppsViewHolder.OnItemClickListener mOnItemClickListener;
  private AppsViewHolder.OnItemLongClickListener mOnItemLongClickListener;
  public AppsAdapter(Context context, List<myInfo> appsList, int size,
      AppsViewHolder.OnItemClickListener onItemClickListener,
      AppsViewHolder.OnItemLongClickListener onItemLongClickListener) {
    mContext = context;
    mSize = size;
    this.appsList = appsList;
    mOnItemClickListener = onItemClickListener;
    mOnItemLongClickListener = onItemLongClickListener;
  }
  @Override public AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.apps_list, parent, false);
    return new AppsViewHolder(view, mSize, mOnItemClickListener, mOnItemLongClickListener);
  }

  @Override public void onBindViewHolder(AppsViewHolder holder, int position) {
    myInfo myInfo = appsList.get(position);
    ResolveInfo info = myInfo.getInfo();
    Drawable drawable = info.activityInfo.loadIcon(mContext.getPackageManager());
    holder.imageView.setImageDrawable(drawable);
    if (myInfo.isSelected()) {
      holder.checkLayout.setVisibility(View.VISIBLE);
    } else {
      holder.checkLayout.setVisibility(View.INVISIBLE);
    }
    //int color =  Color.argb(255, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    //AppCompat.setBackgroundDrawable(holder.checkLayout,
    //    new ColorDrawable(Color.argb(0x70, Color.red(color), Color.green(color), Color.blue(color))));
  }

  @Override public int getItemCount() {
    return appsList.size();
  }


  /**
   * 点item的菜单时候的删除
   *
   * @param selected
   * @param position
   */
  public void setSelectedPosition(boolean selected, int position) {
   appsList.get(position).setSelected(selected);
    notifyItemChanged(position);
  }

  /**
   * 全选
   */
  public void selectAllPhotos() {
    for (int i = 0; i < appsList.size(); i++) {
     appsList.get(i).setSelected(true);
      notifyItemChanged(i);
    }
  }

  /**
   * 取消选择所有照片
   */
  public void cancelSelectPhotos() {
    for (myInfo photoNote : appsList) {
     photoNote.setSelected(false);
      int index = appsList.indexOf(photoNote);
      notifyItemChanged(index);
    }
  }

  /**
   * 照片是否被选择了
   *
   * @param position
   * @return
   */
  public boolean isPhotoSelected(int position) {
    return appsList.get(position).isSelected();
  }

  /**
   * 更新数据
   *
   * @param photoNotes
   */
  public void updateData(List<myInfo> photoNotes) {
    appsList = photoNotes;
    notifyDataSetChanged();
  }

  public void updateDataNoChange(List<myInfo> photoNotes) {
    appsList = photoNotes;
  }
}
