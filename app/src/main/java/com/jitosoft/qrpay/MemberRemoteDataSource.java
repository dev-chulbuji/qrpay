package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class MemberRemoteDataSource implements MemberDataSource {

    private static final String TAG = MemberRemoteDataSource.class.getName();

    @Override
    public void saveMember(@NonNull String email,
                           @NonNull String nickname,
                           @NonNull String password,
                           @NonNull SaveMemberCallback callback) {
        // call api client
        // cache token

        LogUtils.debug(TAG, "this function complete not yet");
        callback.onSaveMemberCompleted();
    }

    @Override
    public Member getMember() {

        // call api client with token

        LogUtils.debug(TAG, "this function complete not yet");
        return new Member();
    }
}
