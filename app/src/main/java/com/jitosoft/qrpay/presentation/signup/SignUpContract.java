package com.jitosoft.qrpay.presentation.signup;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.presentation.mvp.BasePresenter;
import com.jitosoft.qrpay.presentation.mvp.BaseView;

public interface SignUpContract {
    interface View extends BaseView {
        void moveToMainView();
    }

    interface Presenter extends BasePresenter<View> {
        void signUp(@NonNull String email,
                    @NonNull String nickname,
                    @NonNull String password);
    }
}
