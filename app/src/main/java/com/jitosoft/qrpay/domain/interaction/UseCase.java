package com.jitosoft.qrpay.domain.interaction;

import android.support.annotation.Nullable;

import com.jitosoft.qrpay.domain.executor.ExecutionThread;
import com.jitosoft.qrpay.domain.executor.PostExecutionThread;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class UseCase<T> {

    private final ExecutionThread executionThread;
    private final PostExecutionThread postExecutionThread;
    private DisposableSubscriber<T> disposableSubscriber;

    public UseCase(@Nullable ExecutionThread executionThread,
                   @Nullable PostExecutionThread postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Flowable<T> buildUseCaseFlowable();

    public void execute(DisposableSubscriber<T> useCaseSubscriber) {
        Flowable<T> flowable = buildUseCaseFlowable();

        if (executionThread != null) {
            flowable = flowable.subscribeOn(executionThread.getScheduler());
        }
        if (postExecutionThread != null) {
            flowable = flowable.observeOn(postExecutionThread.getScheduler());
        }

        disposableSubscriber = flowable.subscribeWith(useCaseSubscriber);

    }

    public void dispose() {
        if (disposableSubscriber != null && !disposableSubscriber.isDisposed()) {
            disposableSubscriber.dispose();
        }
    }
}
