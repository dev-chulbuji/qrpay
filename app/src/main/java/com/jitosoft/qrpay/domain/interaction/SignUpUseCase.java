package com.jitosoft.qrpay.domain.interaction;

import android.support.annotation.Nullable;

import com.jitosoft.qrpay.domain.model.Member;
import com.jitosoft.qrpay.domain.repository.MemberRepository;
import com.jitosoft.qrpay.domain.executor.ExecutionThread;
import com.jitosoft.qrpay.domain.executor.PostExecutionThread;

import io.reactivex.Flowable;
import lombok.Setter;

public class SignUpUseCase extends UseCase<Member> {

    private final MemberRepository memberRepository;

    @Setter
    String email;
    @Setter
    String nickname;
    @Setter
    String password;

    public SignUpUseCase(@Nullable ExecutionThread executionThread,
                         @Nullable PostExecutionThread postExecutionThread,
                         MemberRepository memberRepository) {
        super(executionThread, postExecutionThread);
        this.memberRepository = memberRepository;
    }

    @Override
    protected Flowable<Member> buildUseCaseFlowable() {
        return memberRepository.saveMember(email, nickname, password);
    }
}
