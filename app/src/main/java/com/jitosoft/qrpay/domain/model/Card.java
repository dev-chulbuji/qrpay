package com.jitosoft.qrpay.domain.model;

import lombok.Getter;

@Getter
public class Card {
    String number;
    String company;
    String name;
    String cvc;

    private Card(Builder builder) {
        this.number = builder.number;
        this.company = builder.company;
        this.cvc = builder.cvc;
        this.name = builder.name;
    }

    public static class Builder {
        String number;
        String company;
        String cvc;
        String name;

        public Builder(String number, String company, String cvc, String name) {
            this.number = number;
            this.company = company;
            this.cvc = cvc;
            this.name = name;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
