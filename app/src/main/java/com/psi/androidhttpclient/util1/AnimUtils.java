package com.psi.androidhttpclient.util1;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.psi.androidhttpclient.R;

/**
 * Created by Administrator on 2016/5/26.
 */
public class AnimUtils {

    public static Animation getRotateCircleAnimation(Context context){
        return AnimationUtils.loadAnimation(context, R.anim.boc_loading);
    }
}
