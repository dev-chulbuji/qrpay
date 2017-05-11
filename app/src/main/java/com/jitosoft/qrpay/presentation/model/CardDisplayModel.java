package com.jitosoft.qrpay.presentation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDisplayModel extends DisplayModel {
    String cardCompany;
    String cardNumber;
    String cvc;

    public CardDisplayModel(Builder builder) {
        this.cardCompany = builder.cardCompany;
        this.cardNumber = builder.cardNumber;
        this.cvc = builder.cvc;

    }

    public static class Builder {
        String cardCompany;
        String cardNumber;
        String cvc;

        public Builder(String cardCompany, String cardNumber, String cvc) {
            this.cardCompany = cardCompany;
            this.cardNumber = cardNumber;
            this.cvc = cvc;
        }

        public CardDisplayModel build() {
            return new CardDisplayModel(this);
        }
    }
}
