package com.jitosoft.qrpay.presentation.ui.main;

import com.google.gson.Gson;
import com.jitosoft.qrpay.BuildConfig;
import com.jitosoft.qrpay.domain.interaction.GetCardsUseCase;
import com.jitosoft.qrpay.domain.model.Card;
import com.jitosoft.qrpay.domain.model.CardList;
import com.jitosoft.qrpay.presentation.model.CardDisplayModel;
import com.jitosoft.qrpay.presentation.mvp.AbsPresenter;
import com.jitosoft.qrpay.presentation.ui.main.adapter.CardAdapterContract;
import com.jitosoft.qrpay.presentation.util.LogUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.subscribers.DisposableSubscriber;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class MainPresenter extends AbsPresenter<MainContract.View> implements MainContract.Presenter {

    private GetCardsUseCase getCardsUseCase;

    private CardAdapterContract.Model adapterModel;
    private List<Card> cachedCardList;
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private boolean isExistNextPage;

    public MainPresenter(MainContract.View view,
                         GetCardsUseCase getCardsUseCase) {
        super(view);
        this.getCardsUseCase = getCardsUseCase;
    }

    @Override
    public void loadCards() {
        getCardsUseCase.setRefresh(true);
        getCardsUseCase.execute(new DisposableSubscriber<CardList>() {
            @Override
            public void onNext(CardList cardList) {
                if (cardList.getCardList().size() > 0) {

                    cachedCardList = cardList.getCardList();
                    adapterModel.setItems(
                            Observable.fromIterable(cardList.getCardList())
                                    .map(card -> transform(card))
                                    .toList()
                                    .blockingGet()
                    );

                    getView().refresh();
                }

                // isExistNextPage = cardList.isExistNextPage();
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.error(MainPresenter.class.getName(), t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadCardsMore() {
        if (!BuildConfig.DEBUG && !isExistNextPage) {
            return;
        }

        getCardsUseCase.setPage(atomicInteger.getAndIncrement());
        getCardsUseCase.setRefresh(true);
        getCardsUseCase.execute(new DisposableSubscriber<CardList>() {
            @Override
            public void onNext(CardList cardList) {
                if (cardList.getCardList().size() > 0) {

                    cachedCardList = cardList.getCardList();
                    adapterModel.addAll(
                            Observable.fromIterable(cardList.getCardList())
                                    .map(card -> transform(card))
                                    .toList()
                                    .blockingGet()
                    );

                    getView().refresh();
                }
                // isExistNextPage = cardList.isExistNextPage();
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.error(MainPresenter.class.getName(), t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void generateJsonData(int position) {
        if (cachedCardList != null) {
            String jsonData = new Gson().toJson(cachedCardList.get(position), Card.class);
            getView().moveToQrCodeView(jsonData);
        }
    }

    private CardDisplayModel transform(Card card) {
        return new CardDisplayModel.Builder(
                card.getCompany(),
                card.getNumber(),
                card.getCvc(),
                card.getName())
                .build();
    }
}
