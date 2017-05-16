package com.jitosoft.qrpay.data.repository;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.datasource.MemberDataSource;
import com.jitosoft.qrpay.data.datasource.MemberDataSourceFactory;
import com.jitosoft.qrpay.data.entity.MemberEntity;
import com.jitosoft.qrpay.domain.model.Member;
import com.jitosoft.qrpay.domain.repository.MemberRepository;

import io.reactivex.Flowable;

public class MemberDataRepository implements MemberRepository {

    private static MemberDataRepository instance;
    MemberDataSource memberDataSource;
    private MemberDataSourceFactory memberDataSourceFactory;

    private MemberDataRepository(MemberDataSourceFactory memberDataSourceFactory) {
        this.memberDataSourceFactory = memberDataSourceFactory;
    }

    public static MemberDataRepository newInstance(MemberDataSourceFactory memberDataSourceFactory) {
        if (instance == null) {
            instance = new MemberDataRepository(memberDataSourceFactory);
        }
        return instance;
    }


    @Override
    public Flowable<Member> saveMember(@NonNull String email,
                                       @NonNull String nickname,
                                       @NonNull String password) {
        memberDataSource = memberDataSourceFactory.remote();
        return memberDataSource.saveMember(email, nickname, password)
                .map(this::transform)
                .doOnNext(member -> {
                    if (member == null) {
                        throw new Exception("member is null");
                    }
                });
    }

    @Override
    public Flowable<Member> getMember() {
        memberDataSource = memberDataSourceFactory.local();
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
