package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.entity.CardEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface CardDataSource {

    Flowable<List<CardEntity>> getCards(@NonNull String email,
                                        @NonNull int page);

    Flowable<CardEntity> createCard(@NonNull String number,
                                    @NonNull String company,
                                    @NonNull String cvc);

}
