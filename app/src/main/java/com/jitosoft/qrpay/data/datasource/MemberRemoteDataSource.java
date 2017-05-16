package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.cache.MemberCache;
import com.jitosoft.qrpay.data.entity.MemberEntity;
import com.jitosoft.qrpay.data.entity.ResultEntity;
import com.jitosoft.qrpay.data.net.QrpayService;

import io.reactivex.Flowable;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class MemberRemoteDataSource implements MemberDataSource {

    static MemberRemoteDataSource instance;
    MemberCache memberCache;

    private MemberRemoteDataSource(MemberCache memberCache) {
        this.memberCache = memberCache;
    }

    public static MemberRemoteDataSource getInstance(MemberCache memberCache) {
        if (instance == null) {
            instance = new MemberRemoteDataSource(memberCache);
        }

        return instance;
    }

    @Override
    public Flowable<MemberEntity> saveMember(@NonNull String email,
                                             @NonNull String nickname,
                                             @NonNull String password) {

        return QrpayService.getRestApiClient().saveMember(email, nickname, password)
                .filter(ResultEntity::isResult)
                .map(ResultEntity::getData)
                .doOnNext(memberEntity ->
                        memberCache.setMemberEntity(memberEntity)
                );

    }

    @Override
    public Flowable<MemberEntity> getMember() {

        return QrpayService.getRestApiClient().getMember()
                .filter(ResultEntity::isResult)
                .map(ResultEntity::getData)
                .doOnNext(memberEntity ->
                        memberCache.setMemberEntity(memberEntity)
                );
    }
}
