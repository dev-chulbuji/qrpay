package com.jitosoft.qrpay.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultEntity<T> {

    boolean result;
    String message;
    T data;
}
