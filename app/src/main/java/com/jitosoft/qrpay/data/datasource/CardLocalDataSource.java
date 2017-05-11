package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.data.entity.CardListEntity;


import io.reactivex.Flowable;

public class CardLocalDataSource implements CardDataSource {

    @Override
    public Flowable<CardListEntity> getCards(@NonNull String email) {

        // TODO: 2017. 5. 9.  get cards from local cache

        throw new UnsupportedOperationException("this function always called in remote data source");
    }

    @Override
    public Flowable<CardEntity> createCard(@NonNull String number, @NonNull String company, @NonNull String cvc) {
        throw new UnsupportedOperationException("this function always called in remote data source");
    }
}
