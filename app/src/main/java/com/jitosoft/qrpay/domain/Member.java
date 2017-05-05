package com.jitosoft.qrpay.domain;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 29..
 */

public class Member {

    String email;
    String nickname;

    private Member(Builder builder) {
        this.email = builder.email;
        this.nickname = builder.nickname;
    }

    public static class Builder {

        String email, nickname;

        public Builder(@NonNull String email, @NonNull String nickname) {
            this.email = email;
            this.nickname = nickname;
        }

        public Member build() {
            return new Member(this);
        }
    }
}