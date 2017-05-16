package com.jitosoft.qrpay.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardEntity {

    @SerializedName("number")
    String number;
    @SerializedName("name")
    String name;
    @SerializedName("company")
    String company;
    @SerializedName("cvc")
    String cvc;
}
