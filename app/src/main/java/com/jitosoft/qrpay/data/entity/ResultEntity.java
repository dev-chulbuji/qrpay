package com.jitosoft.qrpay.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultEntity<T> {

    boolean result;
    String message;
    T data;
}
