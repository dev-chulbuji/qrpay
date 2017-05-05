package com.jitosoft.qrpay.presentation.signup;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.domain.Member;
import com.jitosoft.qrpay.domain.interaction.SignUpUseCase;
import com.jitosoft.qrpay.presentation.mvp.AbsPresenter;

import io.reactivex.subscribers.DisposableSubscriber;

public class SignUpPresenter extends AbsPresenter<SignUpContract.View> implements SignUpContract.Presenter {

    SignUpUseCase signUpUseCase;

    public SignUpPresenter(SignUpContract.View view,
                           SignUpUseCase signUpUseCase) {
        super(view);
        this.signUpUseCase = signUpUseCase;
    }

    @Override
    public void signUp(@NonNull String email, @NonNull String nickname, @NonNull String password) {
        signUpUseCase.setEmail(email);
        signUpUseCase.setNickname(nickname);
        signUpUseCase.setPassword(password);
        signUpUseCase.execute(new DisposableSubscriber<Member>() {
            @Override
            public void onNext(Member member) {
                getView().moveToMainView();
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
