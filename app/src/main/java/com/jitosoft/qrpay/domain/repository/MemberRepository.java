package com.jitosoft.qrpay.domain.repository;


import android.support.annotation.NonNull;

import com.jitosoft.qrpay.domain.Member;

import io.reactivex.Flowable;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public interface MemberRepository {

    Flowable<Member> saveMember(@NonNull String email, @NonNull String nickname, @NonNull String password);

    Flowable<Member> getMember();
}
