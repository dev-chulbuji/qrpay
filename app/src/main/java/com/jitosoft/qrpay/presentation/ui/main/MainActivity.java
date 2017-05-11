package com.jitosoft.qrpay.presentation.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jitosoft.qrpay.Injection;
import com.jitosoft.qrpay.R;
import com.jitosoft.qrpay.data.executor.Executions;
import com.jitosoft.qrpay.domain.interaction.GetCardsUseCase;
import com.jitosoft.qrpay.presentation.ui.main.adapter.CardAdapter;
import com.jitosoft.qrpay.presentation.ui.main.adapter.CardAdapterContract;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private CardAdapterContract.View adapterView;

    public static void start(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GetCardsUseCase getCardsUseCase = new GetCardsUseCase(
                Injection.provideCardRepository(),
                Injection.provideMemberRepository(),
                Executions.job(),
                Executions.ui());

        CardAdapter cardAdapter = new CardAdapter();

        adapterView = cardAdapter;

        presenter = new MainPresenter(this, getCardsUseCase)
                .setAdapterModel(cardAdapter);

        presenter.loadCards();
    }

    @Override
    public void refresh() {
        adapterView.refresh();
    }
}
