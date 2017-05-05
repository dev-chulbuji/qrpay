package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.MemberEntity;
import com.jitosoft.qrpay.data.net.QrpayService;
import com.jitosoft.qrpay.data.net.Response;

import io.reactivex.Flowable;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class MemberRemoteDataSource implements MemberDataSource {

    @Override
    public Flowable<MemberEntity> saveMember(@NonNull String email,
                                             @NonNull String nickname,
                                             @NonNull String password) {

        return QrpayService.getRestApiClient().saveMember(email, nickname, password)
                .map(Response::getResult)
                .doOnNext(memberEntity -> {
                    if (!memberEntity.isResult()) {
                        throw new Exception(memberEntity.getMessage());
                    }
                });

    }

    @Override
    public Flowable<MemberEntity> getMember() {

        return QrpayService.getRestApiClient().getMember()
                .map(Response::getResult)
                .doOnNext(memberEntity -> {
                    if (!memberEntity.isResult()) {
                        throw new Exception(memberEntity.getMessage());
                    }
                });
    }
}
