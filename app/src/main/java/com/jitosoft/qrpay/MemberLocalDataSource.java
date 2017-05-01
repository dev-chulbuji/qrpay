package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 29..
 */

public class MemberLocalDataSource implements MemberDataSource {

    @Override
    public void saveMember(@NonNull String email, @NonNull String nickname, @NonNull String password, @NonNull SaveMemberCallback callback) {
        throw new UnsupportedOperationException("this function always called in remote data source");
    }

    @Override
    public Member getMember() {

        // get member from cache
        throw new UnsupportedOperationException("this function complete not yet");
    }
}
