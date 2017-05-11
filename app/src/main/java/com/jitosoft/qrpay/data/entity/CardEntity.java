package com.jitosoft.qrpay.data.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardEntity extends ResultEntity {

    String number;
    String name;
    String company;
    String cvc;
}
