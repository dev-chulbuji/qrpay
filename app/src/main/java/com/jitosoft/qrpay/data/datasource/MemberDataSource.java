package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.MemberEntity;

import io.reactivex.Flowable;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public interface MemberDataSource {

    Flowable<MemberEntity> saveMember(@NonNull String email, @NonNull String nickname, @NonNull String password);

    Flowable<MemberEntity> getMember();
}
