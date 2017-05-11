package com.jitosoft.qrpay.presentation.ui.main;

import com.jitosoft.qrpay.presentation.mvp.BasePresenter;
import com.jitosoft.qrpay.presentation.mvp.BaseView;

public interface MainContract {

    interface View extends BaseView {

        void refresh();
    }

    interface Presenter extends BasePresenter<View> {

        void loadCards();
    }
}
