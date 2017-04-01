package com.psi.androidhttpclient.ui.fragment.web.ui;

import com.psi.androidhttpclient.ui.fragment.BasePresenter;

public class WebContract {

    public interface View {
    }

    public interface Presenter extends BasePresenter {

        /**
         *
         * @param heartBeatPeriod 心跳包周期
         */
        void qryHeartBeat(int heartBeatPeriod);
    }
}