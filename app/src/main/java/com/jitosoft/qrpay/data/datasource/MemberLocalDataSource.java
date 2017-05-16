package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.cache.MemberCache;
import com.jitosoft.qrpay.data.entity.MemberEntity;

import io.reactivex.Flowable;

/**
 * Created by jihoon on 2017. 4. 29..
 */

public class MemberLocalDataSource implements MemberDataSource {

    static MemberLocalDataSource instance;
    MemberCache memberCache;

    private MemberLocalDataSource(MemberCache memberCache) {
        this.memberCache = memberCache;
    }

    public static MemberLocalDataSource getInstance(MemberCache memberCache) {
        if (instance == null) {
            instance = new MemberLocalDataSource(memberCache);
        }

        return instance;
    }

    @Override
    public Flowable<MemberEntity> saveMember(@NonNull String email, @NonNull String nickname, @NonNull String password) {
        throw new UnsupportedOperationException("this function always called in remote data source");
    }

    @Override
    public Flowable<MemberEntity> getMember() {

        return Flowable.defer(() -> Flowable.just(memberCache.getMemberEntity()));
    }
}
