package com.jitosoft.qrpay.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.jitosoft.qrpay.data.datasource.CardDataSource;
import com.jitosoft.qrpay.data.datasource.CardLocalDataSource;
import com.jitosoft.qrpay.data.datasource.CardRemoteDataSource;
import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.domain.model.Card;
import com.jitosoft.qrpay.domain.model.CardList;
import com.jitosoft.qrpay.domain.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class CardDataRepository implements CardRepository {

    private static CardDataRepository instance;

    private CardDataRepository() {

    }

    public static CardDataRepository newInstance() {
        if (instance == null) {
            instance = new CardDataRepository();
        }
        return instance;
    }

    @Override
    public Flowable<CardList> getCards(@Nullable boolean refresh,
                                       @NonNull String email,
                                       @NonNull int page) {
        CardDataSource cardDataSource;
        if (refresh) {
            cardDataSource = new CardRemoteDataSource();
        } else {
            cardDataSource = new CardLocalDataSource();
        }

        return cardDataSource.getCards(email, page)
                .map(this::transform);


    }

    @Override
    public Flowable<Card> createCard(@NonNull String number, @NonNull String company, @NonNull String cvc) {
        CardDataSource cardDataSource = new CardRemoteDataSource();
        return cardDataSource.createCard(number, company, cvc)
                .map(this::transform);
    }

    private Card transform(CardEntity cardEntity) {
        return new Card.Builder(
                cardEntity.getNumber(),
                cardEntity.getCompany(),
                cardEntity.getCvc(),
                cardEntity.getName())
                .build();
    }

    private CardList transform(List<CardEntity> cardEntities) {
        CardList cardList = new CardList();
        cardList.setCardList(Observable.fromIterable(MoreObjects.firstNonNull(cardEntities, new ArrayList<>()))
                .map(this::transform)
                .toList()
                .blockingGet());

        return cardList;
    }
}
