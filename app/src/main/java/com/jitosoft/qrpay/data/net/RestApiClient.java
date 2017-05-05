package com.jitosoft.qrpay.data.net;

import com.jitosoft.qrpay.data.entity.MemberEntity;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApiClient {

    @FormUrlEncoded
    @POST("/join")
    Flowable<Response<MemberEntity>> saveMember(@Field("email") String email,
                                                @Field("nickname") String nickname,
                                                @Field("password") String password);

    // use header or email query
    @GET("/users")
    Flowable<Response<MemberEntity>> getMember();
}
