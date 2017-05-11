package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.data.entity.CardListEntity;
import com.jitosoft.qrpay.data.net.QrpayService;
import com.jitosoft.qrpay.data.net.Response;


import io.reactivex.Flowable;

public class CardRemoteDataSource implements CardDataSource {

    @Override
    public Flowable<CardListEntity> getCards(@NonNull String email) {
        return QrpayService.getRestApiClient().getCards(email)
                .map(Response::getResult)
                .doOnNext(cardListEntity -> {
                    if (!cardListEntity.isResult()) {
                        throw new Exception(cardListEntity.getMessage());
                    }
                });
    }

    @Override
    public Flowable<CardEntity> createCard(@NonNull String number, @NonNull String company, @NonNull String cvc) {
        return QrpayService.getRestApiClient().createCard(number, company, cvc)
                .map(Response::getResult)
                .doOnNext(cardEntity -> {
                    if (!cardEntity.isResult()) {
                        throw new Exception(cardEntity.getMessage());
                    }
                });
    }
}
