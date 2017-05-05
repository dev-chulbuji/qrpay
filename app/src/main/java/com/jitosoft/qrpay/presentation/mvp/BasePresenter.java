package com.jitosoft.qrpay.presentation.mvp;

public interface BasePresenter<V extends BaseView> {
    V getView();
}
