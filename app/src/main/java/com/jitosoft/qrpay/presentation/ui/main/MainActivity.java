package com.jitosoft.qrpay.presentation.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jitosoft.qrpay.BuildConfig;
import com.jitosoft.qrpay.Injection;
import com.jitosoft.qrpay.R;
import com.jitosoft.qrpay.data.executor.Executions;
import com.jitosoft.qrpay.domain.interaction.GetCardsUseCase;
import com.jitosoft.qrpay.presentation.ui.QrCodeActivity;
import com.jitosoft.qrpay.presentation.ui.main.adapter.CardAdapter;
import com.jitosoft.qrpay.presentation.ui.main.adapter.CardAdapterContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MainContract.Presenter presenter;
    private CardAdapterContract.View adapterView;
    private LinearLayoutManager layoutManager;

    public static void start(Activity activity, Intent intent) {
        activity.startActivity(intent);
        if (!BuildConfig.DEBUG) {
            activity.finish();
        }
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

        recyclerView.setAdapter(cardAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        setRecyclerViewOnScrollListener();

        presenter = new MainPresenter(this, getCardsUseCase)
                .setAdapterModel(cardAdapter);

        presenter.loadCards();

        cardAdapter.setItemClickListener(position ->
                presenter.generateJsonData(position)
        );
    }

    private void setRecyclerViewOnScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            final static int VISIBLE_THRESHOLD = 8;
            int prevTotalItemCount = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (prevTotalItemCount != totalItemCount && totalItemCount - lastVisibleItem <= VISIBLE_THRESHOLD) {
                    presenter.loadCardsMore();
                    prevTotalItemCount = totalItemCount;
                }
            }
        });
    }

    @Override
    public void refresh() {
        adapterView.refresh();
    }

    @Override
    public void moveToQrCodeView(String jsonData) {
        QrCodeActivity.start(this, jsonData);

    }
}
