package com.jitosoft.qrpay.data.entity;

import lombok.Getter;

@Getter
public class ResultEntity<T> {

    boolean result;
    String message;
    T data;
}
