package com.jitosoft.qrpay.data.repository;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.datasource.MemberDataSource;
import com.jitosoft.qrpay.data.datasource.MemberLocalDataSource;
import com.jitosoft.qrpay.data.datasource.MemberRemoteDataSource;
import com.jitosoft.qrpay.data.entity.MemberEntity;
import com.jitosoft.qrpay.domain.Member;
import com.jitosoft.qrpay.domain.repository.MemberRepository;

import io.reactivex.Flowable;

public class MemberDataRepository implements MemberRepository {

    private static MemberDataRepository instance;

    MemberDataSource memberDataSource;

    private MemberDataRepository() {

    }

    public static MemberDataRepository newInstance() {
        if (instance == null) {
            instance = new MemberDataRepository();
        }
        return instance;
    }


    @Override
    public Flowable<Member> saveMember(@NonNull String email,
                                       @NonNull String nickname,
                                       @NonNull String password) {
        memberDataSource = new MemberRemoteDataSource();
        return memberDataSource.saveMember(email, nickname, password)
                .map(this::transform);
    }

    @Override
    public Flowable<Member> getMember() {
        memberDataSource = new MemberLocalDataSource();
        return memberDataSource.getMember()
                .map(this::transform);
    }

    private Member transform(MemberEntity memberEntity) {
        return new Member.Builder(
                memberEntity.getEmail(),
                memberEntity.getNickname())
                .build();
    }
}
