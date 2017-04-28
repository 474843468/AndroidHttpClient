package com.psi.androidhttpclient.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yuyidong on 15/12/27.
 */
public class GridItemImageView extends ImageView {
    private int mSize = 0;

    public GridItemImageView(Context context) {
        this(context, null);
    }

    public GridItemImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridItemImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSize(int size) {
        mSize = size;
    }
/*    dimension
    英 [dɪ'menʃ(ə)n; daɪ-]  美 [dəˈmɛnʃən, daɪ-]
    n. 方面;[数] 维；尺寸；次元；容积 vt. 标出尺寸
    adj. 规格的*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mSize, mSize);
    }
}
