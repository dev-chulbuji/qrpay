package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.data.entity.ResultEntity;
import com.jitosoft.qrpay.data.net.QrpayService;
import com.jitosoft.qrpay.presentation.util.LogUtils;

import java.util.List;

import io.reactivex.Flowable;

public class CardRemoteDataSource implements CardDataSource {

    @Override
    public Flowable<List<CardEntity>> getCards(@NonNull String email) {
        return QrpayService.getRestApiClient().getCards(email)
                .filter(ResultEntity::isResult)
                .map(ResultEntity::getData)
                .doOnNext(cardListEntity -> {
                    LogUtils.debug(CardRemoteDataSource.class.getName(), "result : " + cardListEntity.get(0).toString());
                });
    }

    @Override
    public Flowable<CardEntity> createCard(@NonNull String number, @NonNull String company, @NonNull String cvc) {
        return QrpayService.getRestApiClient().createCard(number, company, cvc)
                .filter(ResultEntity::isResult)
                .map(ResultEntity::getData)
                .doOnNext(cardEntity -> {

                });
    }
}
