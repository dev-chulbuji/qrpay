package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.MemberEntity;
import com.jitosoft.qrpay.presentation.util.LogUtils;

import io.reactivex.Flowable;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class MemberRemoteDataSource implements MemberDataSource {

    private static final String TAG = MemberRemoteDataSource.class.getName();

    @Override
    public Flowable<MemberEntity> saveMember(@NonNull String email,
                                             @NonNull String nickname,
                                             @NonNull String password) {
        // call api client
        // cache token

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail("this is test. we have to get data from api client");
        return Flowable.just(memberEntity);
    }

    @Override
    public Flowable<MemberEntity> getMember() {

        // call api client with token

        LogUtils.debug(TAG, "this function complete not yet");
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail("this is test. we have to get data from api client");
        return Flowable.just(memberEntity);
    }
}
