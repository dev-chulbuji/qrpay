package com.jitosoft.qrpay.domain.interaction;

import android.support.annotation.Nullable;

import com.jitosoft.qrpay.domain.executor.ExecutionThread;
import com.jitosoft.qrpay.domain.executor.PostExecutionThread;
import com.jitosoft.qrpay.domain.model.CardList;
import com.jitosoft.qrpay.domain.repository.CardRepository;
import com.jitosoft.qrpay.domain.repository.MemberRepository;

import io.reactivex.Flowable;
import lombok.Setter;

public class GetCardsUseCase extends UseCase<CardList> {

    CardRepository cardRepository;
    MemberRepository memberRepository;

    @Setter
    boolean refresh;

    @Setter
    int page;

    public GetCardsUseCase(CardRepository cardRepository,
                           MemberRepository memberRepository,
                           @Nullable ExecutionThread executionThread,
                           @Nullable PostExecutionThread postExecutionThread) {

        super(executionThread, postExecutionThread);
        this.cardRepository = cardRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    protected Flowable<CardList> buildUseCaseFlowable() {
        return memberRepository.getMember()
                .flatMap(member -> cardRepository.getCards(refresh, member.getEmail(), page));
    }
}
