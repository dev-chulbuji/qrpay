package com.jitosoft.qrpay.presentation.ui.main;

import com.jitosoft.qrpay.domain.interaction.GetCardsUseCase;
import com.jitosoft.qrpay.domain.model.Card;
import com.jitosoft.qrpay.domain.model.CardList;
import com.jitosoft.qrpay.presentation.model.CardDisplayModel;
import com.jitosoft.qrpay.presentation.mvp.AbsPresenter;
import com.jitosoft.qrpay.presentation.ui.main.adapter.CardAdapterContract;

import io.reactivex.Observable;
import io.reactivex.subscribers.DisposableSubscriber;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class MainPresenter extends AbsPresenter<MainContract.View> implements MainContract.Presenter {

    private GetCardsUseCase getCardsUseCase;

    private CardAdapterContract.Model adapterModel;

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
                    adapterModel.setItems(
                            Observable.fromIterable(cardList.getCardList())
                                    .map(card -> transform(card))
                                    .toList()
                                    .blockingGet()
                    );
                }
                getView().refresh();
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private CardDisplayModel transform(Card card) {
        return new CardDisplayModel.Builder(
                card.getCompany(),
                card.getNumber(),
                card.getCvc())
                .build();
    }
}
