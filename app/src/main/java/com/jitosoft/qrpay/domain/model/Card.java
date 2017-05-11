package com.jitosoft.qrpay.domain.model;

import lombok.Getter;

@Getter
public class Card {
    String number;
    String company;
    String cvc;

    private Card(Builder builder) {
        this.number = builder.number;
        this.company = builder.company;
        this.cvc = builder.cvc;
    }

    public static class Builder {
        String number;
        String company;
        String cvc;

        public Builder(String number, String company, String cvc) {
            this.number = number;
            this.company = company;
            this.cvc = cvc;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
