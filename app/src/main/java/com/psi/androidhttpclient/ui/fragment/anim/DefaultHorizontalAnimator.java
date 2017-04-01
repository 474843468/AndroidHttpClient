package com.psi.androidhttpclient.ui.fragment.anim;

import com.psi.androidhttpclient.R;

/**
 * Created by YoKeyword on 16/2/5.
 */
public class DefaultHorizontalAnimator extends FragmentAnimator {

    public DefaultHorizontalAnimator() {
        enter = R.anim.boc_fragment_infromleft;
        exit = R.anim.boc_fragment_outtoright;
        popEnter = R.anim.boc_fragment_infromright;
        popExit = R.anim.boc_fragment_outtoleft;
    }
}
