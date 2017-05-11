package com.jitosoft.qrpay.data.net;

import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.data.entity.CardListEntity;
import com.jitosoft.qrpay.data.entity.MemberEntity;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApiClient {

    @FormUrlEncoded
    @POST("/join")
    Flowable<Response<MemberEntity>> saveMember(@Field("email") String email,
                                                @Field("nickname") String nickname,
                                                @Field("password") String password);

    // use header or email query
    @GET("/users")
    Flowable<Response<MemberEntity>> getMember();

    @GET("/cards")
    Flowable<Response<CardListEntity>> getCards(@Path("email") String email);

    @FormUrlEncoded
    @POST("/cards")
    Flowable<Response<CardEntity>> createCard(@Field("number") String number,
                                              @Field("company") String company,
                                              @Field("cvc") String cvc);
}
