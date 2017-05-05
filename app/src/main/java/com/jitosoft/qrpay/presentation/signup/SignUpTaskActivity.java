package com.jitosoft.qrpay.presentation.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.jitosoft.qrpay.Injection;
import com.jitosoft.qrpay.R;
import com.jitosoft.qrpay.data.executor.Executions;
import com.jitosoft.qrpay.domain.interaction.SignUpUseCase;
import com.jitosoft.qrpay.presentation.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class SignUpTaskActivity extends AppCompatActivity implements SignUpContract.View {

    private static final String TAG = SignUpTaskActivity.class.getName();

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.password)
    EditText password;

    SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        SignUpUseCase signUpUseCase = new SignUpUseCase(
                Executions.job(),
                Executions.ui(),
                Injection.provideMemberRepository());

        presenter = new SignUpPresenter(this, signUpUseCase);
    }

    @OnClick(R.id.signup)
    void onSignUpClicked() {
        presenter.signUp(email.getText().toString(),
                nickname.getText().toString(),
                password.getText().toString());

    }

    @Override
    public void moveToMainView() {
        // goto main view

        LogUtils.debug(TAG, "moveToMainView");
    }
}
