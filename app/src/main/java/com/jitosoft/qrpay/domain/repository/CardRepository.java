package com.jitosoft.qrpay.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jitosoft.qrpay.domain.model.Card;
import com.jitosoft.qrpay.domain.model.CardList;


import io.reactivex.Flowable;

public interface CardRepository {

    Flowable<CardList> getCards(@Nullable boolean refresh, @NonNull String email);

    Flowable<Card> createCard(@NonNull String number,
                              @NonNull String company,
                              @NonNull String cvc);
}
