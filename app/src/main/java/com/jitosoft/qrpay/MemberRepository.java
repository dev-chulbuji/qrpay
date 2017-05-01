package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class MemberRepository implements MemberDataSource {

    MemberDataSource memberDataSource;

    public MemberRepository() {
    }

    @Override
    public void saveMember(@NonNull String email,
                           @NonNull String nickname,
                           @NonNull String password,
                           @NonNull SaveMemberCallback callback) {
        memberDataSource = new MemberRemoteDataSource();
        memberDataSource.saveMember(email, nickname, password, callback);
    }

    @Override
    public Member getMember() {
        memberDataSource = new MemberLocalDataSource();
        return memberDataSource.getMember();
    }
}
