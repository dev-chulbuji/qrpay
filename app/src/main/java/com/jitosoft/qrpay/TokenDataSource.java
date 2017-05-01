package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public interface TokenDataSource {

    interface GetTokenCallback {

        void onTokenLoaded(String token);

    }

    void getToken(@NonNull String email, @NonNull String password, GetTokenCallback callback);
}
