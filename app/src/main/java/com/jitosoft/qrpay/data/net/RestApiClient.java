package com.jitosoft.qrpay.data.net;

import com.jitosoft.qrpay.data.entity.CardEntity;
import com.jitosoft.qrpay.data.entity.MemberEntity;
import com.jitosoft.qrpay.data.entity.ResultEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApiClient {

    @FormUrlEncoded
    @POST("/join")
    Flowable<ResultEntity<MemberEntity>> saveMember(@Field("email") String email,
                                                    @Field("nickname") String nickname,
                                                    @Field("password") String password);

    // use header or email query
    @GET("/users")
    Flowable<ResultEntity<MemberEntity>> getMember();

    @GET("/cards/{email}")
    Flowable<ResultEntity<List<CardEntity>>> getCards(@Path("email") String email);

    @FormUrlEncoded
    @POST("/cards")
    Flowable<ResultEntity<CardEntity>> createCard(@Field("number") String number,
                                                  @Field("company") String company,
                                                  @Field("cvc") String cvc);
}
