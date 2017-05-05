package com.jitosoft.qrpay.presentation.mvp;

public abstract class AbsPresenter<V extends BaseView> implements BasePresenter<V> {

    private V view;

    public AbsPresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
