package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class TokenRepository implements TokenDataSource {

    TokenDataSource tokenDataSource;

    public TokenRepository() {
    }

    @Override
    public void getToken(@NonNull String email, @NonNull String password, GetTokenCallback callback) {
        tokenDataSource = new TokenRemoteDataSource();

        tokenDataSource.getToken(email, password, callback);
    }
}
