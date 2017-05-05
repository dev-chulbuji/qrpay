package com.jitosoft.qrpay.data.entity;

import lombok.Getter;

@Getter
public class ResponseEntity<T> {

    String message;
    T data;
}
