package com.jitosoft.qrpay;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.google.common.base.Strings;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class SignUpTaskViewModel implements TokenDataSource.GetTokenCallback, MemberDataSource.SaveMemberCallback {

    private static final String TAG = SignUpTaskViewModel.class.getName();

    ObservableBoolean dataLoading = new ObservableBoolean(false);

    ObservableField<String> email = new ObservableField<>();
    ObservableField<String> nickname = new ObservableField<>();
    ObservableField<String> password = new ObservableField<>();

    private final TokenRepository tokenRepository;

    private final MemberRepository memberRepository;

    public SignUpTaskViewModel(TokenRepository tokenRepository,
                               MemberRepository memberRepository) {
        this.tokenRepository = tokenRepository;
        this.memberRepository = memberRepository;
    }

    public void signUp(@NonNull String email, @NonNull String nickname, @NonNull String password) {
        // validation check

        memberRepository.saveMember(email, nickname, password, this);
    }

    @Override
    public void onTokenLoaded(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return;
        }

        LogUtils.debug(TAG, "token : " + token);
    }

    @Override
    public void onSaveMemberCompleted() {
        tokenRepository.getToken(email.get(), password.get(), this);

        LogUtils.debug(TAG, "onSaveMemberCompleted called");
    }

}
