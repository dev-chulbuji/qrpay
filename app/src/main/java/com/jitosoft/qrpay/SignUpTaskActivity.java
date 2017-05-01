package com.jitosoft.qrpay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class SignUpTaskActivity extends AppCompatActivity {

    private static final String SIGNUP_VIEWMODEL_TAG = "SIGNUP_VIEWMODEL_TAG";
    SignUpTaskViewModel signUpTaskViewModel;

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.password)
    EditText password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signUpTaskViewModel = findOrCreateViewModel();
    }

    @OnClick(R.id.signup)
    void onSignUpClicked() {
        signUpTaskViewModel.signUp(email.getText().toString(),
                nickname.getText().toString(),
                password.getText().toString());

    }

    private SignUpTaskViewModel findOrCreateViewModel() {

        @SuppressWarnings("unchecked")
        ViewModelHolder<SignUpTaskViewModel> retainedViewModel =
                (ViewModelHolder<SignUpTaskViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(SIGNUP_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewModel();
        } else {
            // There is no ViewModel yet, create it.
            SignUpTaskViewModel viewModel = new SignUpTaskViewModel(
                    Injection.provideTokenRepository(),
                    Injection.provideMemberRepository()
            );

            // and bind it to this Activity's lifecycle using the Fragment Manager.
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    SIGNUP_VIEWMODEL_TAG);
            return viewModel;
        }
    }
}
