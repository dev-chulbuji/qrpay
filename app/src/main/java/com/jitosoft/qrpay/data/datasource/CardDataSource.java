package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.data.entity.CardListEntity;


import io.reactivex.Flowable;

public interface CardDataSource {

    Flowable<CardListEntity> getCards(@NonNull String email);

    Flowable<CardEntity> createCard(@NonNull String number,
                                    @NonNull String company,
                                    @NonNull String cvc);

}
