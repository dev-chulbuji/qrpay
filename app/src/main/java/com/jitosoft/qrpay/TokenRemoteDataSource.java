package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class TokenRemoteDataSource implements TokenDataSource {

    @Override
    public void getToken(@NonNull String email, @NonNull String password, GetTokenCallback callback) {
        // call api client

        callback.onTokenLoaded("MOCKING TOKEN FROM APP");
    }
}
