package com.jitosoft.qrpay.presentation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardDisplayModel extends DisplayModel {
    String cardCompany;
    String cardNumber;
    String cvc;
    String name;

    public CardDisplayModel(Builder builder) {
        this.cardCompany = builder.cardCompany;
        this.cardNumber = builder.cardNumber;
        this.cvc = builder.cvc;
        this.name = builder.name;

    }

    public static class Builder {
        String cardCompany;
        String cardNumber;
        String cvc;
        String name;

        public Builder(String cardCompany, String cardNumber, String cvc, String name) {
            this.cardCompany = cardCompany;
            this.cardNumber = cardNumber;
            this.cvc = cvc;
            this.name = name;
        }

        public CardDisplayModel build() {
            return new CardDisplayModel(this);
        }
    }
}
