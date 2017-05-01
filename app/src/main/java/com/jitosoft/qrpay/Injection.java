package com.jitosoft.qrpay;

/**
 * Created by jihoon on 2017. 5. 1..
 */

public class Injection {

    public static TokenRepository provideTokenRepository() {
        return new TokenRepository();
    }


    public static MemberRepository provideMemberRepository() {
        return new MemberRepository();
    }
}
